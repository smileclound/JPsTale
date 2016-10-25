package com.jme3.asset.maxase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Animation;
import com.jme3.animation.Bone;
import com.jme3.animation.BoneTrack;
import com.jme3.animation.Skeleton;
import com.jme3.animation.SkeletonControl;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetManager;
import com.jme3.asset.maxase.animation.Keyframe;
import com.jme3.asset.maxase.material.Mtl;
import com.jme3.asset.maxase.mesh.AseScene;
import com.jme3.asset.maxase.mesh.Face;
import com.jme3.asset.maxase.mesh.GeomObject;
import com.jme3.bounding.BoundingBox;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.control.AbstractControl;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.BufferUtils;
import com.jme3.util.IntMap;
import com.jme3.util.IntMap.Entry;

/**
 * Translate AseScene into JME3 Spatials
 * 
 * @author yanmaoyuan
 * 
 */
public class AseProcessor implements CONSTANT {
	
	private AssetManager manager = null;
	private AssetKey key = null;

	public AseProcessor(AssetInfo info) {
		this.manager = info.getManager();
		this.key = info.getKey();
	}

	// Nodes
	private Node rootNode = null;
	private HashMap<String, Node> nodes = new HashMap<String, Node>();

	// Default materials. i use them for testing models.
	private Material missingMtl = null;// No textures?
	private Material alphaMtl = null;// Just can't see it
	private Material wireframeMtl = null;

	public Spatial process(AseScene scene) {

		nodes.clear();

		rootNode = new Node("Ascii_Model_" + scene.name);
		// build models 
		compileModel(scene.getObjects());
		// create bones
		compileSkeleton(scene);
		// create animation - don't use
		compileAnimation(scene);
		// bake animation
		bake(scene);
		
		return rootNode;
	}

	/*******************************
	 * 
	 * Generate JME3 Spatials
	 * 
	 *******************************/

	/**
	 * Build JME3 Model
	 * 
	 * @param objects
	 * 
	 * @return
	 */
	protected void compileModel(List<GeomObject> objects) {
		cache.clear();
		Node bones = new Node("BONES");
		rootNode.attachChild(bones);

		Node skins = new Node("SKINS");
		rootNode.attachChild(skins);
		
		for (GeomObject obj : objects) {

			// Check mesh
			if (!obj.hasMesh()) {
				// No mesh??
				// It may be a HelpObject or Bip, do nothing for now
			} else if (obj.isBone()) {
				// It's a bone, handle it in compileSkeleton() method.
				Mesh mesh = compileSingleMesh(obj);

				Geometry geom = new Geometry(obj.name, mesh);
				geom.setModelBound(new BoundingBox());
				geom.updateModelBound();
				geom.setMaterial(getWireFrameMaterial());// Even if it has no matrials, i want to see the bone.

				bones.attachChild(geom);
			} else if (!obj.hasMaterial() || !obj.hasTextureFace()) {
				Mesh mesh = compileSingleMesh(obj);

				Geometry geom = new Geometry(obj.name, mesh);
				geom.setModelBound(new BoundingBox());
				geom.updateModelBound();
				geom.setMaterial(getAlphaMaterial());// Even if it has no matrials, i want to see it.

				skins.attachChild(geom);
				geom = null;
			} else {
				switch (obj.mtl.clazz) {
				case "Standard": {// Standard 3DMAX material.
					Mesh mesh = compileStandardMesh(obj);

					Geometry geom = new Geometry(obj.name, mesh);
					geom.setModelBound(new BoundingBox());
					geom.updateModelBound();

					Material material = makeMaterial(obj.mtl);
					geom.setMaterial(material);

					makeScript(geom, obj.mtl);
					
					skins.attachChild(geom);

					material = null;
					geom = null;
					break;
				}
				case "Multi/Sub-Object": {// One mesh with two or more textures.

					/**
					 * In this situation, I separate the mesh to sub meshes, 
					 * each mesh while has only one texture, generate one Geometry.
					 * Separate it by MTL_ID
					 */
					constructMultiGeomNode(obj);

					break;
				}
				case "Shell Material": {// 3DMAX shell material. I HATE THIS ONE.
					constructMultiGeomNode(obj);
					break;
				}
				default:
					System.out.println("Default" + obj.mtl.clazz);
				}
			}

		}

		cache.clear();
	}

	/**
	 * build mesh
	 * 
	 * @param node
	 * 
	 * @param obj
	 * @return
	 */
	private void constructMultiGeomNode(GeomObject obj) {
		/*********************** Separate Mesh by MTL_ID *******************/
		IntMap<List<Face>> faceMap = new IntMap<List<Face>>();
		for (int i = 0; i < obj.getFaceCount(); i++) {
			Face face = obj.faces.get(i);
			face.index = i;
			if (faceMap.get(face.mtlid) == null) {
				List<Face> groupByMtlid = new ArrayList<Face>();
				groupByMtlid.add(face);
				faceMap.put(face.mtlid, groupByMtlid);
			} else {
				List<Face> groupByMtlid = faceMap.get(face.mtlid);
				groupByMtlid.add(face);
			}
		}

		/********* For each texture, i build one mesh and one Geometry ************/
		for (Entry<List<Face>> entity : faceMap) {
			int mtl_id = entity.getKey();

			GeomObject subObj = obj.clone();
			subObj.faces = entity.getValue();
			subObj.name = obj.name + "_" + mtl_id;

			Mesh mesh = compileStandardMesh(subObj);
			Geometry geom = new Geometry(subObj.name, mesh);

			geom.setModelBound(new BoundingBox());
			geom.updateModelBound();

			// Set materials
			if (obj.mtl.clazz.equals("Multi/Sub-Object")) {
				Material material = makeMaterial(obj.mtl.subMtls.get(mtl_id));
				geom.setMaterial(material);
				
				makeScript(geom, obj.mtl.subMtls.get(mtl_id));
			} else {// Shell Material
				Mtl diffuseMaps = obj.mtl.subMtls.get(0);// DiffuseMap
				Mtl lightMap = obj.mtl.subMtls.get(1);// LightMap;

				Material material = makeMaterial(diffuseMaps.subMtls.get(mtl_id)).clone();
				material.setTexture("LightMap", loadTexture(lightMap.diffuseMap.texName));
				geom.setMaterial(material);
				
				makeScript(geom, diffuseMaps.subMtls.get(mtl_id));
			}
			((Node)rootNode.getChild("SKINS")).attachChild(geom);

			geom = null;
		}
	}

	/**
	 * compile Standard Mesh
	 * 
	 * @param obj
	 * @param mtl_id
	 * @param faces
	 * @return
	 */
	private Mesh compileStandardMesh(final GeomObject obj) {
		Mesh mesh = new Mesh();
		int fCount = obj.faces.size();

		float v[] = new float[fCount * 3 * 3];
		int f[] = new int[fCount * 3];
		float tv[] = new float[fCount * 3 * 2];

		for (int i = 0; i < obj.faces.size(); i++) {
			Face face = obj.faces.get(i);

			// faces
			f[i * 3] = i * 3;
			f[i * 3 + 1] = i * 3 + 1;
			f[i * 3 + 2] = i * 3 + 2;

			// Vectors
			Vector3f v1 = obj.verts.get(face.v1);
			v[i * 3 * 3 + 0] = v1.x;
			v[i * 3 * 3 + 1] = v1.y;
			v[i * 3 * 3 + 2] = v1.z;
			Vector3f v2 = obj.verts.get(face.v2);
			v[i * 3 * 3 + 3] = v2.x;
			v[i * 3 * 3 + 4] = v2.y;
			v[i * 3 * 3 + 5] = v2.z;
			Vector3f v3 = obj.verts.get(face.v3);
			v[i * 3 * 3 + 6] = v3.x;
			v[i * 3 * 3 + 7] = v3.y;
			v[i * 3 * 3 + 8] = v3.z;

			// UV
			Face uvFace = obj.uvFaces.get(face.index);
			Vector2f uv1 = obj.texCoords.get(uvFace.v1);
			Vector2f uv2 = obj.texCoords.get(uvFace.v2);
			Vector2f uv3 = obj.texCoords.get(uvFace.v3);
			tv[i * 6] = uv1.x;
			tv[i * 6 + 1] = uv1.y;
			tv[i * 6 + 2] = uv2.x;
			tv[i * 6 + 3] = uv2.y;
			tv[i * 6 + 4] = uv3.x;
			tv[i * 6 + 5] = uv3.y;
		}

		mesh.setBuffer(Type.Position, 3, v);
		mesh.setBuffer(Type.Index, 3, f);
		mesh.setBuffer(Type.TexCoord, 2, tv);

		mesh.setStatic();
		mesh.updateBound();
		mesh.updateCounts();

		return mesh;
	}

	/**
	 * compile Single Mesh
	 * 
	 * @param node
	 * @param obj
	 * @return
	 */
	private Mesh compileSingleMesh(GeomObject obj) {
		Mesh mesh = new Mesh();

		float v[] = new float[obj.getVertexCount() * 3];
		for (int i = 0; i < obj.verts.size(); i++) {
			Vector3f vert = obj.verts.get(i);
			v[i * 3] = vert.x;
			v[i * 3 + 1] = vert.y;
			v[i * 3 + 2] = vert.z;
		}
		mesh.setBuffer(Type.Position, 3, v);

		int f[] = new int[obj.getFaceCount() * 3];
		for (int i = 0; i < obj.faces.size(); i++) {
			Face face = obj.faces.get(i);
			f[i * 3] = face.v1;
			f[i * 3 + 1] = face.v2;
			f[i * 3 + 2] = face.v3;
		}
		mesh.setBuffer(Type.Index, 3, f);
		
		Vector3f[] normals = generateNormals(obj.verts, f);
		mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normals));

		mesh.setStatic();
		mesh.updateBound();
		mesh.updateCounts();

		return mesh;
	}
	
    private Vector3f[] generateNormals(List<Vector3f> verts)
    {
        Vector3f[] normals = new Vector3f[verts.size()];
        for(int i=0; i < normals.length;)
        {
            Vector3f normal    = new Vector3f();
            Vector3f v1        = new Vector3f();
            Vector3f v2        = new Vector3f();
    
            v1 = verts.get(i+1).subtract(verts.get(i));
            v2 = verts.get(i+2).subtract(verts.get(i));
            v1.cross(v2, normal);
            normal.normalize();

    
            normals[i++] = normal;
            normals[i++] = normal;
            normals[i++] = normal;
        }

        return normals;
    }
    
    private Vector3f[] generateNormals(List<Vector3f> verts, int[] indices)
    {
    	Vector3f[] normals = new Vector3f[verts.size()];
        for(int i=0; i < indices.length; i+=3)
        {
        	int index0 = indices[i];
            int index1 = indices[i+1];
            int index2 = indices[i+2];
            
            Vector3f v1 = verts.get(index1).subtract(verts.get(index0));
            Vector3f v2 = verts.get(index2).subtract(verts.get(index0));
            Vector3f normal = v1.cross(v2);
            normal.normalize();

            if (normals[index0] == null) {
            	normals[index0] = normal;
            } else {
        		normals[index0].addLocal(normal);
        	}
            
            if (normals[index1] == null) {
            	normals[index1] = normal;
            } else {
        		normals[index1].addLocal(normal);
        	}
            
            if (normals[index2] == null) {
            	normals[index2] = normal;
            } else {
        		normals[index2].addLocal(normal);
        	}
        }
        
        for(int i=0; i<normals.length; i++) {
        	if (normals[i] != null) {
        		normals[i].normalize();
        	}
        }

        return normals;
    }

	/*******************************
	 * 
	 * Build JME3 Material
	 * 
	 *******************************/
	HashMap<String, Material> cache = new HashMap<String, Material>();

	private Material makeMaterial(Mtl mtl) {

		if (mtl == null) {
			return getMissingMaterial();
		}
		
		// wall:
		if ((mtl.ScriptState & sMATS_SCRIPT_NOTVIEW) != 0) {
			return getAlphaMaterial();
		}

		String key = String.format("%s_%s_%d", mtl.name, mtl.clazz, mtl.id);

		// i already had it?
		if (cache.get(key) != null) {
			return cache.get(key);
		}

		Material material = null;

		material = new Material(manager, "Common/MatDefs/Light/Lighting.j3md");

		material.setBoolean("UseMaterialColors", true);

		material.setColor("Ambient", ColorRGBA.White);
		material.setColor("Diffuse", ColorRGBA.White);
		material.setColor("Specular", ColorRGBA.White);
		material.setColor("GlowColor", ColorRGBA.Black);
		material.setFloat("Shininess", 25f);

		RenderState rs = material.getAdditionalRenderState();

		rs.setAlphaTest(true);
		rs.setAlphaFallOff(0.01f);

		if (mtl.twoSide) {
			rs.setFaceCullMode(RenderState.FaceCullMode.Off);// twoside
		}

		if (mtl.diffuseMap != null) {
			Texture texture = loadTexture(mtl.diffuseMap.texName);
			switch (mtl.diffuseMap.BitmapFormState) {
			case CONSTANT.D3DTOP_ADD: {
				break;
			}
			}
			switch (mtl.diffuseMap.BitmapStageState) {
			case 0:
				break;
			}
			material.setTexture("DiffuseMap", texture);
		} else {
			rs.setFaceCullMode(FaceCullMode.FrontAndBack);// Not textures?
		}
		if (mtl.opacityMap != null) {
			Texture texture = loadTexture(mtl.opacityMap.texName);
			material.setTexture("AlphaMap", texture);
		}

		// TODO under developing.
		int WindMeshBottom = 0;
		int MeshState = 0;
		if ((mtl.ScriptState & sMATS_SCRIPT_WIND) != 0) {
			WindMeshBottom = sMATS_SCRIPT_WINDZ1;
			MeshState = 0;
		}
		if ((mtl.ScriptState & sMATS_SCRIPT_WINDX1) != 0) {
			WindMeshBottom = sMATS_SCRIPT_WINDX1;
			MeshState = 0;
		}
		if ((mtl.ScriptState & sMATS_SCRIPT_WINDX2) != 0) {
			WindMeshBottom = sMATS_SCRIPT_WINDX2;
			MeshState = 0;
		}
		if ((mtl.ScriptState & sMATS_SCRIPT_WINDZ1) != 0) {
			WindMeshBottom = sMATS_SCRIPT_WINDZ1;
			MeshState = 0;
		}
		if ((mtl.ScriptState & sMATS_SCRIPT_WINDZ2) != 0) {
			WindMeshBottom = sMATS_SCRIPT_WINDZ2;
			MeshState = 0;
		}
		if ((mtl.ScriptState & sMATS_SCRIPT_WATER) != 0) {
			WindMeshBottom = sMATS_SCRIPT_WATER;
			MeshState = 0;
		}
		if ((mtl.ScriptState & sMATS_SCRIPT_NOTPASS) != 0) {
			MeshState = SMMAT_STAT_CHECK_FACE;
		} else {
			if ((mtl.ScriptState & sMATS_SCRIPT_PASS) != 0) {
				MeshState = 0;
			}
		}
		if ((mtl.ScriptState & sMATS_SCRIPT_RENDLATTER) != 0) {
			MeshState |= sMATS_SCRIPT_RENDLATTER;
		}
		if ((mtl.ScriptState & sMATS_SCRIPT_CHECK_ICE) != 0) {
			MeshState |= sMATS_SCRIPT_CHECK_ICE;
		}
		if ((mtl.ScriptState & sMATS_SCRIPT_ORG_WATER) != 0) {
			MeshState = sMATS_SCRIPT_ORG_WATER;
		}

		// Blink Color
		if ((mtl.ScriptState & sMATS_SCRIPT_BLINK_COLOR) != 0
				&& WindMeshBottom == 0) {
			int cnt = 0;
			for (cnt = 0; cnt < MAX_MAP_BLINK_COLOR_TIME; cnt++) {
				if (mtl.strScript.contains(szBlinkTimeScript[cnt]))
					break;
			}

			if (cnt >= MAX_MAP_BLINK_COLOR_TIME)
				WindMeshBottom = dwBlinkTimeCode[0];
			else
				WindMeshBottom = dwBlinkTimeCode[cnt];
		}
		// save materials
		cache.put(key, material);

		return material;
	}

	private Texture loadTexture(String path) {
		Texture texture = null;
		try {
			texture = manager.loadTexture(path);
			texture.setWrap(WrapMode.Repeat);
		} catch (Exception ex) {
			texture = manager.loadTexture("Common/Textures/MissingTexture.png");
			texture.setWrap(WrapMode.Clamp);
		}
		return texture;
	}

	private void makeScript(Geometry geom, Mtl mtl) {
		int AnimCount = 0;
		if(mtl == null) return;
		if (mtl.strScript != null) {
			if ((mtl.ScriptState & sMATS_SCRIPT_ANIM2) != 0)
				AnimCount = 2;
			if ((mtl.ScriptState & sMATS_SCRIPT_ANIM4) != 0)
				AnimCount = 4;
			if ((mtl.ScriptState & sMATS_SCRIPT_ANIM8) != 0)
				AnimCount = 8;
			if ((mtl.ScriptState & sMATS_SCRIPT_ANIM16) != 0)
				AnimCount = 16;
		}
		if (AnimCount != 0) {
			geom.addControl(new ScriptControl(mtl.strScript, 8));
		}
	}

	/**
	 * Í¼Æ¬¶¯»­¿ØÖÆÆ÷
	 * 
	 * @author yanmaoyuan
	 * 
	 */
	class ScriptControl extends AbstractControl {
		// Í¼Æ¬Êý¾Ý
		private ArrayList<Texture> imgs = new ArrayList<Texture>();

		public ScriptControl(String script, int animCount) {
			if (animCount != 0) {
				int first = script.indexOf(":");
				int last = script.lastIndexOf(":");

				int FrameSpeed = 0;
				if (last != first) {
					String spd = script.substring(last + 1);
					FrameSpeed = Integer.parseInt(spd);
				}

				String bmp = null;
				if (last != first)
					bmp = script.substring(first + 1, last);
				else
					bmp = script.substring(first + 1);
				for (int i = 0; i < animCount; i++) {
					String tex = String.format(bmp, i);
					String name = key.getFolder() + tex;
					Texture texture = null;
					try {
						texture = manager.loadTexture(name);
						texture.setWrap(WrapMode.Repeat);
						imgs.add(texture);
					} catch (Exception ex) {
					}
					
				}
			}
		}

		float second = 0;
		float internal = 1 / 5f;
		@Override
		protected void controlUpdate(float tpf) {
			second += tpf;
			if (second > internal) {
				second -= internal;
				changeImage();
			}
		}

		int n = 0;

		private void changeImage() {
			if (spatial instanceof Geometry) {
				n++;
				if (n >= imgs.size()) {
					n = 0;
				}
				Geometry geom = (Geometry) spatial;
				geom.getMaterial().setTexture("DiffuseMap", imgs.get(n));
			}
		}

		@Override
		protected void controlRender(RenderManager rm, ViewPort vp) {
		}

		public ScriptControl clone() {
//			ScripteControl ctrl = new ScripteControl(script, animCount);
//			ctrl.spatial = spatial;
//			ctrl.imgs = imgs;
//			return ctrl;
			return this;
		}
	}

	/**
	 * Missing Material
	 * 
	 * @return
	 */
	protected Material getMissingMaterial() {
		if (missingMtl == null) {
			missingMtl = new Material(manager,
					"Common/MatDefs/Misc/Unshaded.j3md");
			missingMtl.setTexture("ColorMap",
					loadTexture("Common/Textures/MissingMaterial.png"));
		}
		return missingMtl;
	}

	/**
	 * You just can't see it
	 * 
	 * @return
	 */
	protected Material getAlphaMaterial() {
		if (alphaMtl == null) {
			alphaMtl = new Material(manager,
					"Common/MatDefs/Misc/Unshaded.j3md");
			RenderState rs = alphaMtl.getAdditionalRenderState();
			rs.setFaceCullMode(RenderState.FaceCullMode.FrontAndBack);
		}
		return alphaMtl;
	}

	/**
	 * White wire frame
	 * 
	 * @return
	 */
	protected Material getWireFrameMaterial() {
		if (wireframeMtl == null) {
			wireframeMtl = new Material(manager,
					"Common/MatDefs/Misc/Unshaded.j3md");
			wireframeMtl.setColor("Color", ColorRGBA.White);
			RenderState rs = wireframeMtl.getAdditionalRenderState();
			rs.setWireframe(true);
		}
		return wireframeMtl;
	}

	/*******************************
	 * 
	 * Generate JME3 bone & animation
	 * Under developint
	 * 
	 *******************************/

	protected void compileSkeleton(AseScene scene) {
		HashMap<String, Bone> boneMap = new HashMap<String, Bone>();
		List<Bone> boneList = new ArrayList<Bone>();
		for (GeomObject obj : scene.getObjects()) {
			if (!obj.isBone()) {
				// Not a bone??
				continue;
			}
			
			Bone bone = new Bone(obj.name);
			boneMap.put(obj.name, bone);
			boneList.add(bone);

			// I AM YOUR FATHER!!!
			if (obj.parent != null) {
				Bone parent = boneMap.get(obj.parent);
				parent.addChild(bone);
			}
			
			// Translation
			Vector3f position = new Vector3f(-obj.pos.y, obj.pos.z, -obj.pos.x);
			
			// Rotation
			Quaternion rotation = new Quaternion();
			Vector3f rotAxis = new Vector3f(-obj.rotAxis.y, obj.rotAxis.z, -obj.rotAxis.x);
			rotation.fromAngleAxis(-obj.rotAngle, rotAxis);
			
			// Scale
			Vector3f scale = new Vector3f(obj.scale.y, obj.scale.z, obj.scale.x);
			
			bone.setBindTransforms(position, rotation, scale);

			bone = null;
		}
		
		Bone[] bones = boneList.toArray(new Bone[boneList.size()]);
		Skeleton ske = new Skeleton(bones);

		AnimControl ac = new AnimControl(ske);
		rootNode.addControl(ac);

	}
	
	protected void compileAnimation(AseScene scene) {
		
		String name = "Anim";
		float length = scene.getAnimationLength();
		Animation anim = new Animation(name, length);

		// Calculate tracks
		Skeleton ske = rootNode.getControl(AnimControl.class).getSkeleton();
		for (GeomObject obj : scene.getObjects()) {
			if (!obj.isBone()) {
				// Not a bone??
				System.out.println(obj.name + " isn't a bone.");
				continue;
			}
			if (!obj.hasAnimation()) {
				// Don't have keyframes
				System.out.println(obj.name + " has not animation.");
				continue;
			}
			
			int targetBoneIndex = ske.getBoneIndex(obj.name);
			int size = obj.keyframes.size();
			float[] times = new float[size];
			Vector3f[] translations = new Vector3f[size];
			Quaternion[] rotations = new Quaternion[size];
			Vector3f[] scales = new Vector3f[size];
			
			System.out.println(obj.name + " keyframes:" + size);
			int i = 0;
			for(float time : obj.keyframes.keySet()) {
				times[i] = time;
				Keyframe frame = obj.keyframes.get(time);
				translations[i] = frame.translation;
				rotations[i] = frame.rotation.normalizeLocal();
				scales[i] = frame.scale;
				
				assert translations[i] != null;
				assert rotations[i] != null;
				assert scales[i] != null;
				
				i++;
			}
			
			assert i == size;
			
			BoneTrack track = new BoneTrack(targetBoneIndex, times, translations, rotations, scales);
			anim.addTrack(track);
		}
		
		AnimControl ac = rootNode.getControl(AnimControl.class);
		ac.addAnim(anim);
		
		SkeletonControl sc = new SkeletonControl(ske);
		rootNode.addControl(sc);
		
	}
	
	protected void bake(AseScene scene) {
		Node node = (Node)rootNode.getChild("BONES");
		Skeleton ske = rootNode.getControl(AnimControl.class).getSkeleton();
		
		/** skinning the model */
		for(Spatial child : node.getChildren()) {
			if (child instanceof Geometry) {
				Geometry geom = (Geometry)child;
				String name = geom.getName();
				skinning(geom.getMesh(), (byte)ske.getBoneIndex(name));
			}
		}
	}
	
	/**
	 * Skinning the mesh
	 * @param mesh
	 * @param targetBoneIndex
	 */
	private void skinning(Mesh mesh, byte targetBoneIndex) {
		if (targetBoneIndex == -1) return;
		
		// Calculate vertex count
		int limit = mesh.getBuffer(Type.Position).getData().limit();
		// Notice: i should call mesh.getMode() to decide how many 
		// floats is used for each vertex. Default mode is Mode.Triangles
		int vertexCount = limit/3;// by default
		
		int boneIndexCount = vertexCount * 4;
		byte[] boneIndex = new byte[boneIndexCount];
		float[] boneWeight = new float[boneIndexCount];

		// calculate bone indices and bone weights;
		for(int i=0; i<boneIndexCount; i+=4) {
			boneIndex[i] = targetBoneIndex;
			// I don't need the other 3 indices so I discard them
			boneIndex[i+1] = 0;
			boneIndex[i+2] = 0;
			boneIndex[i+3] = 0;
			
			boneWeight[i] = 1;
			// I don't need the other 3 indices so I discard them
			boneWeight[i+1] = 0;
			boneWeight[i+2] = 0;
			boneWeight[i+3] = 0;
		}
		mesh.setMaxNumWeights(1);
		
		// apply software skinning
		mesh.setBuffer(Type.BoneIndex, 4, boneIndex);
		mesh.setBuffer(Type.BoneWeight, 4, boneWeight);
		// apply hardware skinning
		mesh.setBuffer(Type.HWBoneIndex, 4, boneIndex);
		mesh.setBuffer(Type.HWBoneWeight, 4, boneWeight);

		mesh.generateBindPose(true);
	}
}
