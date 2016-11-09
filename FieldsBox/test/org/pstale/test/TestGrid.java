package org.pstale.test;

import static org.pstale.asset.loader.SMDTYPE.STAGE3D;

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
import org.pstale.asset.loader.FileLocator;
import org.pstale.asset.loader.SmdKey;
import org.pstale.asset.loader.SmdLoader;
import org.pstale.asset.struct.MATERIAL;
import org.pstale.asset.struct.STAGE3D;
import org.pstale.asset.struct.STAGE_FACE;

import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import com.jme3.util.TempVars;

/**
 * 测试九宫格网格
 * 
 * @author yanmaoyuan
 * 
 */
public class TestGrid {

	static Logger log = Logger.getLogger(TestGrid.class);

	static int MAP_SIZE = 256;
	static int GRID_SIZE = 16;

	public static void main(String[] args) {

		TempVars tv = TempVars.get();

		AssetManager assetManager = new DesktopAssetManager();
		assetManager.registerLoader(SmdLoader.class, "smd");
		// assetManager.registerLocator("D:/Priston Tale/PTCN3550/PTCN3550", FileLocator.class);
		assetManager.registerLocator("I:/game/PTCN-RPT1.0", FileLocator.class);

		STAGE3D stage = (STAGE3D) assetManager.loadAsset(new SmdKey(
				"Field/forest/fore-1.smd", STAGE3D));
		Mesh mesh = buildCollisionMesh(stage);
		log.info("DONE");

		// 绘制三角形网格
		drawTri(mesh);

		// 包围盒
		BoundingBox bb = (BoundingBox) mesh.getBound();
		Vector3f max = tv.vect1;
		Vector3f min = tv.vect2;
		bb.getMin(min);
		bb.getMax(max);

		float unitX = (max.x - min.x) / MAP_SIZE;
		float unitY = (max.z - min.z) / MAP_SIZE;

		log.info("min:" + min + " max:" + max + " width:" + unitX + " height:"
				+ unitY);

		FloatBuffer fb = (FloatBuffer) mesh.getBuffer(Type.Position).getData();
		int vCount = fb.limit() / 3;

		// 统计每个区域有多少个顶点，同时记录每个顶点落在那个区域了。
		int area[][] = new int[MAP_SIZE][MAP_SIZE];
		int vPosition[] = new int[vCount];
		int maxCount = 0;

		for (int i = 0; i < vCount; i++) {
			float x = fb.get(i * 3);
			float z = fb.get(i * 3 + 2);

			Point p = getAreaPosition(x, z, min, unitX, unitY);

			vPosition[i] = p.y * MAP_SIZE + p.x;
			area[p.y][p.x]++;

			if (maxCount < area[p.y][p.x]) {
				maxCount = area[p.y][p.x];
			}
		}

		log.warn("格子中最大顶点数=" + maxCount);
		drawVCount(area, maxCount, "map.png");

		// 统计每个面落在哪个格子
		IntBuffer ib = (IntBuffer) mesh.getBuffer(Type.Index).getData();
		int fCount = ib.limit() / 3;// 面数

		int[][] areaF = new int[MAP_SIZE][MAP_SIZE];
		// 临时变量，用来表示每个三角形
		Vector2f[] tri = new Vector2f[] {tv.vect2d, tv.vect2d2, new Vector2f()};
		Vector2f[] box = new Vector2f[] { new Vector2f(), new Vector2f(), new Vector2f(), new Vector2f() };

		maxCount = 0;
		for (int i = 0; i < fCount; i++) {
			// 连续添加3个点，构建一个三角形。

			float boundsMinX = Float.MAX_VALUE;
			float boundsMinY = Float.MAX_VALUE;
			float boundsMaxX = -Float.MAX_VALUE;
			float boundsMaxY = -Float.MAX_VALUE;

			Point p = new Point();
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
				p = getAreaPosition(x, z, min, unitX, unitY);
			}

			// 下面要看看三角形落在那几个area中。
			Point p1 = getAreaPosition(boundsMinX, boundsMinY, min, unitX,
					unitY);
			Point p2 = getAreaPosition(boundsMaxX, boundsMaxY, min, unitX,
					unitY);

			// 遍历附近的每个矩形，看看和这个三角形是否相交。
			// 一个三角形可能跨好几个区域，用一个变量来衡量它。若guimo的值比较小，生成的数据地图上可能就会出现漏洞。
			for (int py = p1.y; py <= p2.y; py++) {
				for (int px = p1.x; px <= p2.x; px++) {

					// 避免下标越界
					// if (px < 0 || py < 0 || px >= MAP_SIZE || py >= MAP_SIZE)
					// continue;

					float boxX = min.x + px * unitX;
					float boxY = min.z + py * unitY;
					box[0].set(boxX, boxY);
					box[1].set(boxX + unitX, boxY);
					box[2].set(boxX + unitX, boxY + unitY);
					box[3].set(boxX, boxY + unitY);

					/**
					 * 判断这个方块和三角形是否相交。 只要矩形的任意一点落在三角形内，就认为他们相交。
					 */
					if ((p.x == px && p.y == py) || intersect(tri[0], tri[1], tri[2], box)) {
						areaF[py][px]++;
						if (maxCount < areaF[py][px]) {
							maxCount = areaF[py][px];
						}
					}
				}
			}
		}

		log.debug("格子中最大面数 = " + maxCount);
		drawVCount(areaF, maxCount, "f.png");

		tv.release();
	}

	static Point getAreaPosition(final float x, final float z, Vector3f min,
			final float unitW, final float unitH) {
		float X = x - min.x;
		float Z = z - min.z;

		float col = X / unitW;
		float row = Z / unitH;

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

	static void drawTri(Mesh mesh) {
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
		BufferedImage image = new BufferedImage(MAP_SIZE * GRID_SIZE, MAP_SIZE
				* GRID_SIZE, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();

		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(14));
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

	static void drawVCount(int[][] area, int maxCount, String fileName) {
		// 把它画下来
		BufferedImage image = new BufferedImage(MAP_SIZE * GRID_SIZE, MAP_SIZE
				* GRID_SIZE, BufferedImage.TYPE_INT_RGB);
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
					Color c = new Color(255 * n / maxCount, 255
							* (maxCount - n) / maxCount, 0);
					g.setColor(c);
					g.fillRect(x, y, GRID_SIZE, GRID_SIZE);
					
					g.setColor(new Color(255-c.getRed(), 255-c.getGreen(), 255));
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

	/**
	 * 生成碰撞网格：将透明的、不参与碰撞检测的面统统裁剪掉，只保留参于碰撞检测的面。
	 * 
	 * @return
	 */
	private static Mesh buildCollisionMesh(STAGE3D stage) {
		Mesh mesh = new Mesh();

		int materialCount = stage.materialGroup.materialCount;
		/**
		 * 根据材质的特诊来筛选参加碰撞检测的物体， 将被忽略的材质设置成null，作为一种标记。
		 */
		MATERIAL m;// 临时变量
		for (int mat_id = 0; mat_id < materialCount; mat_id++) {
			m = stage.materials[mat_id];

			if ((m.MeshState & 0x0001) != 0 && m.Transparency < 0.2f
					&& m.BlendType != 4) {
				continue;
			}

			if ((m.UseState & 0x1000) != 0) {
				// 这些面要参加碰撞检测
				continue;
			}

			if ((m.UseState & 0x07FF) != 0) {
				// 这些面被设置为可以直接穿透
				stage.materials[mat_id] = null;
				continue;
			}

			if (m.BlendType == 1 || m.BlendType == 4) {// ALPHA混色
				stage.materials[mat_id] = null;
				continue;
			}

			if (m.MapOpacity != 0 || m.Transparency != 0f) {
				// 透明的面不参加碰撞检测
				stage.materials[mat_id] = null;
				continue;
			}

			if (m.TextureType == 1) {
				// 帧动画也不纳入碰撞检测。比如火焰、飞舞的光点。
				stage.materials[mat_id] = null;
				continue;
			}

		}

		/**
		 * 统计有多少个要参加碰撞检测的面。
		 */
		int loc[] = new int[stage.nVertex];
		for (int i = 0; i < stage.nVertex; i++) {
			loc[i] = -1;
		}

		int fSize = 0;
		for (int i = 0; i < stage.nFace; i++) {
			STAGE_FACE face = stage.Face[i];
			if (stage.materials[face.v[3]] != null) {
				loc[face.v[0]] = face.v[0];
				loc[face.v[1]] = face.v[1];
				loc[face.v[2]] = face.v[2];

				fSize++;
			}
		}

		int vSize = 0;
		for (int i = 0; i < stage.nVertex; i++) {
			if (loc[i] > -1) {
				vSize++;
			}
		}

		// 记录新的顶点编号
		Vector3f[] v = new Vector3f[vSize];
		vSize = 0;
		for (int i = 0; i < stage.nVertex; i++) {
			if (loc[i] > -1) {
				v[vSize] = stage.Vertex[i].v;
				loc[i] = vSize;
				vSize++;
			}
		}

		// 记录新的顶点索引号
		int[] f = new int[fSize * 3];
		fSize = 0;
		for (int i = 0; i < stage.nFace; i++) {
			STAGE_FACE face = stage.Face[i];
			if (stage.materials[face.v[3]] != null) {
				f[fSize * 3] = loc[face.v[0]];
				f[fSize * 3 + 1] = loc[face.v[1]];
				f[fSize * 3 + 2] = loc[face.v[2]];
				fSize++;
			}
		}

		mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(v));
		mesh.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(f));

		mesh.updateBound();
		mesh.setStatic();

		return mesh;
	}

	/**
	 * 如果矩形的4个顶点有任何一个落在三角新abc内，就这个格子就需要对这个三角形进行碰撞检测。
	 * @param a
	 * @param b
	 * @param c
	 * @param box
	 * @return
	 */
	static boolean intersect(final Vector2f a, final Vector2f b,
			final Vector2f c, final Vector2f[] box) {
		for (int k = 0; k < 4; k++) {
			if (pointinTriangle(a, b, c, box[k])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断顶点P是否在由三角形ABC，使用重心坐标进行计算。
	 * 若向量AP = uAB + vAC，且0<=v<=1, 0<=u<=1, u + v < 1，则P在三角形ABC内部。
	 * 
	 * @param A
	 * @param B
	 * @param C
	 * @param P
	 * @return
	 */
	static boolean pointinTriangle(Vector2f A, Vector2f B, Vector2f C,
			Vector2f P) {
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
}
