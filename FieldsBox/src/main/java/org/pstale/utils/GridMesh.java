package org.pstale.utils;

import java.awt.Point;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.jme3.bounding.BoundingBox;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;

/**
 * <h2>九宫格网格。</h2>
 * 定义一个256 * 256大小的矩阵，将地图划分成65536个格子。统计地图网格中所有的三角形，看看它们都落在
 * 哪个格子的范围内。一个3万面的地图，平均落在每个格子中只有4个面。少部分区域
 * 由于建筑比较复杂，可能会有100面左右。</br>
 * 
 * <h2>用途</h2>
 * 在进行碰撞检测时，可以根据玩家的坐标得知他落在哪个单元格中。取以这个格子为中心的九宫格，只对这九个 格子
 * 中的三角形进行碰撞检测。
 * 
 * @author yanmaoyuan
 * 
 */
public class GridMesh {
	
	static Logger log = Logger.getLogger(GridMesh.class);

	public final static int MAP_SIZE = 256;

	@SuppressWarnings("serial")
	public class FaceList extends ArrayList<Integer> {}
	
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
	
	private ArrayList<Integer>[][] area;
	
	public GridMesh (Mesh mesh) {
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
		
		getFaceList();
		log.debug("已生成GridMesh");
	}
	
	/**
	 * 计算每个格子中有哪些面
	 */
	public ArrayList<Integer>[][] getFaceList() {

		if (area == null) {
			area = new ArrayList[MAP_SIZE][MAP_SIZE];
			
			// 用来表示每个三角形的顶点坐标
			Vector2f[] tri = new Vector2f[] {new Vector2f(), new Vector2f(), new Vector2f()};
			// 用来表示每个矩形的顶点坐标
			Vector2f[] box = new Vector2f[] { new Vector2f(), new Vector2f(), new Vector2f(), new Vector2f()};
	
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
						 * 判断这个方块和三角形是否相交。 
						 * 若三角形的顶点落在这个矩形内，他们相交。
						 * 若矩形的任意一点落在三角形内，他们相交。
						 */
						if ((p[0].x == px && p[0].y == py) ||
								(p[1].x == px && p[1].y == py) || 
								(p[2].x == px && p[2].y == py) ||
								intersect(tri[0], tri[1], tri[2], box) ||
								intersect(tri, box)) {
							
							if (area[py][px] == null) {
								area[py][px] = new FaceList();
							}
							
							if (!area[py][px].contains(i)) {
								area[py][px].add(i);
							}
						}
					}
				}
			}
			
			/***
			 * 第二段算法，检查每个格子及其周围8格，合计有那些面要参加碰撞检测。
			 */
			ArrayList<Integer>[][] nineGridFaceList = new ArrayList[MAP_SIZE][MAP_SIZE];
			for(int row=0; row<MAP_SIZE; row++) {
				for(int col=0; col<MAP_SIZE; col++) {
					nineGridFaceList[row][col] = getNineGridFaceList(col, row);
				}
			}
			
			area = nineGridFaceList;
		}
		
		return area;
	}

	/**
	 * 根据坐标计算这个物体在哪个区域内
	 * @param x
	 * @param z
	 * @return
	 */
	public Point getAreaPosition(final float x, final float z) {
		// 不在此网格的边界内
		if (x < min.x || z < min.z || x > max.x || z > max.z) {
			return null;
		}
		
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
	 * 根据(x, y)坐标，附近九宫格中参加碰撞的面数。
	 * @param x
	 * @param y
	 * @return
	 */
	private ArrayList<Integer> getNineGridFaceList(final int x, final int y) {
		
		ArrayList<Integer> faces = new ArrayList<Integer>();
		
		// 取以xy为中心，附近9个格子。
		for(int i=y-1; i<=y+1; i++) {
			// 下标越界
			if (i < 0 || i>MAP_SIZE-1) {
				continue;
			}
			
			for(int j=x-1; j<=x+1; j++) {
				// 下标越界
				if (j<0 || j > MAP_SIZE -1) {
					continue;
				}
				
				// 这个格子中没有三角形
				ArrayList<Integer> fl = area[i][j];
				if (fl == null)
					continue;
				
				// 将面的索引添加到faces集合中
				for(int k=0; k<fl.size(); k++) {
					int e = fl.get(k);
					// 防止出现重复的面
					if (!faces.contains(e)) {
						faces.add(e);
					}
				}
			}
		}
		
		if (faces.size() == 0)
			return null;
		else
			return faces;
		
	}
	
	/**
	 * 根据Vector3f坐标，获得参加碰撞的面数
	 * @param position
	 * @return
	 */
	public ArrayList<Integer> getFaceList(final Vector3f position) {
		Point p = getAreaPosition(position.x, position.z);
		
		if (p != null) {
			return getFaceList(p.x, p.y);
		} else {
			return null;
		}
	}
	
	/**
	 * 根据(x, y)坐标，获取参加碰撞检测的面。
	 * @param x
	 * @param y
	 * @return
	 */
	public ArrayList<Integer> getFaceList(final int x, final int y) {
		if (x < 0 || y< 0 || x >= MAP_SIZE || y >= MAP_SIZE)
			return null;
		return area[y][x];
		
	}
	
	/**
	 * 如果矩形的4个顶点有任何一个落在三角新abc内，就这个格子就需要对这个三角形进行碰撞检测。
	 * @param a
	 * @param b
	 * @param c
	 * @param box
	 * @return
	 */
	private boolean intersect(final Vector2f a, final Vector2f b,
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
	 * 若向量AP = uAB + vAC，且0<=v<=1, 0<=u<=1, u + v <= 1，则P在三角形ABC内部。
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
	 * 判断矩形和三角形相交的另一种条件。
	 * 如果它们任意2条边相交，则它们相交
	 * @param tri
	 * @param box
	 * @return
	 */
	private boolean intersect(Vector2f[] tri, Vector2f[] box) {
		Vector2f a = new Vector2f();
		Vector2f b = new Vector2f();
		Vector2f c = new Vector2f();
		Vector2f d = new Vector2f();
		for(int i=0; i<3; i++) {
			
			a.set(tri[i]);
			if (i == 2) {
				b.set(tri[0]);
			} else {
				b.set(tri[i+1]);
			}
			
			for(int j=0; j<4; j++) {
				
				c.set(box[j]);
				if (j == 3) {
					d.set(box[0]);
				} else {
					d.set(box[j+1]);
				}
				
				if (lineCross(a, b, c, d))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * (a, b)为一条线段两端点 (c, d)为另一条线段的两端点
	 * 相交返回true, 不相交返回false
	 */
	private boolean lineCross(Vector2f a, Vector2f b, Vector2f c, Vector2f d) {
		double delta = determinant(b.x-a.x, c.x-d.x, b.y-a.y, c.y-d.y);  
		if ( delta<=(1e-6) && delta>=-(1e-6) )  // delta=0，表示两线段重合或平行  
			return false;
		
		double namenda = determinant(c.x-a.x, c.x-d.x, c.y-a.y, c.y-d.y) / delta;
		if ( namenda>1 || namenda<0 )
			return false;
		
		double miu = determinant(b.x-a.x, c.x-a.x, b.y-a.y, c.y-a.y) / delta;
		if ( miu>1 || miu<0 )
			return false;
		return true;
	}
	
	/**
	 * 行列式 
	 */
	private double determinant(double v1, double v2, double v3, double v4) {
		return v1 * v4 - v2 * v3;
	}
	

	public Vector3f getMax() {
		return max;
	}

	public Vector3f getMin() {
		return min;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getUnitX() {
		return unitX;
	}

	public float getUnitY() {
		return unitY;
	}
}
