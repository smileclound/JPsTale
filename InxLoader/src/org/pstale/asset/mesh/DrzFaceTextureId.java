package org.pstale.asset.mesh;

public class DrzFaceTextureId {
	public int a;
	public int b;
	public int c;
	public int material_id;

	public DrzFaceTextureId(int _a, int _b, int _c) {
		a = _a;
		b = _b;
		c = _c;
	}

	public DrzFaceTextureId() {
		a = 0;
		b = 0;
		c = 0;
		material_id = 0;
	}

	public DrzFaceTextureId(int _a, int _b, int _c, int _material_id) {
		a = _a;
		b = _b;
		c = _c;
		material_id = _material_id;
	}
}