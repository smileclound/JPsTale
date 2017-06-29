package com.jme3.scene.plugins.inx;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.plugins.inx.base.AbstractLoader;
import com.jme3.scene.plugins.inx.base.ByteReader;
import com.jme3.scene.plugins.inx.mesh.DrzFaceTextureId;
import com.jme3.scene.plugins.inx.mesh.DrzLight;
import com.jme3.scene.plugins.inx.mesh.DrzMaterials;
import com.jme3.scene.plugins.inx.mesh.DrzMesh;
import com.jme3.scene.plugins.inx.mesh.DrzSubMesh;
import com.jme3.util.BufferUtils;

/**
 * 用于导入角色、怪物、NPC等模型的数据
 * 
 * @author yanmaoyuan
 * 
 */
public class ImportStage extends ByteReader {

	boolean useLightMap = true;
	
	AbstractLoader loader;
	Node rootNode = new Node();
	Material mats[];

	DrzMesh mesh;

	public ImportStage(AbstractLoader loader) {
		this.loader = loader;
	}

	public synchronized void loadScene(InputStream inputStream)
			throws IOException {
		int length = inputStream.available();

		if (length <= 67083) {
			System.out.println("Error: can't read inx-file (invalid file content)\n");
			return;
		}

		getByteBuffer(inputStream);

		String head = getString();
		System.out.println("smd header:" + head);
		
		if ("SMD Stage data Ver 0.72".equals(head)) {// 地图
			load();
		} else if ("SMD Model data Ver 0.62".equals(head)){// 带动画的模型
			
		} else {
			throw new RuntimeException("invalid smd file");
		}

	}

	public void load() {

		mesh = new DrzMesh();
		mesh.MeshType = 999;// STAGE

		DrzSubMesh stageMesh = new DrzSubMesh();

		// Parse Materials
		buffer.position(28);
		mesh.SubMaterialNum = getInt();
		// Read Vertex, Face etc
		buffer.position(262752);
		int VertexNum = getInt();
		int FaceNum = getInt();
		int UvVertexNum = getInt();
		int LightNum = getInt();

		// Parse Materials
		int MaterialOffset = 262904;
		int MaterialByteSize = ParseMaterials(MaterialOffset);

		// parse vertices
		int vertoffset = MaterialOffset + MaterialByteSize;

		// Reset BoundingBox
		stageMesh.BoundBox.SetMaxValues();

		stageMesh.mVertexList = new ArrayList<Vector3f>();
		stageMesh.mVertexColorList = new ArrayList<ColorRGBA>();
		for (int i = 0; i < VertexNum; i++) {

			int offset = vertoffset + (i * 28) + 8;
			buffer.position(offset);
			float x = getPTDouble();
			float y = getPTDouble();
			float z = getPTDouble();

			Vector3f cvert = new Vector3f();
			cvert = new Vector3f(-z, y, -x);

			// Resize BoundBox
			stageMesh.BoundBox.ResizeBoxByValue(cvert);

			stageMesh.mVertexList.add(cvert);

			offset = vertoffset + (i * 28) + 20;
			buffer.position(offset);
			float r = getShort() / 256f;
			float g = getShort() / 256f;
			float b = getShort() / 256f;
			float a = getShort() / 256f;
			ColorRGBA col = new ColorRGBA(r, g, b, a);
			stageMesh.mVertexColorList.add(col);
		}

		// faces & uvvertex
		int faceoffset = vertoffset + VertexNum * 28;
		stageMesh.mFaceList = new ArrayList<DrzFaceTextureId>();

		// UvVertex
		int uvoffset = vertoffset + (VertexNum * 28) + (FaceNum * 28);
		stageMesh.mUvVertexList1 = new ArrayList<Vector2f>();
		stageMesh.mUvVertexList2 = new ArrayList<Vector2f>();

		int uvvertcount = 0;

		// do read
		for (int facecount = 0; facecount < FaceNum; facecount++) {
			// add the face
			buffer.position(faceoffset + (28 * facecount) + 8);
			int a = getUnsignedShort();
			int b = getUnsignedShort();
			int c = getUnsignedShort();
			int mat_id = getUnsignedShort();
			stageMesh.mFaceList
					.add(new DrzFaceTextureId(a, b, c, mat_id));

			if (getUnsignedInt() == 0) {
				stageMesh.mUvVertexList1.add(new Vector2f(0.0f, 0.0f));
				stageMesh.mUvVertexList1.add(new Vector2f(0.0f, 0.0f));
				stageMesh.mUvVertexList1.add(new Vector2f(0.0f, 0.0f));

				stageMesh.mUvVertexList2.add(new Vector2f(0.0f, 0.0f));
				stageMesh.mUvVertexList2.add(new Vector2f(0.0f, 0.0f));
				stageMesh.mUvVertexList2.add(new Vector2f(0.0f, 0.0f));
			} else {
				Vector2f UvVert1 = new Vector2f(getFloat(uvoffset
						+ (uvvertcount * 32) + 0), 1 - getFloat(uvoffset
						+ (uvvertcount * 32) + 12));
				Vector2f UvVert2 = new Vector2f(getFloat(uvoffset
						+ (uvvertcount * 32) + 4), 1 - getFloat(uvoffset
						+ (uvvertcount * 32) + 16));
				Vector2f UvVert3 = new Vector2f(getFloat(uvoffset
						+ (uvvertcount * 32) + 8), 1 - getFloat(uvoffset
						+ (uvvertcount * 32) + 20));

				stageMesh.mUvVertexList1.add(UvVert1);
				stageMesh.mUvVertexList1.add(UvVert2);
				stageMesh.mUvVertexList1.add(UvVert3);

				// no defintion for mapping channel?
				buffer.position(uvoffset + (uvvertcount * 32) + 28);
				if (getUnsignedInt() == 0) {
					stageMesh.mUvVertexList2.add(new Vector2f(0.0f, 0.0f));
					stageMesh.mUvVertexList2.add(new Vector2f(0.0f, 0.0f));
					stageMesh.mUvVertexList2.add(new Vector2f(0.0f, 0.0f));
				} else {
					uvvertcount++;

					UvVert1 = new Vector2f(getFloat(uvoffset + (uvvertcount * 32) + 0), 1 - getFloat(uvoffset + (uvvertcount * 32) + 12));
					UvVert2 = new Vector2f(getFloat(uvoffset + (uvvertcount * 32) + 4), 1 - getFloat(uvoffset + (uvvertcount * 32) + 16));
					UvVert3 = new Vector2f(getFloat(uvoffset + (uvvertcount * 32) + 8), 1 - getFloat(uvoffset + (uvvertcount * 32) + 20));

					stageMesh.mUvVertexList2.add(UvVert1);
					stageMesh.mUvVertexList2.add(UvVert2);
					stageMesh.mUvVertexList2.add(UvVert3);
				}

				uvvertcount++;
			}
		}

		mesh.mLightList = new ArrayList<DrzLight>();
		int lightoffset = vertoffset + (VertexNum * 28) + (FaceNum * 28)
				+ (UvVertexNum * 32);
		for (int i = 0; i < LightNum; i++) {
			DrzLight Light = new DrzLight();

			buffer.position(lightoffset + i * 28);
			Light.LightFlag = getUnsignedInt();

			buffer.position(lightoffset + i * 28 + 4);
			float x = getPTDouble();
			float y = getPTDouble();
			float z = getPTDouble();
			Light.mLightPositon = new Vector3f(-z, y, -x);

			buffer.position(lightoffset + i * 28 + 16);
			Light.LightRange = getUnsignedInt() / 64f / 256f;

			buffer.position(lightoffset + i * 28 + 20);
			float r = getPTLightColor();
			float g = getPTLightColor();
			float b = getPTLightColor();
			Light.mLightColor = new Vector3f(r, g, b);

			mesh.mLightList.add(Light);
			
			// 添加光源
			PointLight light = new PointLight();
			light.setPosition(Light.mLightPositon);
			light.setRadius(Light.LightRange);
			light.setColor(new ColorRGBA(r, g, b, 1));
			rootNode.addLight(light);
		}

		// region Addional Stuff

		// divide vector3
		Vector3f divided = stageMesh.BoundBox.BoxMax.subtract(stageMesh.BoundBox.BoxMin).mult(0.5f);
		Vector3f meshCenter = stageMesh.BoundBox.BoxMin.add(divided);

		Vector3f Pos1 = new Vector3f(stageMesh.BoundBox.BoxMin.x, 0, stageMesh.BoundBox.BoxMin.z);
		Vector3f Pos2 = new Vector3f(stageMesh.BoundBox.BoxMin.x, 0, stageMesh.BoundBox.BoxMax.z);
		Vector3f Pos3 = new Vector3f(stageMesh.BoundBox.BoxMax.x, 0, stageMesh.BoundBox.BoxMax.z);
		Vector3f Pos4 = new Vector3f(stageMesh.BoundBox.BoxMax.x, 0, stageMesh.BoundBox.BoxMin.z);

		meshCenter.x -= 500;
		Vector3f width = Pos1.subtract(Pos4);
		
		rootNode.setUserData("min_x", stageMesh.BoundBox.BoxMin.x);
		rootNode.setUserData("min_z", stageMesh.BoundBox.BoxMin.y);
		rootNode.setUserData("min_y", stageMesh.BoundBox.BoxMin.z);
		rootNode.setUserData("max_x", stageMesh.BoundBox.BoxMax.x);
		rootNode.setUserData("max_y", stageMesh.BoundBox.BoxMax.y);
		rootNode.setUserData("max_z", stageMesh.BoundBox.BoxMax.z);

		// Add the Mesh to the List
		mesh.subMeshList.add(stageMesh);

		//onlyOneMesh(stageMesh);
		createMesh(stageMesh);
	}

	private int ParseMaterials(final int offset) {
		buffer.position(offset);

		// Output Materials
		int matt = 0;

		int buf = offset;

		if (mesh.SubMaterialNum > 0) {
			mats = new Material[mesh.SubMaterialNum];

			for (int i = 0; i < mesh.SubMaterialNum; i++) {
				int submatbuf = buf + matt;

				DrzMaterials drzMat = new DrzMaterials();
				drzMat.MatIndex = i;

				buffer.position(submatbuf);
				drzMat.MatNameIndex = getInt();

				// Copy raw data
				buffer.position(submatbuf);
				buffer.get(drzMat.MaterialRawInfo, 0, 320);

				// Get Mat TwoSided
				buffer.position(submatbuf + 124);
				if (getInt() == 1) {
					drzMat.IsDoubleSided = true;
				} else
					drzMat.IsDoubleSided = false;

				// Get Mat-Diffuse
				buffer.position(submatbuf + 132);
				drzMat.Diffuse = getVector3f();

				// Get Mat Transparency
				drzMat.Transparency = getFloat();

				// Get Mat Selfillum
				drzMat.Selfillum = getFloat();

				// Mat solid
				buffer.position(submatbuf + 168);
				if (getInt() == 1) {
					drzMat.IsSolidState = true;
				}

				// Read using texture names.
				buffer.position(submatbuf + 320);
				int texture_names_len = getInt();
				if (texture_names_len > 0) {
					int textture_name_offset = 0;
					drzMat.texture_name_count = 0;
					do {
						buffer.position(submatbuf + 324 + textture_name_offset);
						String TextureName = getString();

						if (TextureName.length() > 0) {
							int start = TextureName.lastIndexOf("\\") + 1;
							String path = TextureName.substring(start);

							if (drzMat.texture_name_count == 0) {
								drzMat.TextureUniqueIdent1 = path;
							} else {
								drzMat.TextureUniqueIdent2 = path;
							}

							drzMat.texture_name_count++;
						}

						textture_name_offset += TextureName.length() + 1;
					} while (textture_name_offset < texture_names_len);

					if (drzMat.texture_name_count > 2)
						System.err.println("Error. There is a Texture Def with more the 2 textures");
				}

				buffer.position(submatbuf + 4);
				int ExtentMaterialInfoNum = getInt();

				// Extendet material info?
				if (ExtentMaterialInfoNum > 0) {
					// Addional MaterialInfo
					for (int c = 1; c < ExtentMaterialInfoNum; c++) {
						// SHOWERROR( "Material: " + tostr(i) +
						// "\nMaterialName:" +
						// ppMesh->mMaterials[i].MaterialName + "\nMapName: " +
						// ppMesh->mMaterials[i].MapName + "\nTexturePath: " +
						// ppMesh->mMaterials[i].mTextureName, __FILE__,
						// __LINE__ );
						// std::String cnt_mapname = tostr( mapName( submatbuf,
						// c ) );

						// int bid = c + (readbool( submatbuf, 108 ) ? 1 : 0);
						// std::String cnt_bitmap = tostr( mapBitmap( submatbuf,
						// bid ) );
					}
				}

				buffer.position(submatbuf + 304);
				if (getInt() != 0)
					System.out
							.println("WARNING: FIND MATOFFSET 304 poitive value");

				mesh.MeshMaterials.put(i, drzMat);

				buffer.position(submatbuf + 320);
				matt += (324 + getInt());
			}
		}
		return matt;
	}

	private Material createMaterial(DrzMaterials drzMat) {
		Material mat = new Material(loader.manager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.White);
		mat.setFloat("AlphaDiscardThreshold", 0.01f);
		RenderState rs = mat.getAdditionalRenderState();
		
		if (drzMat.IsDoubleSided)
			rs.setFaceCullMode(FaceCullMode.Off);
		
		if (drzMat.texture_name_count == 0) {
			rs.setFaceCullMode(FaceCullMode.FrontAndBack);
		} else {
			for( int n=0; n<drzMat.texture_name_count; n++) {
				switch(n) {
				case 0:
					mat.setTexture("ColorMap", loader.createTexture(drzMat.TextureUniqueIdent1));
					break;
				case 1:
					//TODO 只对有LightingMap的材质添加Light Map
					if (drzMat.TextureUniqueIdent2.contains("LightingMap")) {
						if (useLightMap) {
							mat.setBoolean("SeparateTexCoord", true);
							mat.setTexture("LightMap", loader.createTexture(drzMat.TextureUniqueIdent2));
						} else {
							mat.setTexture("ColorMap", loader.createTexture(drzMat.TextureUniqueIdent2));
						}
						
					}
					break;
				default:
					break;
				}
			}
		}
		
		return mat;
	}
	
	
	/**
	 * 
	 * @param subMesh
	 * @param mFaceNum
	 * @param mTVFaceNum
	 */
	void onlyOneMesh(DrzSubMesh subMesh) {

		Mesh mesh = new Mesh();
		Geometry geom = new Geometry(subMesh.NodeName, mesh);
		rootNode.attachChild(geom);
		
		geom.setMaterial(loader.getDefaultMaterial());
		
		// 顶点
		Vector3f[] vertex = subMesh.mVertexList.toArray(new Vector3f[] {});// 顶点
		mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertex));
		
		// 面索引
		int mFaceNum = subMesh.mFaceList.size();
		int f[] = new int[mFaceNum * 3];
		for(int inx=0; inx<mFaceNum; inx++) {
			DrzFaceTextureId id = subMesh.mFaceList.get(inx);
			f[inx * 3] = id.a;
			f[inx * 3 + 1] = id.b;
			f[inx * 3 + 2] = id.c;
			
			int mat_id = id.material_id;
			assert mat_id <= this.mesh.SubMaterialNum;
		}
		mesh.setBuffer(Type.Index, 3, f);
		
		// UV 纹理映射
		Vector2f[] texCoord = subMesh.mUvVertexList1.toArray(new Vector2f[] {});
		mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
		
		mesh.setStatic();
		mesh.updateBound();
		mesh.updateCounts();
	}

	private void createMesh(DrzSubMesh submesh) {

		if (submesh.mFaceList == null)
			return;
		if (submesh.mFaceList.size() < 1)
			return;

		for (int mat_id = 0; mat_id < mesh.SubMaterialNum; mat_id++) {
			// 统计材质为mat_id的面一共有多少个
			int size = 0;
			for (int i = 0; i < submesh.mFaceList.size(); i++) {
				if (submesh.mFaceList.get(i).material_id != mat_id)
					continue;
				size++;
			}
			if (size < 1)
				continue;

			// 生成材质
			DrzMaterials dMat = mesh.MeshMaterials.get(mat_id);
			Material mat = createMaterial(dMat);
			
			Mesh mesh = new Mesh();
			Geometry geom = new Geometry(submesh.NodeName + "#" + mat_id, mesh);
			geom.setMaterial(mat);
			rootNode.attachChild(geom);

			
			Vector3f[] position = new Vector3f[size * 3];
			int[] f = new int[size * 3];
			Vector2f[] uv1 = new Vector2f[size * 3];
			Vector2f[] uv2 = new Vector2f[size * 3];

			int index = 0;
			// Prepare MeshData
			for (int i = 0; i < submesh.mFaceList.size(); i++) {
				DrzFaceTextureId id = submesh.mFaceList.get(i);
				// Check the MaterialIndex
				if (id.material_id != mat_id)
					continue;

				// 顶点 VERTEX
				position[index * 3 + 0] = submesh.mVertexList.get(id.a);
				position[index * 3 + 1] = submesh.mVertexList.get(id.b);
				position[index * 3 + 2] = submesh.mVertexList.get(id.c);

				// 面 FACE
				if (i < submesh.mFaceList.size()) {
					f[index * 3 + 0] = index * 3 + 0;
					f[index * 3 + 1] = index * 3 + 1;
					f[index * 3 + 2] = index * 3 + 2;
				}

				// 纹理映射 UV1
				if (submesh.mUvVertexList1.size() > i * 3) {
					// UvCoords
					uv1[index * 3 + 0] = submesh.mUvVertexList1.get(i * 3);
					uv1[index * 3 + 1] = submesh.mUvVertexList1.get(i * 3 + 1);
					uv1[index * 3 + 2] = submesh.mUvVertexList1.get(i * 3 + 2);
				}
				
				// 纹理映射 UV2
				if (submesh.mUvVertexList2.size() > i * 3) {
					// UvCoords
					uv2[index * 3 + 0] = submesh.mUvVertexList2.get(i * 3);
					uv2[index * 3 + 1] = submesh.mUvVertexList2.get(i * 3 + 1);
					uv2[index * 3 + 2] = submesh.mUvVertexList2.get(i * 3 + 2);
				}

				index++;
			}

			mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(position));
			mesh.setBuffer(Type.Index, 3, f);
			// DiffuseMap UV
			mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv1));
			// LightMap UV
			if (submesh.mUvVertexList2.size() > 0) {
				if (useLightMap) {
					mesh.setBuffer(Type.TexCoord2, 2, BufferUtils.createFloatBuffer(uv2));
				} else {
					mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv2));
				}
			}

			mesh.setStatic();
			mesh.updateBound();
			mesh.updateCounts();
		}
	}

}
