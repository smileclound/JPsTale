package org.pstale.asset.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.pstale.asset.anim.DrzAnimation;
import org.pstale.asset.base.AbstractLoader;
import org.pstale.asset.mesh.DrzFaceTextureId;
import org.pstale.asset.mesh.DrzLight;
import org.pstale.asset.mesh.DrzMaterials;
import org.pstale.asset.mesh.DrzMesh;
import org.pstale.asset.mesh.DrzSubMesh;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

public class SmdLoader extends AbstractLoader {
	protected Node rootNode;

	@Override
	public Object parse(InputStream inputStream) throws IOException {

		mesh = new DrzMesh();

		if (key instanceof SmdKey) {
			SmdKey k = (SmdKey) key;
			mesh.mAnimation = k.getDrzAnimation();
			rootNode = k.getRootNode();
		} else {
			mesh.mAnimation = new DrzAnimation();
			mesh.mAnimation.SetSubAnimtionNum(1);// TODO ?????不知道有用没用
		}
		
		Node node = null;
		
		getByteBuffer(inputStream);

		String head = getString();
		
		if ("SMD Stage data Ver 0.72".equals(head)) {// 地图
			if (rootNode == null)
				rootNode = new Node("SMD Stage");
			loadStage();
			node = rootNode;
		} else if ("SMD Model data Ver 0.62".equals(head)){// 带动画的模型
			if (rootNode == null)
				rootNode = new Node("SMD Model");
			loadModel();
			
			/**
			// TODO 暂不支持动画
			MotionControl mc = rootNode.getControl(MotionControl.class);
			if (mc != null)rootNode.removeControl(mc);
			AnimControl ac = rootNode.getControl(AnimControl.class);
			if (ac != null)rootNode.removeControl(ac);
			SkeletonControl sk = rootNode.getControl(SkeletonControl.class);
			if (sk != null)rootNode.removeControl(sk);
			
			*/
			
			node = rootNode;
		} else {
			throw new RuntimeException("invalid smd file");
		}
		
		return node;
	}
	
	boolean useLightMap = true;
	Material mats[];
	DrzMesh mesh;
	
	/************
	 * 加载Stage模型
	 */
	protected synchronized void loadStage() {

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
		int MaterialByteSize = parseStageMaterials(MaterialOffset);

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
		createStageMesh(stageMesh);
	}

	/**
	 * 读取材质数据
	 * @param offset
	 * @return
	 */
	private int parseStageMaterials(final int offset) {
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
							} else if (drzMat.texture_name_count == 1){
								drzMat.TextureUniqueIdent2 = path;
							} else {
								System.err.println("Unknown texture: " + drzMat.texture_name_count + " : " + path);
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
					System.out.println("WARNING: FIND MATOFFSET 304 poitive value");

				mesh.MeshMaterials.put(i, drzMat);

				buffer.position(submatbuf + 320);
				matt += (324 + getInt());
			}
		}
		return matt;
	}

	private Material createStageMaterial(DrzMaterials drzMat) {
		Material mat = new Material(manager, "Common/MatDefs/Misc/Unshaded.j3md");
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
					mat.setTexture("ColorMap", createTexture(drzMat.TextureUniqueIdent1));
					break;
				case 1:
					//TODO 只对有LightingMap的材质添加Light Map
					if (drzMat.TextureUniqueIdent2.contains("LightingMap")) {
						if (useLightMap) {
							mat.setBoolean("SeparateTexCoord", true);
							mat.setTexture("LightMap", createTexture(drzMat.TextureUniqueIdent2));
						} else {
							mat.setTexture("ColorMap", createTexture(drzMat.TextureUniqueIdent2));
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
		
		geom.setMaterial(getDefaultMaterial());
		
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

	private void createStageMesh(DrzSubMesh submesh) {

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
			Material mat = createStageMaterial(dMat);
			
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
	
	
	
	
	
	
	
	
	/**********
	 * 加载角色模型
	 */
	/**
	 * 加载模型的网格数据
	 * 
	 * @throws IOException
	 */
	public synchronized boolean loadModel() throws IOException {
		// Parse Materials
		buffer.position(24);
		int meshNum = getInt();
		buffer.position(28);
		mesh.SubMaterialNum = getInt();

		// Parse Materials
		int MaterialOffset = 556 + (meshNum * 40) + 88;
		int MaterialByteSize = ParseModelMaterials(MaterialOffset);

		// read meshes
		int meshOffset = MaterialOffset + MaterialByteSize;
		for (int i = 0; i < meshNum; i++) {
			DrzSubMesh subMesh = new DrzSubMesh();

			// Check header
			buffer.position(meshOffset);
			if (!((buffer.get() == 'D') || (buffer.get() == 'C') || (buffer.get() == 'B'))) {
				System.out.println("Invalid mesh header. mesh(" + i + ")\n");
				return false;
			}

			// check if model has bones
			boolean hasBones = false;
			buffer.position(meshOffset + 16);
			if (getInt() != 0)
				hasBones = true;

			// Read Vertex, Face etc
			buffer.position(meshOffset + 80);
			int mFaceNum = getInt();
			int mVertexNum = getInt();
			getInt();// mUnkonwnNum
			int mTVFaceNum = getInt();

			buffer.position(meshOffset + 172);
			subMesh.NodeName = getString();
			buffer.position(meshOffset + 204);
			subMesh.NodeParentName = getString();

			// ParseMatrix(meshOffset + 240, subMesh.meshMatrix,
			// subMesh.mTMRotation, subMesh.mTMPos, subMesh.mTMScale);

			// Read vertices
			if (mVertexNum > 0) {
				buffer.position();
				if (ParseVertexData(meshOffset + 2236, mVertexNum, mFaceNum, mTVFaceNum, hasBones, subMesh) == false)
					return false;
			}

			// Read Faces & MatId
			if (mFaceNum > 0) {
				ParseFaceBlock(meshOffset + 2236 + (mVertexNum * 24), subMesh,
						mFaceNum);
			}

			// Read tverts && tvfaces
			if (mTVFaceNum > 0) {
				ParseUVFaceVerticesBlock(meshOffset + 2236 + (mVertexNum * 24)
						+ (mFaceNum * 36), subMesh, mTVFaceNum);
			}

			// Set AniKeyNums
			buffer.position(meshOffset + 684);
			// int mNumRot = getUnsignedInt();
			// int mNumPos = getUnsignedInt();
			// int mNumScl = getUnsignedInt();
			// int numunk = mNumRot;

			// Set position of the next mesh
			meshOffset += 2236;
			meshOffset += mVertexNum * 24;
			meshOffset += mFaceNum * 36;
			meshOffset += mTVFaceNum * 32;

			if (hasBones) {
				meshOffset += mVertexNum * 32; // physique
			}

			// Add the Mesh to the List
			mesh.subMeshList.add(subMesh);

			// printMesh(subMesh, mVertexNum, mFaceNum, mTVFaceNum);

			createModelMesh(subMesh);
		}

		return true;
	}

	private int ParseModelMaterials(final int offset) {
		buffer.position(offset);

		// Output Materials
		int matt = 0;

		int buf = offset;

		if (mesh.SubMaterialNum > 0) {

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
	
	private Material createModelMaterial(DrzMaterials drzMat) {
		Material mat = new Material(manager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.White);
		mat.setFloat("AlphaDiscardThreshold", 0.01f);
		RenderState rs = mat.getAdditionalRenderState();

		if (drzMat.IsDoubleSided)
			rs.setFaceCullMode(FaceCullMode.Off);

		if (drzMat.texture_name_count == 0) {
			rs.setFaceCullMode(FaceCullMode.FrontAndBack);
		} else {
			mat.setTexture("ColorMap", createTexture(drzMat.TextureUniqueIdent1));
		}

		return mat;
	}
	
	/**
	 * 打印网格数据
	 * 
	 * @param subMesh
	 * @param mVertexNum
	 * @param mFaceNum
	 * @param mTVFaceNum
	 */
	void printMesh(DrzSubMesh subMesh, int mVertexNum, int mFaceNum, int mTVFaceNum) {

		AnimControl ac = rootNode.getControl(AnimControl.class);
		Skeleton ske = ac.getSkeleton();

		Mesh mesh = new Mesh();

		Geometry geom = new Geometry(subMesh.NodeName, mesh);
		rootNode.attachChild(geom);

		// geom.setMaterial(loader.getDefaultMaterial());

		assert mVertexNum == subMesh.mBoneAssignmentList.size();

		// 顶点
		Vector3f[] vertex = subMesh.mVertexList.toArray(new Vector3f[] {});// 顶点
		mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertex));

		// 面索引
		int f[] = new int[mFaceNum * 3];
		for (int inx = 0; inx < mFaceNum; inx++) {
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
		mesh.setBuffer(Type.TexCoord, 2,
				BufferUtils.createFloatBuffer(texCoord));

		// 顶点法线
		Vector3f[] vnorm = new Vector3f[mVertexNum];
		for (int inx = 0; inx < mVertexNum; inx++) {
			vnorm[inx] = new Vector3f();
		}
		mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(vnorm));

		// 骨骼
		byte bi[] = new byte[mVertexNum * 4];// bone index
		float bw[] = new float[mVertexNum * 4];// bone weight
		for (int inx = 0; inx < mVertexNum; inx++) {
			String bone = subMesh.mBoneAssignmentList.get(inx);
			int targetBoneIndex = ske.getBoneIndex(bone);

			bi[inx * 4] = (byte) targetBoneIndex;
			bi[inx * 4 + 1] = (byte) targetBoneIndex;
			bi[inx * 4 + 2] = (byte) targetBoneIndex;
			bi[inx * 4 + 3] = (byte) targetBoneIndex;

			bw[inx * 4] = 1;
			bw[inx * 4 + 1] = 0;
			bw[inx * 4 + 2] = 0;
			bw[inx * 4 + 3] = 0;
		}

		// 绑定动画
		mesh.setBuffer(Type.BoneIndex, 4, bi);
		mesh.setBuffer(Type.BoneWeight, 1, bw);
		mesh.setBuffer(Type.HWBoneIndex, 4, bi);
		mesh.setBuffer(Type.HWBoneWeight, 1, bw);
		mesh.setMaxNumWeights(1);
		mesh.generateBindPose(true);

		mesh.setStatic();
		mesh.updateBound();
		mesh.updateCounts();
	}

	void createModelMesh(DrzSubMesh submesh) {

		if (submesh.mFaceList == null)
			return;
		if (submesh.mFaceList.size() < 1)
			return;

		AnimControl ac = rootNode.getControl(AnimControl.class);
		Skeleton ske = null;
		if (ac != null) {
			ske = ac.getSkeleton();
		}

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
			Material mat = createModelMaterial(dMat);

			Mesh mesh = new Mesh();
			Geometry geom = new Geometry(submesh.NodeName + "#" + mat_id, mesh);
			rootNode.attachChild(geom);
			geom.setMaterial(mat);

			Vector3f[] position = new Vector3f[size * 3];
			int[] f = new int[size * 3];
			Vector2f[] uv = new Vector2f[size * 3];
			Vector3f[] vn = new Vector3f[size * 3];
			byte bi[] = new byte[size * 12];
			Vector4f[] bw = new Vector4f[size * 3];

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

				// 纹理映射 UV
				if (submesh.mUvVertexList1.size() > i * 3) {
					// UvCoords
					uv[index * 3 + 0] = submesh.mUvVertexList1.get(i * 3);
					uv[index * 3 + 1] = submesh.mUvVertexList1.get(i * 3 + 1);
					uv[index * 3 + 2] = submesh.mUvVertexList1.get(i * 3 + 2);
				}

				// 顶点法线
				vn[index * 3 + 0] = new Vector3f();
				vn[index * 3 + 1] = new Vector3f();
				vn[index * 3 + 2] = new Vector3f();

				// 骨骼
				if (ske != null) {
					String boneA = submesh.mBoneAssignmentList.get(id.a);
					String boneB = submesh.mBoneAssignmentList.get(id.b);
					String boneC = submesh.mBoneAssignmentList.get(id.c);
					byte biA = (byte) ske.getBoneIndex(boneA);
					byte biB = (byte) ske.getBoneIndex(boneB);
					byte biC = (byte) ske.getBoneIndex(boneC);

					// 骨骼索引
					bi[index * 12 + 0] = biA;
					bi[index * 12 + 1] = biA;
					bi[index * 12 + 2] = biA;
					bi[index * 12 + 3] = biA;
					bi[index * 12 + 4] = biB;
					bi[index * 12 + 5] = biB;
					bi[index * 12 + 6] = biB;
					bi[index * 12 + 7] = biB;
					bi[index * 12 + 8] = biC;
					bi[index * 12 + 9] = biC;
					bi[index * 12 + 10] = biC;
					bi[index * 12 + 11] = biC;

					// 骨骼权重
					bw[index * 3 + 0] = new Vector4f(1, 0, 0, 0);
					bw[index * 3 + 1] = new Vector4f(1, 0, 0, 0);
					bw[index * 3 + 2] = new Vector4f(1, 0, 0, 0);
				}
				index++;
			}

			mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(position));
			mesh.setBuffer(Type.Index, 3, f);
			mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(vn));
			mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv));

			// 骨骼
			if (ske != null) {
				mesh.setBuffer(Type.BoneIndex, 4, bi);
				mesh.setBuffer(Type.BoneWeight, 1, BufferUtils.createFloatBuffer(bw));
				mesh.setBuffer(Type.HWBoneIndex, 4, bi);
				mesh.setBuffer(Type.HWBoneWeight, 1, BufferUtils.createFloatBuffer(bw));
				mesh.setMaxNumWeights(1);
				mesh.generateBindPose(true);
			}

			mesh.setStatic();
			mesh.updateBound();
			mesh.updateCounts();
		}
	}

	/**
	 * Calculate face normals
	 * 
	 * @param faceNormals
	 * @param vertexs
	 * @param indices
	 */
	protected void calculateFaceNormals(Vector3f[] faceNormals,
			Vector3f[] vertexs, int[] indices) {
		for (int i = 0; i < faceNormals.length; i++) {
			int index0 = indices[i * 3];
			int index1 = indices[i * 3 + 1];
			int index2 = indices[i * 3 + 2];

			Vector3f v1 = vertexs[index1].subtract(vertexs[index0]);
			Vector3f v2 = vertexs[index2].subtract(vertexs[index0]);

			faceNormals[i] = v1.cross(v2).normalize();
		}
	}

	private boolean ParseVertexData(int offset, int mVertexNum, int mFaceNum,
			int mTVFaceNum, boolean hasBones, DrzSubMesh submesh) {
		buffer.position(offset);
		submesh.mVertexList = new ArrayList<Vector3f>();
		submesh.mBoneAssignmentList = new ArrayList<String>();

		int vertoffset = offset;
		int physoffset = offset + (mVertexNum * 24) + (mFaceNum * 36) + (mTVFaceNum * 32);

		BONEBUFF BoneBuff = new BONEBUFF();
		if (hasBones == false) {
			// Just this object itself has the data!
			int matbuf = offset + 304;

			// 4x4 matrix
			BoneBuff.s1 = getInt(matbuf + 0);
			BoneBuff.s2 = getInt(matbuf + 16);
			BoneBuff.s3 = getInt(matbuf + 32);
			BoneBuff.s4 = getInt(matbuf + 48);
			BoneBuff.s5 = getInt(matbuf + 4);
			BoneBuff.s6 = getInt(matbuf + 20);
			BoneBuff.s7 = getInt(matbuf + 36);
			BoneBuff.s8 = getInt(matbuf + 52);
			BoneBuff.s9 = getInt(matbuf + 8);
			BoneBuff.s10 = getInt(matbuf + 24);
			BoneBuff.s11 = getInt(matbuf + 40);
			BoneBuff.s12 = getInt(matbuf + 56);

		}

		for (int k = 0; k < mVertexNum; k++) {
			buffer.position(vertoffset);
			long res1 = getInt();
			long res2 = getInt();
			long res3 = getInt();

			long v1, v2, v3;

			boolean BoneFound = false;

			String strBoneName = null;

			if (hasBones == true) {
				// Since this one has bones, we must go through the bone file
				buffer.position(physoffset);
				strBoneName = getString();
				if (SmbLoader.mBoneMeshDataInfo.containsKey(strBoneName) == true) {
					BoneBuff = SmbLoader.mBoneMeshDataInfo.get(strBoneName);
					BoneFound = true;
				}

				submesh.mBoneAssignmentList.add(strBoneName);
			}

			if (BoneFound == true) {
				// reverting..
				v1 = -((res2 * BoneBuff.s11 * BoneBuff.s2 - res2 * BoneBuff.s10
						* BoneBuff.s3 - res1 * BoneBuff.s11 * BoneBuff.s6
						+ res1 * BoneBuff.s10 * BoneBuff.s7 - res3
						* BoneBuff.s2 * BoneBuff.s7 + res3 * BoneBuff.s3
						* BoneBuff.s6 + BoneBuff.s12 * BoneBuff.s2
						* BoneBuff.s7 - BoneBuff.s12 * BoneBuff.s3
						* BoneBuff.s6 - BoneBuff.s11 * BoneBuff.s2
						* BoneBuff.s8 + BoneBuff.s11 * BoneBuff.s4
						* BoneBuff.s6 + BoneBuff.s10 * BoneBuff.s3
						* BoneBuff.s8 - BoneBuff.s10 * BoneBuff.s4
						* BoneBuff.s7) << 8)
						/ (BoneBuff.s1 * BoneBuff.s11 * BoneBuff.s6
								- BoneBuff.s11 * BoneBuff.s2 * BoneBuff.s5
								- BoneBuff.s1 * BoneBuff.s10 * BoneBuff.s7
								+ BoneBuff.s10 * BoneBuff.s3 * BoneBuff.s5
								+ BoneBuff.s2 * BoneBuff.s7 * BoneBuff.s9 - BoneBuff.s3
								* BoneBuff.s6 * BoneBuff.s9);
				v2 = ((res2 * BoneBuff.s1 * BoneBuff.s11 - res1 * BoneBuff.s11
						* BoneBuff.s5 - res3 * BoneBuff.s1 * BoneBuff.s7 + res3
						* BoneBuff.s3 * BoneBuff.s5 - res2 * BoneBuff.s3
						* BoneBuff.s9 + res1 * BoneBuff.s7 * BoneBuff.s9
						+ BoneBuff.s1 * BoneBuff.s12 * BoneBuff.s7
						- BoneBuff.s12 * BoneBuff.s3 * BoneBuff.s5
						- BoneBuff.s1 * BoneBuff.s11 * BoneBuff.s8
						+ BoneBuff.s11 * BoneBuff.s4 * BoneBuff.s5
						+ BoneBuff.s3 * BoneBuff.s8 * BoneBuff.s9 - BoneBuff.s4
						* BoneBuff.s7 * BoneBuff.s9) << 8)
						/ (BoneBuff.s1 * BoneBuff.s11 * BoneBuff.s6
								- BoneBuff.s11 * BoneBuff.s2 * BoneBuff.s5
								- BoneBuff.s1 * BoneBuff.s10 * BoneBuff.s7
								+ BoneBuff.s10 * BoneBuff.s3 * BoneBuff.s5
								+ BoneBuff.s2 * BoneBuff.s7 * BoneBuff.s9 - BoneBuff.s3
								* BoneBuff.s6 * BoneBuff.s9);
				v3 = -((res2 * BoneBuff.s1 * BoneBuff.s10 - res1 * BoneBuff.s10
						* BoneBuff.s5 - res3 * BoneBuff.s1 * BoneBuff.s6 + res3
						* BoneBuff.s2 * BoneBuff.s5 - res2 * BoneBuff.s2
						* BoneBuff.s9 + res1 * BoneBuff.s6 * BoneBuff.s9
						+ BoneBuff.s1 * BoneBuff.s12 * BoneBuff.s6
						- BoneBuff.s12 * BoneBuff.s2 * BoneBuff.s5
						- BoneBuff.s1 * BoneBuff.s10 * BoneBuff.s8
						+ BoneBuff.s10 * BoneBuff.s4 * BoneBuff.s5
						+ BoneBuff.s2 * BoneBuff.s8 * BoneBuff.s9 - BoneBuff.s4
						* BoneBuff.s6 * BoneBuff.s9) << 8)
						/ (BoneBuff.s1 * BoneBuff.s11 * BoneBuff.s6
								- BoneBuff.s11 * BoneBuff.s2 * BoneBuff.s5
								- BoneBuff.s1 * BoneBuff.s10 * BoneBuff.s7
								+ BoneBuff.s10 * BoneBuff.s3 * BoneBuff.s5
								+ BoneBuff.s2 * BoneBuff.s7 * BoneBuff.s9 - BoneBuff.s3
								* BoneBuff.s6 * BoneBuff.s9);

				float x = (float) v1 / 256.0f;
				float y = (float) v2 / 256.0f;
				float z = (float) v3 / 256.0f;
				Vector3f vert = new Vector3f(x, y, z);
				submesh.mVertexList.add(vert);

			} else {
				System.out.println("Vertex read error! No valid mesh matrix found. BoneName=" + strBoneName + " hasBone=" + hasBones + " Model=" + key.getName());
				return false;
			}

			// next vertex...
			vertoffset += 24;
			physoffset += 32;
		}

		return true;
	}

	private void ParseFaceBlock(final int offset, final DrzSubMesh submesh,
			final int mFaceNum) {
		buffer.position(offset);
		submesh.mFaceList = new ArrayList<DrzFaceTextureId>();

		int buf = offset;
		for (int k = 0; k < mFaceNum; k++) {
			buffer.position(buf);
			submesh.mFaceList.add(new DrzFaceTextureId(getShort(), getShort(),
					getShort(), getShort()));
			// next face
			buf += 36;
		}
	}

	private void ParseUVFaceVerticesBlock(final int offset,
			final DrzSubMesh submesh, final int UVFaceNum) {
		buffer.position(offset);
		submesh.mUvVertexList1 = new ArrayList<Vector2f>();

		int uvoff = offset;

		for (int i = 0; i < UVFaceNum; i++) {
			submesh.mUvVertexList1.add(new Vector2f(getFloat(uvoff + 0),
					1 - getFloat(uvoff + 12)));
			submesh.mUvVertexList1.add(new Vector2f(getFloat(uvoff + 4),
					1 - getFloat(uvoff + 16)));
			submesh.mUvVertexList1.add(new Vector2f(getFloat(uvoff + 8),
					1 - getFloat(uvoff + 20)));

			// next
			uvoff += 32;
		}
	}

}
