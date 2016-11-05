package org.pstale.utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import com.jme3.util.TempVars;

public class MeshUtils {
	/**
	 * 计算法向量
	 * 
	 * @param mesh
	 */
	public static void computeNormals(Mesh mesh) {

		if (mesh.getBuffer(Type.Position) == null
				|| mesh.getBuffer(Type.Index) == null) {
			return;
		}
		
		TempVars tv = TempVars.get();

		Vector3f v1 = tv.vect1;
		Vector3f v2 = tv.vect2;
		Vector3f n = tv.vect3;

		Vector3f va = tv.vect4;
		Vector3f vb = tv.vect5;
		Vector3f vc = tv.vect6;

		Vector3f sum = tv.vect7;

		FloatBuffer fb = (FloatBuffer) mesh.getBuffer(Type.Position).getData();
		IntBuffer ib = (IntBuffer) mesh.getBuffer(Type.Index).getData();

		int[] f = ib.array();
		float[] v = fb.array();

		/**
		 * 分配内存
		 */
		Vector3f[] faceNormals = new Vector3f[f.length / 3];
		Vector3f[] vertNormals = new Vector3f[v.length / 3];

		// 计算每个面的法向量
		for (int i = 0; i < f.length / 3; i++) {

			int a = f[i * 3];
			int b = f[i * 3 + 1];
			int c = f[i * 3 + 2];

			va.x = v[a * 3];
			va.y = v[a * 3 + 1];
			va.z = v[a * 3 + 2];

			vb.x = v[b * 3];
			vb.y = v[b * 3 + 1];
			vb.z = v[b * 3 + 2];

			vc.x = v[c * 3];
			vc.y = v[c * 3 + 1];
			vc.z = v[c * 3 + 2];

			v1 = vb.subtract(va, v1);
			v2 = vc.subtract(va, v2);
			n = v1.cross(v2, n);

			faceNormals[i] = n.normalizeLocal();
		}

		/**
		 * 统计每个定点被多少个面共用，求平均值即其法线。
		 */
		int shared = 0;
		sum.zero();
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < f.length / 3; j++) {
				if (f[j * 3] == i || f[j * 3 + 1] == i || f[j * 3 + 2] == i) {
					sum.addLocal(faceNormals[j]);
					shared++;
				}
			}

			vertNormals[i] = sum.divide((-shared)).normalizeLocal();
			
			// 清零
			sum.zero();
			shared = 0;
		}

		mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(vertNormals));
		tv.release();
	}
}
