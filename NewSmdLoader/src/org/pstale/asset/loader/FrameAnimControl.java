package org.pstale.asset.loader;

import java.util.ArrayList;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.control.AbstractControl;
import com.jme3.texture.Texture;

/**
 * 帧动画控制器
 * 精灵的部分动画是通过图片轮播来实现的。
 * @author yanmaoyuan
 *
 */
public class FrameAnimControl extends AbstractControl {
	// 图片数据
	ArrayList<Texture> animTexture;
	
	private int animTexCnt;// 动画帧数
	private int frameMask;// 
	private int shiftFrameSpeed;// 变换速度
	private int animationFrame;// 是否自动播放
	
	private float internal;
	public FrameAnimControl(int numTex) {
		this.animTexCnt = numTex;
		this.frameMask = numTex - 1;
		this.animTexture = new ArrayList<Texture>(numTex);
		this.shiftFrameSpeed = 6;
		this.internal = 0.1f;
	}
	
	private float time = 0;
	@Override
	protected void controlUpdate(float tpf) {
		time += tpf;
		if (time > internal) {
			time -= internal;
			changeImage();
		}
	}
	
	private int index = 0;
	private void changeImage() {
		if (spatial instanceof Geometry) {
			index++;
			if (index == animTexCnt) {
				index = 0;
			}
			Geometry geom = (Geometry) spatial;
			geom.getMaterial().setTexture("DiffuseMap", animTexture.get(index));
		}
	}
	
	@Override
	protected void controlRender(RenderManager rm, ViewPort vp) {}

}
