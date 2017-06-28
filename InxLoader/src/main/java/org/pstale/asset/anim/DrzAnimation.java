package org.pstale.asset.anim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrzAnimation {
	public String mAnimationName;

	public HashMap<Integer, DrzAnimationSet> mAnimationSetMap = new HashMap<Integer, DrzAnimationSet>();

	public List<DrzInxMeshInfo> meshDefInfo = new ArrayList<DrzInxMeshInfo>();
	
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