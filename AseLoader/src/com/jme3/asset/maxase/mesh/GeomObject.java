package com.jme3.asset.maxase.mesh;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.jme3.asset.maxase.animation.Keyframe;
import com.jme3.asset.maxase.animation.VertexAssignment;
import com.jme3.asset.maxase.material.Mtl;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix4f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class GeomObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2641177779889470194L;
	public String name;
	public String parent;

	/**
	 * Node Transform
	 */
	public Vector3f row0;
	public Vector3f row1;
	public Vector3f row2;
	public Vector3f row3;

	public Vector3f pos = new Vector3f(0, 0, 0);
	public Vector3f rotAxis;
	public float rotAngle;
	public Quaternion rotation = new Quaternion();

	public Vector3f scale = new Vector3f(1, 1, 1);
	public Vector3f scaleAxis;
	public float scaleAxisAngle;

	public Matrix4f getNodeTransfromMatrix4f() {
		return new Matrix4f(
				row0.x, row1.x, row2.x, row3.x, 
				row0.y, row1.y, row2.y, row3.y,
				row0.z, row1.z, row2.z, row3.z,
				0, 0, 0, 1);
	}

	// Mesh
	public int timevalue = 0;

	public List<Vector3f> verts = null;
	public List<Face> faces = null;
	public List<Vector2f> texCoords = null;
	public List<Face> uvFaces = null;
	public List<Vector3f> normals = null;
	public List<Face> vcFaces = null;
	public List<ColorRGBA> vcVerts = null;

	public int getVertexCount() {
		return (verts == null) ? 0 : verts.size();
	}

	public int getFaceCount() {
		return (faces == null) ? 0 : faces.size();
	}

	public int getTextureVertexCount() {
		return (texCoords == null) ? 0 : texCoords.size();
	}

	public int getTextureFaceCount() {
		return (uvFaces == null) ? 0 : uvFaces.size();
	}

	public int getNormalCount() {
		return (normals == null) ? 0 : normals.size();
	}

	public boolean hasMesh() {
		return getFaceCount() > 0;
	}

	public boolean hasTextureFace() {
		return getTextureFaceCount() > 0 && mtl != null;
	}

	public boolean hasNormal() {
		return getNormalCount() > 0;
	}

	// Material
	public Mtl mtl;
	public ColorRGBA wireframe;

	public boolean hasMaterial() {
		return mtl != null;
	}

	// Animation
	public boolean isBone() {
		return name.startsWith("Bip") || name.startsWith("Dummy");
	}

	public TreeMap<Float, Keyframe> keyframes;
	public ArrayList<VertexAssignment> assignments;

	public boolean hasAnimation() {
		return keyframes != null && keyframes.size() > 1;
	}

	@Override
	public GeomObject clone() {

		GeomObject obj = new GeomObject();

		obj.name = name;
		obj.parent = parent;

		// Node Transform
		obj.row0 = row0.clone();
		obj.row1 = row1.clone();
		obj.row2 = row2.clone();
		obj.row3 = row3.clone();

		obj.pos = pos.clone();
		obj.rotAxis = rotAxis.clone();
		obj.rotAngle = rotAngle;
		obj.rotation = rotation.clone();

		obj.scale = scale.clone();
		obj.scaleAxis = scaleAxis.clone();
		obj.scaleAxisAngle = scaleAxisAngle;

		// Mesh
		obj.timevalue = timevalue;
		obj.verts = verts;
		obj.faces = faces;
		obj.texCoords = texCoords;
		obj.uvFaces = uvFaces;
		obj.normals = normals;
		obj.vcFaces = vcFaces;
		obj.vcVerts = vcVerts;

		// Animation
		obj.assignments = assignments;
		obj.keyframes = keyframes;

		return obj;
	}
}