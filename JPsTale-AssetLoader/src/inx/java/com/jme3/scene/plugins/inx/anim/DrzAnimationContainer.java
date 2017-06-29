package com.jme3.scene.plugins.inx.anim;

public class DrzAnimationContainer {
	public int mSubAnimationNum;
	public DrzSubAnimation[] mSubAnimtion = null;

	public void SetSubAnimtionNum(int _mSubAnimationNum) {
		if (mSubAnimtion != null)
			mSubAnimtion = null;

		mSubAnimationNum = _mSubAnimationNum;

		if (mSubAnimationNum > 0) {
			this.mSubAnimtion = new DrzSubAnimation[mSubAnimationNum];

			for (int i = 0; i < mSubAnimationNum; i++)
				mSubAnimtion[i] = new DrzSubAnimation();
		}
	}
}