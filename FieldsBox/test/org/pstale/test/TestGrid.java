package org.pstale.test;

import static org.pstale.asset.loader.SMDTYPE.STAGE3D;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
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
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

/**
 * 测试九宫格网格
 * @author yanmaoyuan
 *
 */
public class TestGrid {

	static Logger log = Logger.getLogger(TestGrid.class);
	
	static int MAP_SIZE = 256;
	
	public static void main(String[] args) {

		AssetManager assetManager = new DesktopAssetManager();
		assetManager.registerLoader(SmdLoader.class, "smd");
		assetManager.registerLocator("D:/Priston Tale/PTCN3550/PTCN3550", FileLocator.class);

		STAGE3D stage = (STAGE3D)assetManager.loadAsset(new SmdKey("Field/forest/fore-1.smd", STAGE3D));
		Mesh mesh = buildCollisionMesh(stage);
		log.info("DONE");
		
		drawTri(mesh);
		
		BoundingBox bb = (BoundingBox)mesh.getBound();
		
		Vector3f max = new Vector3f();
		Vector3f min = new Vector3f();
		bb.getMin(min);
		bb.getMax(max);
		
		float unitX = (max.x - min.x) / MAP_SIZE;
		float unitY = (max.z - min.z) / MAP_SIZE;
		
		log.info("min:" + min + " max:" + max + " width:" + unitX + " height:" + unitY);
		
		FloatBuffer fb = (FloatBuffer)mesh.getBuffer(Type.Position).getData();
		int vCount = fb.limit() / 3;

		// 统计每个区域有多少个顶点，同时记录每个顶点落在那个区域了。
		int area[][] = new int[MAP_SIZE][MAP_SIZE];
		int vPosition[] = new int[vCount];
		int maxCount = 0;
		
		for(int i=0; i<vCount; i++) {
			float x = fb.get(i*3);
			float z = fb.get(i*3+2);
			
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
		IntBuffer ib = (IntBuffer)mesh.getBuffer(Type.Index).getData();
		int fCount = ib.limit() / 3;// 面数

		int[][] areaF = new int[MAP_SIZE][MAP_SIZE];
		// 临时变量，用来表示每个三角形
		Polygon tri;
		maxCount = 0;
		for(int i=0; i<fCount; i++) {
			// 连续添加3个点，构建一个三角形。
			tri = new Polygon();
			for(int j=0; j<3; j++) {
				int vIndex = ib.get(i*3 + j);
				
				// 取顶点的x、z坐标，映射到2D平面上。
				float x = fb.get(vIndex * 3);
				float z = fb.get(vIndex * 3 + 2);
				
				// 将顶点坐标放大256倍，以提高检测的精度
				tri.addPoint((int)(x * 256), (int)(z * 256));
			}
			
			// 获取三角形的包围盒
			Rectangle rect = tri.getBounds();
			
			// 下面要看看三角形落在那几个area中。
			float minX = (rect.x - rect.width) / 256f;
			float minY = (rect.y - rect.width) / 256f;
			Point p1 = getAreaPosition(minX, minY, min, unitX, unitY);
			float maxX = ( rect.x + 2 * rect.width ) / 256f;
			float maxY = ( rect.y + 2 * rect.height ) / 256f;
			Point p2 = getAreaPosition(maxX, maxY, min, unitX, unitY);
			
			float UnitX = 256f *  unitX;
			float UnitY = 256f *  unitY;
			// 遍历附近的每个矩形，看看和这个三角形是否相交。
//			for(int py = 0; py< MAP_SIZE; py++) {
//				for(int px = 0; px < MAP_SIZE; px++) {
			
			// 一个三角形可能跨好几个区域，用一个变量来衡量它。若guimo的值比较小，生成的数据地图上可能就会出现漏洞。
			int guimo = 16;
			for(int py = p2.y-guimo; py<= p2.y+guimo; py++) {
				for(int px = p1.x-guimo; px <= p1.x+guimo; px++) {
					
					// 避免下标越界
					if (px < 0 || py < 0 || px > MAP_SIZE-1 || py > MAP_SIZE- 1)
						continue;
					
					double boxX = 256f * (px * unitX + min.x);
					double boxY = 256f * (py * unitY + min.y);
					
					if (tri.intersects(boxX, boxY, UnitX, UnitY) ||
							tri.contains(boxX, boxY, UnitX, UnitY)) {
						areaF[py][px]++;
						
						if (maxCount < areaF[py][px]) {
							maxCount = areaF[py][px];
						}
					}
				}
			}
		}
		
		drawVCount(areaF, maxCount, "f.png");
	}
	
	static Point getAreaPosition(float x, float z, Vector3f min, final float unitW, final float unitH) {
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
			log.warn("col=" + col + " X=" + X);
			col = MAP_SIZE - 1;
		}
		
		if (row >= MAP_SIZE - 1) {
			log.warn("row=" + row + " Z=" + Z);
			row = MAP_SIZE - 1;
		}
		
		return new Point((int)col, (int)row);
	}
	
	static void drawTri(Mesh mesh) {
		BoundingBox bb = (BoundingBox)mesh.getBound();
		
		Vector3f max = new Vector3f();
		Vector3f min = new Vector3f();
		bb.getMin(min);
		bb.getMax(max);
		
		float unitX = (max.x - min.x) / MAP_SIZE;
		float unitY = (max.z - min.z) / MAP_SIZE;
		
		// 统计每个面落在哪个格子
		IntBuffer ib = (IntBuffer)mesh.getBuffer(Type.Index).getData();
		FloatBuffer fb = (FloatBuffer)mesh.getBuffer(Type.Position).getData();
		int fCount = ib.limit() / 3;// 面数

		
		// 准备画三角形
		int gridSize = 16;
		BufferedImage image = new BufferedImage(MAP_SIZE * gridSize, MAP_SIZE * gridSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)image.getGraphics();
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(14));
		g.fillRect(0, 0, MAP_SIZE * gridSize, MAP_SIZE * gridSize);
				
		// 临时变量，用来表示每个三角形
		Polygon tri;
		for(int i=0; i<fCount; i++) {
			// 连续添加3个点，构建一个三角形。
			tri = new Polygon();
			for(int j=0; j<3; j++) {
				int vIndex = ib.get(i*3 + j);
				
				// 取顶点的x、z坐标，映射到2D平面上。
				float x = fb.get(vIndex * 3);
				float z = fb.get(vIndex * 3 + 2);
				
				Point p1 = getAreaPosition(x, z, min, unitX, unitY);
				// 将顶点坐标放大256倍，以提高检测的精度
				tri.addPoint(p1.x * gridSize, p1.y * gridSize);
			}
			
			g.setColor(Color.red);
			g.draw(tri);
		}
		
		try {
			ImageIO.write(image, "png", new File("tri.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void drawVCount(int[][] area, int maxCount, String fileName) {
		// 把它画下来
		int gridSize = 16;
		BufferedImage image = new BufferedImage(MAP_SIZE * gridSize, MAP_SIZE * gridSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)image.getGraphics();
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(14));
		g.fillRect(0, 0, MAP_SIZE * gridSize, MAP_SIZE * gridSize);
		for(int row=0; row<MAP_SIZE; row++) {
			for(int col = 0; col<MAP_SIZE; col++) {
				int n = area[row][col];
				int x = col * gridSize;
				int y = row * gridSize;
				
				if (n != 0) {
					Color c = new Color(255*n/maxCount, 255*(maxCount-n)/maxCount, 0);
					g.setColor(c);
					g.drawString(""+n, x+1, y+14);
					g.drawRect(x, y, gridSize, gridSize);
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

			if ((m.MeshState & 0x0001) != 0 && m.Transparency < 0.2f && m.BlendType != 4) {
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
			
			if ( m.BlendType == 1 || m.BlendType == 4) {// ALPHA混色
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
}
