package com.jme3.asset.maxase.material;

import java.io.Serializable;

import com.jme3.asset.maxase.CONSTANT;

public final class TextureMap implements CONSTANT, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7645945401570911332L;

	public String mapName;
	public String texName;

	public float uOffset = 0;
	public float vOffset = 0;

	public float uTiling = 1;
	public float vTiling = 1;

	public float angle = 0;
	public float blur = 0;

	public boolean equals(Object o) {
		if (!(o instanceof TextureMap)) {
			return false;
		}

		TextureMap map = (TextureMap) o;

		if ((mapName == null) != (map.mapName == null)) {
			return false;
		}
		if ((texName == null) != (map.texName == null)) {
			return false;
		}

		return (mapName == null || mapName.equals(map.mapName))
				&& (texName == null || texName.equals(map.texName))
				&& (uOffset == map.uOffset) && (vOffset == map.vOffset)
				&& (uTiling == map.uTiling) && (vTiling == map.vTiling)
				&& (angle == map.angle) && (blur == map.blur);
	}

	public int BitmapStageState = 0;
	public int BitmapFormState = 0;

	public void process() {
		for (int cnt = 0; cnt < BS_SCRIPT_MAX; cnt++) {
			if (mapName.contains(szMapStageScript[cnt])) {
				BitmapStageState = BsStageScript[cnt];
				break;
			}
		}
		for (int cnt = 0; cnt < FS_SCRIPT_MAX; cnt++) {
			if (mapName.contains(szMapFormScript[cnt])) {
				BitmapFormState = cnt;
				break;
			}
		}
	}

}