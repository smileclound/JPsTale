package org.pstale.optimize;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.pstale.utils.FileLocator;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.plugins.smd.SMDTYPE;
import com.jme3.scene.plugins.smd.SmdKey;
import com.jme3.scene.plugins.smd.SmdLoader;

/**
 * <h2>测试九宫格网格。</h2> 定义一个256 * 256大小的矩阵，将地图划分成65536个格子。统计地图网格中所有的三角形，看看它们都落在
 * 哪个格子的范围内。一个3万面的地图，平均落在每个格子中只有10个面。大部分格子中只有1~3个面，少部分区域
 * 由于建筑比较复杂，可能会有100面左右。</br>
 * 
 * <h2>用途</h2> 在进行碰撞检测时，可以根据玩家的坐标得知他落在哪个单元格中。取以这个格子为中心的九宫格，只对这九个 格子 中的三角形进行碰撞检测。
 * 
 * @author yanmaoyuan
 * 
 */
public class TestGrid {

    static Logger log = Logger.getLogger(TestGrid.class);

    private final static int MAP_SIZE = 256;

    // 网格
    private Mesh mesh;

    /**
     * 用于存储地图的边界点bounds。地图的网格是3D坐标，但我只需要取用xz坐标。
     */
    private Vector3f max;
    private Vector3f min;

    private float width;
    private float height;

    private float unitX;
    private float unitY;

    public TestGrid(Mesh mesh) {
        this.mesh = mesh;

        // 包围盒
        BoundingBox bb = (BoundingBox) mesh.getBound();
        max = new Vector3f();
        min = new Vector3f();
        bb.getMin(min);
        bb.getMax(max);

        width = max.x - min.x;
        height = max.z - min.z;

        unitX = width / MAP_SIZE;
        unitY = height / MAP_SIZE;
    }

    /**
     * 统计每个区域有多少个顶点，同时记录每个顶点落在那个区域了。
     * 
     * @return
     */
    public int[][] vec() {
        int area[][] = new int[MAP_SIZE][MAP_SIZE];

        // 获得顶点数据
        FloatBuffer fb = (FloatBuffer) mesh.getBuffer(Type.Position).getData();
        int vCount = fb.limit() / 3;
        // 统计每个区域有多少个顶点，同时记录每个顶点落在那个区域了。
        int vPosition[] = new int[vCount];
        for (int i = 0; i < vCount; i++) {
            float x = fb.get(i * 3);
            float z = fb.get(i * 3 + 2);

            Point p = getAreaPosition(x, z);

            vPosition[i] = p.y * MAP_SIZE + p.x;
            area[p.y][p.x]++;
        }

        return area;
    }

    /**
     * 统计每个格子中有多少个面
     */
    public int[][] face() {

        int[][] area = new int[MAP_SIZE][MAP_SIZE];

        // 用来表示每个三角形的顶点坐标
        Vector2f[] tri = new Vector2f[] { new Vector2f(), new Vector2f(), new Vector2f() };
        // 用来表示每个矩形的顶点坐标
        Vector2f[] box = new Vector2f[] { new Vector2f(), new Vector2f(), new Vector2f(), new Vector2f() };

        // 获得顶点数据
        FloatBuffer fb = (FloatBuffer) mesh.getBuffer(Type.Position).getData();

        // 三角形数据
        IntBuffer ib = (IntBuffer) mesh.getBuffer(Type.Index).getData();

        int fCount = ib.limit() / 3;// 面数
        for (int i = 0; i < fCount; i++) {
            // 连续添加3个点，构建一个三角形。

            float boundsMinX = Float.MAX_VALUE;
            float boundsMinY = Float.MAX_VALUE;
            float boundsMaxX = -Float.MAX_VALUE;
            float boundsMaxY = -Float.MAX_VALUE;

            Point[] p = new Point[3];
            for (int j = 0; j < 3; j++) {
                int vIndex = ib.get(i * 3 + j);

                // 取顶点的x、z坐标，映射到2D平面上。
                float x = fb.get(vIndex * 3);
                float z = fb.get(vIndex * 3 + 2);

                // 获取三角形的包围盒
                boundsMinX = Math.min(boundsMinX, x);
                boundsMaxX = Math.max(boundsMaxX, x);

                boundsMinY = Math.min(boundsMinY, z);
                boundsMaxY = Math.max(boundsMaxY, z);

                tri[j].set(x, z);

                // 看看这个点落在哪里
                p[j] = getAreaPosition(x, z);
            }

            // 下面要看看三角形落在那几个area中。
            Point p1 = getAreaPosition(boundsMinX, boundsMinY);
            Point p2 = getAreaPosition(boundsMaxX, boundsMaxY);

            // 遍历附近的每个矩形，看看和这个三角形是否相交。
            for (int py = p1.y; py <= p2.y; py++) {
                for (int px = p1.x; px <= p2.x; px++) {

                    float boxX = min.x + px * unitX;
                    float boxY = min.z + py * unitY;
                    box[0].set(boxX, boxY);
                    box[1].set(boxX + unitX, boxY);
                    box[2].set(boxX + unitX, boxY + unitY);
                    box[3].set(boxX, boxY + unitY);

                    /**
                     * 判断这个方块和三角形是否相交。 若三角形的顶点落在这个矩形内，他们相交。 若矩形的任意一点落在三角形内，他们相交。
                     */
                    if ((p[0].x == px && p[0].y == py) || (p[1].x == px && p[1].y == py)
                            || (p[2].x == px && p[2].y == py) || intersect(tri[0], tri[1], tri[2], box)) {

                        area[py][px]++;
                    }
                }
            }
        }

        return area;
    }

    public Point getAreaPosition(final float x, final float z) {
        float X = x - min.x;
        float Z = z - min.z;

        float col = X / unitX;
        float row = Z / unitY;

        if (col < 0) {
            col = 0;
        }

        if (row < 0) {
            row = 0;
        }

        if (col >= MAP_SIZE - 1) {
            col = MAP_SIZE - 1;
        }

        if (row >= MAP_SIZE - 1) {
            row = MAP_SIZE - 1;
        }

        return new Point((int) col, (int) row);
    }

    /**
     * 如果矩形的4个顶点有任何一个落在三角新abc内，就这个格子就需要对这个三角形进行碰撞检测。
     * 
     * @param a
     * @param b
     * @param c
     * @param box
     * @return
     */
    private boolean intersect(final Vector2f a, final Vector2f b, final Vector2f c, final Vector2f[] box) {
        for (int k = 0; k < 4; k++) {
            if (pointinTriangle(a, b, c, box[k])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断顶点P是否在由三角形ABC，使用重心坐标进行计算。 若向量AP = uAB + vAC，且0<=v<=1, 0<=u<=1, u + v <=
     * 1，则P在三角形ABC内部。
     * 
     * @param A
     * @param B
     * @param C
     * @param P
     * @return
     */
    private boolean pointinTriangle(Vector2f A, Vector2f B, Vector2f C, Vector2f P) {
        Vector2f v0 = C.subtract(A);
        Vector2f v1 = B.subtract(A);
        Vector2f v2 = P.subtract(A);

        float dot00 = v0.dot(v0);
        float dot01 = v0.dot(v1);
        float dot02 = v0.dot(v2);
        float dot11 = v1.dot(v1);
        float dot12 = v1.dot(v2);

        float inverDeno = 1 / (dot00 * dot11 - dot01 * dot01);

        float u = (dot11 * dot02 - dot01 * dot12) * inverDeno;
        if (u < 0 || u > 1) // if u out of range, return directly
        {
            return false;
        }

        float v = (dot00 * dot12 - dot01 * dot02) * inverDeno;
        if (v < 0 || v > 1) // if v out of range, return directly
        {
            return false;
        }

        return u + v <= 1;
    }

    /**
     * 统计格子中的均值
     * 
     * @param area
     * @return
     */
    public static int avg(int[][] area) {
        int sum = 0;
        int count = 0;
        int c = 0;
        for (int row = 0; row < MAP_SIZE; row++) {
            for (int col = 0; col < MAP_SIZE; col++) {
                c = area[row][col];
                if (c > 0) {
                    sum += c;
                    count++;
                }
            }
        }

        return sum / count;
    }

    /**
     * 统计格子中最大值
     * 
     * @param area
     * @return
     */
    public static int max(int[][] area) {
        int max = 0;
        int c = 0;
        for (int row = 0; row < MAP_SIZE; row++) {
            for (int col = 0; col < MAP_SIZE; col++) {
                c = area[row][col];
                if (max < c) {
                    max = c;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {

        // 初始化资源管理器
        AssetManager assetManager = new DesktopAssetManager();
        assetManager.registerLoader(SmdLoader.class, "smd");
        if (new File("I:/game/PTCN-RPT1.0").exists()) {
            assetManager.registerLocator("I:/game/PTCN-RPT1.0", FileLocator.class);
        } else {
            assetManager.registerLocator("D:/Priston Tale/PTCN3550/PTCN3550", FileLocator.class);
        }

        // 读取地图的smd文件
        Mesh stage = (Mesh) assetManager.loadAsset(new SmdKey("Field/dungeon/dun-4.smd", SMDTYPE.STAGE3D_COLLISION));

        // 绘制三角形网格
        drawTri(stage);
        log.info("绘制tri.png");

        TestGrid grid = new TestGrid(stage);
        // 统计每个区域有多少个顶点，同时记录每个顶点落在那个区域了。
        int[][] area = grid.vec();
        int avgCount = avg(area);
        int maxCount = max(area);

        log.info("avg=" + avgCount + " max=" + maxCount);

        drawVCount(area, maxCount, "map.png");

        // 统计每个面落在哪个格子
        area = grid.face();
        avgCount = avg(area);
        maxCount = max(area);

        log.info("avg=" + avgCount + " max=" + maxCount);
        drawVCount(area, maxCount, "f.png");

    }

    // 绘图单元格的大小
    static int GRID_SIZE = 8;

    // 绘制三角形网格
    private static void drawTri(Mesh mesh) {
        BoundingBox bb = (BoundingBox) mesh.getBound();

        Vector3f max = new Vector3f();
        Vector3f min = new Vector3f();
        bb.getMin(min);
        bb.getMax(max);

        float width = max.x - min.x;
        float height = max.z - min.z;

        // 统计每个面落在哪个格子
        IntBuffer ib = (IntBuffer) mesh.getBuffer(Type.Index).getData();
        FloatBuffer fb = (FloatBuffer) mesh.getBuffer(Type.Position).getData();
        int fCount = ib.limit() / 3;// 面数

        // 准备画三角形
        BufferedImage image = new BufferedImage(MAP_SIZE * GRID_SIZE, MAP_SIZE * GRID_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, MAP_SIZE * GRID_SIZE, MAP_SIZE * GRID_SIZE);
        // 临时变量，用来表示每个三角形
        Polygon drawTri;
        for (int i = 0; i < fCount; i++) {
            // 连续添加3个点，构建一个三角形。
            drawTri = new Polygon();

            for (int j = 0; j < 3; j++) {
                int vIndex = ib.get(i * 3 + j);
                // 取顶点的x、z坐标，映射到2D平面上。
                float x = fb.get(vIndex * 3);
                float z = fb.get(vIndex * 3 + 2);

                float X = x - min.x;
                float Y = z - min.z;

                int px = (int) (MAP_SIZE * GRID_SIZE * X / width);
                int py = (int) (MAP_SIZE * GRID_SIZE * Y / height);

                drawTri.addPoint(px, py);
            }

            g.setColor(Color.red);
            g.draw(drawTri);

        }

        try {
            ImageIO.write(image, "png", new File("tri.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void drawVCount(int[][] area, int maxCount, String fileName) {

        // 把它画下来
        BufferedImage image = new BufferedImage(MAP_SIZE * GRID_SIZE, MAP_SIZE * GRID_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(GRID_SIZE - 2));
        g.fillRect(0, 0, MAP_SIZE * GRID_SIZE, MAP_SIZE * GRID_SIZE);
        for (int row = 0; row < MAP_SIZE; row++) {
            for (int col = 0; col < MAP_SIZE; col++) {
                int n = area[row][col];
                int x = col * GRID_SIZE;
                int y = row * GRID_SIZE;

                if (n != 0) {
                    Color c = new Color(255 * n / maxCount, 255 * (maxCount - n) / maxCount, 0);
                    g.setColor(c);
                    g.fillRect(x, y, GRID_SIZE, GRID_SIZE);

                    g.setColor(new Color(255 - c.getRed(), 255 - c.getGreen(), 255));
                    g.drawString("" + n, x + 1, y + 14);
                }
            }
        }

        try {
            ImageIO.write(image, "png", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
