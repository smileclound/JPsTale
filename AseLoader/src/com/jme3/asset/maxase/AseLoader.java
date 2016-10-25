package com.jme3.asset.maxase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.maxase.animation.Keyframe;
import com.jme3.asset.maxase.animation.VertexAssignment;
import com.jme3.asset.maxase.material.Mtl;
import com.jme3.asset.maxase.material.TextureMap;
import com.jme3.asset.maxase.mesh.AseScene;
import com.jme3.asset.maxase.mesh.Face;
import com.jme3.asset.maxase.mesh.GeomObject;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 * Load *.ase models
 * 
 * @author yanmaoyuan
 *
 */
public class AseLoader implements AssetLoader {
	
	// using this to scan ASE file.
	private Scanner scan = null;
	private String cmd = null;
	private int strLevel = 0;
	
	// use it to translate AscScene Object into JME3 Spatial
	private AseProcessor processor;
	private AseScene scene = null;

	protected void reset() {
		scan = null;
		cmd = null;
		strLevel = 0;
	}

	private AssetKey key;
	/**
	 * Parse an ase file
	 */
	@Override
	public Object load(AssetInfo info) throws IOException {
		
		key = info.getKey();
		
		Spatial node = null;
		reset();
		
		try {
			scan = new Scanner(info.openStream());
			
			scene = new AseScene();
			
			// Model name
			// "char/monster/Buma/buma-boss.ASE" -> buma-boss
			int index = key.getFolder().length();
			int ext = key.getName().length() - key.getExtension().length() - 1;
			scene.name = key.getName().substring(index, ext);
			
			// scan the file
			scanAll();

			// Generate JME3 Spatial
			processor = new AseProcessor(info);
			node = processor.process(scene);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scan.close();
		}
		
		return node;
	}

	// SCENE
	private static final String SCENE = "*SCENE";
	private static final String SCENE_LASTFRAME = "*SCENE_LASTFRAME";
	private static final String SCENE_FRAMESPEED = "*SCENE_FRAMESPEED";
	private static final String SCENE_TICKSPERFRAME = "*SCENE_TICKSPERFRAME";
	
	// MATERIAL
	private static final String MATERIAL_LIST = "*MATERIAL_LIST";
	private static final String MATERIAL = "*MATERIAL";
	private static final String SUBMATERIAL = "*SUBMATERIAL";
	private static final String MATERIAL_NAME = "*MATERIAL_NAME";
	private static final String MATERIAL_CLASS = "*MATERIAL_CLASS";

	// TEXTURE
	private static final String MAP_DIFFUSE = "*MAP_DIFFUSE";
	private static final String MAP_OPACITY = "*MAP_OPACITY";
	private static final String MAP_NAME = "*MAP_NAME";
	private static final String BITMAP = "*BITMAP";
	private static final String UVW_U_OFFSET = "*UVW_U_OFFSET";
	private static final String UVW_V_OFFSET = "*UVW_V_OFFSET";
	private static final String UVW_U_TILING = "*UVW_U_TILING";
	private static final String UVW_V_TILING = "*UVW_V_TILING";

	// GEOMOBJECT
	private static final String GEOMOBJECT = "*GEOMOBJECT";
	private static final String HELPEROBJECT = "*HELPEROBJECT";
	private static final String NODE_NAME = "*NODE_NAME";
	private static final String NODE_PARENT = "*NODE_PARENT";
	
	private static final String NODE_TM = "*NODE_TM";
	private static final String TM_ROW0 = "*TM_ROW0";
	private static final String TM_ROW1 = "*TM_ROW1";
	private static final String TM_ROW2 = "*TM_ROW2";
	private static final String TM_ROW3 = "*TM_ROW3";
	private static final String TM_POS = "*TM_POS";
	private static final String TM_ROTAXIS = "*TM_ROTAXIS";
	private static final String TM_ROTANGLE = "*TM_ROTANGLE";
	private static final String TM_SCALE = "*TM_SCALE";
	private static final String TM_SCALEAXIS = "*TM_SCALEAXIS";
	private static final String TM_SCALEAXISANG = "*TM_SCALEAXISANG";
	
	// MESH
	private static final String MESH = "*MESH";
	private static final String TIMEVALUE = "*TIMEVALUE";
	private static final String MESH_VERTEX_LIST = "*MESH_VERTEX_LIST";
	private static final String MESH_VERTEX = "*MESH_VERTEX";
	private static final String MESH_FACE_LIST = "*MESH_FACE_LIST";
	private static final String MESH_FACE = "*MESH_FACE";
	private static final String MESH_TVERTLIST = "*MESH_TVERTLIST";
	private static final String MESH_TVERT = "*MESH_TVERT";
	private static final String MESH_TFACELIST = "*MESH_TFACELIST";
	private static final String MESH_TFACE = "*MESH_TFACE";
	private static final String MESH_NORMALS = "*MESH_NORMALS";
	private static final String MESH_VERTEXNORMAL = "*MESH_VERTEXNORMAL";
	private static final String MESH_CVERTLIST = "*MESH_CVERTLIST";
	private static final String MESH_VERTCOL = "*MESH_VERTCOL";
	private static final String MESH_CFACELIST = "*MESH_CFACELIST";
	private static final String MESH_CFACE = "*MESH_CFACE";
	private static final String MESH_MTLID = "*MESH_MTLID";
	private static final String MATERIAL_REF = "*MATERIAL_REF";
	private static final String WIREFRAME_COLOR = "*WIREFRAME_COLOR";
	
	// ANIMATION - under developing
	private static final String TM_ANIMATION = "*TM_ANIMATION";
	private static final String CONTROL_POS_TRACK = "*CONTROL_POS_TRACK";
	private static final String CONTROL_POS_SAMPLE = "*CONTROL_POS_SAMPLE";
	private static final String CONTROL_ROT_TRACK = "*CONTROL_ROT_TRACK";
	private static final String CONTROL_ROT_SAMPLE = "*CONTROL_ROT_SAMPLE";
	
	// SKIN - under developing
	private static final String PHYSIQUE = "*PHYSIQUE";
	private static final String PHYSIQUE_VERTEXASSIGNMENT_NONBLENDED_RIGIDTYPE = "*PHYSIQUE_VERTEXASSIGNMENT_NONBLENDED_RIGIDTYPE";
	
	/**
	 * Scan the whole ase file
	 */
	private void scanAll() {
		strLevel = 0;
		while(scan.hasNext()) {
			String line = scan.nextLine().trim();
			
			if (line.endsWith("{")) {
				strLevel++;
			}
			
			StringTokenizer tokenizer = new StringTokenizer(line);
			cmd = tokenizer.nextToken();

			if (cmd.equals(SCENE)) {
				scanScene();
			} else if (cmd.equals(MATERIAL_LIST)) {
				scanMaterialList();
			} else if (cmd.equals(GEOMOBJECT)) {
				scanGeomObject();
			} else if (cmd.equals(HELPEROBJECT)) {
				scanGeomObject();
			}
		}
	}

	/*******************************
	 * 
	 * *SCENE
	 * 
	 *******************************/
	
	private void scanScene() {
		int curLevel = strLevel;
		
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}

			StringTokenizer tokenizer = new StringTokenizer(line);
			cmd = tokenizer.nextToken();
			
			if (cmd.equals(SCENE_LASTFRAME)) {
				int lastframe = Integer.parseInt(tokenizer.nextToken());
				scene.setLastframe(lastframe);
			} else if (cmd.equals(SCENE_FRAMESPEED)) {
				float framespeed = Float.parseFloat(tokenizer.nextToken());
				scene.setFramespeed(framespeed);
			} else if (cmd.equals(SCENE_TICKSPERFRAME)) {
				float ticksPerFrame = Float.parseFloat(tokenizer.nextToken());
				scene.setTicksPerFrame(ticksPerFrame);
			}
		}
	}
	
	
	/*******************************
	 * 
	 * *MATERIAL_LIST
	 * 
	 *******************************/
	private void scanMaterialList() {
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tokenizer = new StringTokenizer(line);
			cmd = tokenizer.nextToken();

			// MaterialList
			if (cmd.equals(MATERIAL)) {
				int id = Integer.parseInt(tokenizer.nextToken());
				Mtl mtl = scanMaterial();
				mtl.id = id;
				scene.add(mtl);
			}
		}
	}
	
	/**
	 * Read one 3dmax Material
	 * @param mtl 
	 * @param mtl 
	 */
	private Mtl scanMaterial() {
		
		Mtl mtl = new Mtl();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel)
					break;
			}

			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();

			if (cmd.equals(MATERIAL_NAME)) {
				int start = line.indexOf("\"") + 1;
				int end = line.length() - 1;
				mtl.name = line.substring(start, end);
			} else if (cmd.equals(MATERIAL_CLASS)) {
				int start = line.indexOf("\"") + 1;
				int end = line.length() - 1;
				mtl.clazz = line.substring(start, end);
			} else if (cmd.equals("*MATERIAL_TWOSIDED")) {
				mtl.twoSide = true;
			} else if (cmd.equals(MAP_DIFFUSE)) {
				mtl.diffuseMap = scanTexture();
			} else if (cmd.equals(MAP_OPACITY)) {
				mtl.opacityMap = scanTexture();
			} else if (cmd.equals(SUBMATERIAL)) {// it has sub material
				int id = nextInt(tok);
			
				Mtl subMtl = scanMaterial();
				subMtl.id = id;
				
				if (mtl.subMtls == null) {
					mtl.subMtls = new HashMap<Integer, Mtl>();
				}
				mtl.subMtls.put(id, subMtl);
				
			}
		}
		
		mtl.process();
		return mtl;
	}
	
	/**
	 * Textures
	 * @return
	 */
	private TextureMap scanTexture() {
		TextureMap map = new TextureMap();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel)
					break;
			}

			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();

			if (cmd.equals(MAP_NAME)) {
				int start = line.indexOf("\"") + 1;
				int end = line.length() - 1;
				map.mapName = line.substring(start, end);
			} else if (cmd.equals(BITMAP)) {
				// 3D MAX use absolute path, I only need the file name. Image files
				// must put just in the some folder with ASE model.
				// C:\XXXX\Works\Autodesk\3DMAX\images\skin.bmp -> skin.bmp
				int start = line.indexOf("\"") + 1;
				int end = line.length() - 1;
				String texName = line.substring(start, end);

				int index = texName.lastIndexOf("\\");
				if (index != -1) {
					texName = texName.substring(index + 1);
				}
				texName = key.getFolder() + texName;
				
				map.texName = texName;
			} else if (cmd.equals(UVW_U_OFFSET)) {
				map.uOffset = Float.parseFloat(tok.nextToken());
			} else if (cmd.equals(UVW_V_OFFSET)) {
				map.vOffset = Float.parseFloat(tok.nextToken());
			} else if (cmd.equals(UVW_U_TILING)) {
				map.uTiling = Float.parseFloat(tok.nextToken());
			} else if (cmd.equals(UVW_V_TILING)) {
				map.vTiling = Float.parseFloat(tok.nextToken());
			}
		}
		
		if (map.uOffset != 0 || map.vOffset != 0) {
			// TODO 
		}
		if (map.uTiling != 1 || map.vTiling != 1) {
			// TODO 
		}
		
		map.process();
		return map;
	}
	/*******************************
	 * 
	 * *GEOMOBJECT
	 * 
	 *******************************/
	private void scanGeomObject() {
		
		GeomObject obj = new GeomObject();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {

			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();

			if (cmd.equals(NODE_NAME)) {
				int start = line.indexOf("\"") + 1;
				int end = line.length() - 1;
				obj.name = line.substring(start, end);
			} else if (cmd.equals(NODE_PARENT)) {
				int start = line.indexOf("\"") + 1;
				int end = line.length() - 1;
				obj.parent = line.substring(start, end);
			} else if (cmd.equals(NODE_TM)) {
				scanNodeTm(obj);
			} else if (cmd.equals(MESH)) {
				scanMesh(obj);
			} else if (cmd.equals(MATERIAL_REF)) {
				int mtl_ref = nextInt(tok);
				obj.mtl = scene.getMtl(mtl_ref);
			} else if (cmd.equals(WIREFRAME_COLOR)) {
				Vector3f c = nextVector3f(tok);
				obj.wireframe = new ColorRGBA(c.x, c.y, c.z, 1);
			} else if (cmd.equals(TM_ANIMATION)) {
				scanAnimation(obj);
			} else if (cmd.equals(PHYSIQUE)) {
				scanPhysique(obj);
			}
		}
		
		scene.add(obj);
	}
	
	private void scanNodeTm(GeomObject obj) {
		int curLevel = strLevel;
		
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(TM_ROW0)) {
				Vector3f ret = new Vector3f(0, 0, 0);
				ret.x = nextFloat(tok);
				ret.y = nextFloat(tok);
				ret.z = nextFloat(tok);
				obj.row0 = ret;
			} else if (cmd.equals(TM_ROW1)) {
				Vector3f ret = new Vector3f(0, 0, 0);
				ret.x = nextFloat(tok);
				ret.y = nextFloat(tok);
				ret.z = nextFloat(tok);
				obj.row1 = ret;
			} else if (cmd.equals(TM_ROW2)) {
				Vector3f ret = new Vector3f(0, 0, 0);
				ret.x = nextFloat(tok);
				ret.y = nextFloat(tok);
				ret.z = nextFloat(tok);
				obj.row2 = ret;
			} else if (cmd.equals(TM_ROW3)) {
				Vector3f ret = new Vector3f(0, 0, 0);
				ret.x = nextFloat(tok);
				ret.y = nextFloat(tok);
				ret.z = nextFloat(tok);
				obj.row3 = ret;
			} else if (cmd.equals(TM_POS)) {
				Vector3f ret = new Vector3f(0, 0, 0);
				ret.x = nextFloat(tok);
				ret.y = nextFloat(tok);
				ret.z = nextFloat(tok);
				obj.pos = ret;
			} else if (cmd.equals(TM_ROTAXIS)) {
				Vector3f ret = new Vector3f(0, 0, 0);
				ret.x = nextFloat(tok);
				ret.y = nextFloat(tok);
				ret.z = nextFloat(tok);
				obj.rotAxis = ret;
			} else if (cmd.equals(TM_ROTANGLE)) {
				obj.rotAngle = nextFloat(tok);
			} else if (cmd.equals(TM_SCALE)) {
				Vector3f ret = new Vector3f(0, 0, 0);
				ret.x = nextFloat(tok);
				ret.y = nextFloat(tok);
				ret.z = nextFloat(tok);
				obj.scale = ret;
			} else if (cmd.equals(TM_SCALEAXIS)) {
				Vector3f ret = new Vector3f(0, 0, 0);
				ret.x = nextFloat(tok);
				ret.y = nextFloat(tok);
				ret.z = nextFloat(tok);
				obj.scaleAxis = ret;
			} else if (cmd.equals(TM_SCALEAXISANG)) {
				obj.scaleAxisAngle = nextFloat(tok);
			}
		}
		
		obj.rotation = new Quaternion().fromAngleAxis(-obj.rotAngle, obj.rotAxis);
	}
	
	private void scanMesh(GeomObject mesh) {
		
		boolean scanningThis = true;
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(TIMEVALUE)) {
				mesh.timevalue = nextInt(tok);
			} else if (cmd.equals("*MESH_MAPPINGCHANNEL")) {
				// TODO this geomobject use Shell Material
				// I hate 3dmax Material!!
			} else if (cmd.equals(MESH_VERTEX_LIST)) {
				if (scanningThis) {
					scanMeshVertexList(mesh);
				}
			} else if (cmd.equals(MESH_FACE_LIST)) {
				if (scanningThis) {
					scanMeshFaceList(mesh);
				}
			} else if (cmd.equals(MESH_TVERTLIST)) {
				if (scanningThis) {
					scanMeshTextureVertexList(mesh);
				}
			} else if (cmd.equals(MESH_TFACELIST)) {
				if (scanningThis) {
					scanMeshTextureFaceList(mesh);
				}
			} else if (cmd.equals(MESH_NORMALS)) {
				if (scanningThis) {
					scanMeshNormalList(mesh);
				}
			} else if (cmd.equals(MESH_CVERTLIST)) {
				if (scanningThis) {
					scanMeshVertexColorList(mesh);
				}
			} else if (cmd.equals(MESH_CFACELIST)) {
				if (scanningThis) {
					scanMeshColorFaceList(mesh);
				}
			}
		}
		
	}
	
	private void scanMeshColorFaceList(GeomObject mesh) {
		mesh.vcFaces = new ArrayList<Face>();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(MESH_CFACE)) {
				Face face = new Face();
				nextInt(tok);// index
				face.v1 = nextInt(tok);
				face.v2 = nextInt(tok);
				face.v3 = nextInt(tok);
				mesh.vcFaces.add(face);
			}
		}
	}

	private void scanMeshVertexColorList(GeomObject mesh) {
		mesh.vcVerts = new ArrayList<ColorRGBA>();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(MESH_VERTCOL)) {
				ColorRGBA c = new ColorRGBA();
				nextInt(tok);// index
				c.r = nextFloat(tok);
				c.g = nextFloat(tok);
				c.b = nextFloat(tok);
				c.a = 1;
				mesh.vcVerts.add(c);
			}
		}
	}
	
	private void scanMeshNormalList(GeomObject mesh) {
		mesh.normals = new ArrayList<Vector3f>();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(MESH_VERTEXNORMAL)){
				nextInt(tok);// index
				Vector3f normal = nextVector3f(tok);
				mesh.normals.add(normal);
			}
		}
	}

	private void scanMeshTextureVertexList(GeomObject mesh) {
		mesh.texCoords = new ArrayList<Vector2f>();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(MESH_TVERT)) {
				Vector2f uv = new Vector2f();
				nextInt(tok);// index
				uv.x = nextFloat(tok);
				uv.y = nextFloat(tok);
				mesh.texCoords.add(uv);
			}
		}
	}

	private void scanMeshTextureFaceList(GeomObject mesh) {
		mesh.uvFaces = new ArrayList<Face>();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			

			if (cmd.equals(MESH_TFACE)) {
				Face face = new Face();
				nextInt(tok);// index
				face.v1 = nextInt(tok);
				face.v2 = nextInt(tok);
				face.v3 = nextInt(tok);
				mesh.uvFaces.add(face);
			}
		}
	}

	private void scanMeshFaceList(GeomObject mesh) {
		mesh.faces = new ArrayList<Face>();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(MESH_FACE)) {
				Face f = new Face();
				f.index = mesh.faces.size();
				tok.nextToken();// index:
				tok.nextToken();// A:
				f.v1 = nextInt(tok);
				tok.nextToken();// B:
				f.v2 = nextInt(tok);
				tok.nextToken();// C:
				f.v3 = nextInt(tok);
				
				// skip what i dont need
				while(!tok.nextToken().equals(MESH_MTLID));
				
				f.mtlid = nextInt(tok);
				mesh.faces.add(f);
			}
		}
	}

	private void scanMeshVertexList(GeomObject mesh) {
		mesh.verts = new ArrayList<Vector3f>();

		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(MESH_VERTEX)) {
				nextInt(tok);// index
				Vector3f v = nextVector3f(tok);
				mesh.verts.add(v);
			}
		}
		
	}

	/*******************************
	 * 
	 * *TM_ANIMATION
	 * 
	 *******************************/
	private void scanAnimation(GeomObject obj) {
		
		TreeMap<Float, Keyframe> keyframes = new TreeMap<Float, Keyframe>();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(CONTROL_POS_TRACK) || cmd.equals("*CONTROL_POS_BEZIER")||cmd.equals("*CONTROL_POS_TCB")) {
				scanPositionTrack(keyframes);
			} else if (cmd.equals(CONTROL_ROT_TRACK) || cmd.equals("*CONTROL_ROT_BEZIER") || cmd.equals("*CONTROL_ROT_TCB")) {
				scanRotationTrack(keyframes);
			} else if (cmd.equals("*CONTROL_SCL_TRACK") || cmd.equals("*CONTROL_SCALE_BEZIER") || cmd.equals("*CONTROL_SCALE_TCB")) {
				scanScaleTrack(keyframes);
			}
		}
		
		if (keyframes.size() > 0) {
			obj.keyframes = keyframes;
			scene.hasAnimation = true;
		}
	}
	
	private Keyframe getOrMakeKeyframe(SortedMap<Float, Keyframe> keyframes,
			float time) {
		Keyframe k = keyframes.get(time);
		if (k == null) {
			k = new Keyframe();
			keyframes.put(time, k);
		}
		return k;
	}
	
	private void scanPositionTrack(SortedMap<Float, Keyframe> keyframes) {
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(CONTROL_POS_SAMPLE)
					|| cmd.equals("*CONTROL_TCB_POS_KEY")
					|| cmd.equals("*CONTROL_BEZIER_POS_KEY")) {
				float time = nextFloat(tok) * scene.getSecsPerTick();
				Vector3f vec3 = nextVector3f(tok);
				Keyframe k = getOrMakeKeyframe(keyframes, time);
				k.translation = vec3;
			}
		}
	}

	private void scanRotationTrack(SortedMap<Float, Keyframe> keyframes) {
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(CONTROL_ROT_SAMPLE)
					|| cmd.equals("*CONTROL_TCB_ROT_KEY")) {
				float time = nextFloat(tok) * scene.getSecsPerTick();
				Vector3f axis = nextVector3f(tok);
				float radians = nextFloat(tok);
				
				Quaternion rotation = new Quaternion();
				//	the first frame is baked into the TM
				if (time == 0)
					rotation.set(0, 0, 0, 1);
				else
					rotation.fromAngleAxis(-radians, axis);
				
				Keyframe k = getOrMakeKeyframe(keyframes, time);
				k.rotation = rotation;
				
			}
		}
		
//		turn relative rotations into absolutes
		getOrMakeKeyframe(keyframes, 0);
		Quaternion ori = new Quaternion();
		for (Keyframe k : keyframes.values()) {
			if (k.rotation != null) {
				ori = k.rotation.mult(ori);
				k.rotation.set(ori);
			}
		}
	}
	
	private void scanScaleTrack(SortedMap<Float, Keyframe> keyframes) {
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals("*CONTROL_SCALE_SAMPLE")
					|| cmd.equals("*CONTROL_TCB_SCALE_KEY")
					|| cmd.equals("*CONTROL_BEZIER_SCALE_KEY")) {
				float time = nextFloat(tok) * scene.getSecsPerTick();
				Vector3f scale = new Vector3f(0, 0, 0);
				scale.y = nextFloat(tok);
				scale.z = nextFloat(tok);
				scale.x = nextFloat(tok);
				
				Keyframe k = getOrMakeKeyframe(keyframes, time);
				k.scale = scale;
			}
		}
	}
	private void scanPhysique(GeomObject obj) {
		obj.assignments = new ArrayList<VertexAssignment>();
		
		int curLevel = strLevel;
		while (scan.hasNext()) {
			String line = scan.nextLine().trim();
			if (line.endsWith("{")) {
				strLevel++;
			} else if (line.endsWith("}")) {
				strLevel--;
				if (strLevel < curLevel) {
					break;
				}
			}
			
			StringTokenizer tok = new StringTokenizer(line);
			cmd = tok.nextToken();
			
			if (cmd.equals(PHYSIQUE_VERTEXASSIGNMENT_NONBLENDED_RIGIDTYPE)) {
				VertexAssignment va = new VertexAssignment();
				va.vertexIndex = nextInt(tok);
				int start = line.indexOf("\"") + 1;
				int end = line.length() - 1;
				va.boneName = line.substring(start, end);
				obj.assignments.add(va);
			}
			if (cmd.equals("*PHYSIQUE_VERTEXASSIGNMENT_BLENDED_RIGIDTYPE")) {
				int vertexIndex = nextInt(tok);// pNum
				System.out.println("Blended: " + vertexIndex);
			}
			if (cmd.equals("*PHYSIQUE_VERTEXASSIGNMENT_NODE")) {
				int x = nextInt(tok);
				int y = nextInt(tok);
				String boneName = tok.nextToken();
				
				if (x == 0) {
					System.out.println("Node: " + boneName + " " + x + " " + y);
				}
			}
		}
	}
	
	/**
	 * Next int
	 * @param tok
	 * @return
	 */
	private int nextInt(StringTokenizer tok) {
		String str = tok.nextToken();
		return Integer.parseInt(str);
	}
	
	/**
	 * Next float
	 * @param tok
	 * @return
	 */
	private float nextFloat(StringTokenizer tok) {
		String str = tok.nextToken();
		// Don't understand why, some times it is a "-1.#QNB"
		if (str.equals("-1.#QNB")) {
			str = "0.0000";
		}
		return Float.parseFloat(str);
	}
	
	/**
	 * Next vector3f
	 * @param tok
	 * @return
	 */
	private Vector3f nextVector3f(StringTokenizer tok) {
		/*
		 * 3D MAX Axis:
		 *                 z     x
		 *                 |    /
		 *                 |   /
		 *                 |  /
		 *                 | /
		 *                 |/
		 *     y ----------*
		 * JME3 Axis:
		 *          | y
		 *          |
		 *          |
		 *          |
		 *          |
		 *          *---------- x
		 *         /
		 *        /
		 *       /
		 *    z /
		 * 
		 */
		float x = nextFloat(tok);
		float y = nextFloat(tok);
		float z = nextFloat(tok);
		Vector3f ret = new Vector3f(-y, z, -x);
		return ret;
	}
}