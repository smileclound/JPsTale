package org.pstale.asset.base;

import java.io.IOException;
import java.io.InputStream;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

/**
 * @author yanmaoyuan
 *
 */
public abstract class AbstractLoader implements AssetLoader {
	public AssetManager manager = null;
	public AssetKey<?> key = null;
	
	public Material defaultMaterial;
	
	@Override
	public Object load(AssetInfo assetInfo) throws IOException {
		key = assetInfo.getKey();
		manager = assetInfo.getManager();
		
		return parse(assetInfo.openStream());
	}
	
	public abstract Object parse(InputStream inputStream) throws IOException;

	/**
	 * 改变文件的后缀名
	 * @param line
	 * @return
	 */
	public String changeName(String line) {
		int index = line.lastIndexOf("\\");
		if (index != -1) {
			line = line.substring(index + 1);
		}
		line = key.getFolder() + line;
		
		return line;
	}
	/**
	 * Loads the image to serve as a texture.
	 * 
	 * @param textureImageName
	 *            name of the image that is going to be set to be the texture.
	 */
	public Texture createTexture(String textureImageName) {
		Texture texture = null;
		try {
			texture = manager.loadTexture(key.getFolder() + textureImageName);
			texture.setWrap(WrapMode.Repeat);
		} catch (Exception ex) {
			System.err.println("Cannot load texture image " + textureImageName);
			texture = manager.loadTexture("Common/Textures/MissingTexture.png");
			texture.setWrap(WrapMode.EdgeClamp);
		}
		return texture;
	}
	
	/**
	 * 创建一个正方形
	 * @return
	 */
	public Geometry createBox() {
		Box box = new Box(1, 1, 1);
		Geometry geom = new Geometry("box", box);
		geom.setMaterial(getDefaultMaterial());
		return geom;
	}
	
	/**
	 * load a default material
	 * 
	 * @return
	 */
	public Material getDefaultMaterial() {
		if (defaultMaterial == null) {
			defaultMaterial = new Material(manager, "Common/MatDefs/Misc/Unshaded.j3md");
			defaultMaterial.setColor("Color", ColorRGBA.Green);
			defaultMaterial.getAdditionalRenderState().setWireframe(true);
		}

		return defaultMaterial;
	}
	
	/**
	 * load a light material
	 * 
	 * @return
	 */
	public Material getLightMaterial() {
		Material material = new Material(manager, "Common/MatDefs/Light/Lighting.j3md");
		material.setColor("Ambient", ColorRGBA.White);
		material.setColor("Diffuse", ColorRGBA.White);
		material.setColor("Specular", ColorRGBA.White);
		material.setColor("GlowColor", ColorRGBA.Black);
		material.setFloat("Shininess", 25f);
		material.setFloat("AlphaDiscardThreshold", 0.01f);

		return material;
	}
}
