package org.pstale.asset.anim;

public class DrzSubAnimation {
	public int mAnimNodeNum;
	public DrzAnimationNode[] mAnimNode = null;

	public double mTicksPerSecond;
	public double mAllAniDurationTime;

	public void SetAnimationNodeNum(int _subaninum) {
		if (mAnimNode != null)
			mAnimNode = null;

		mAnimNodeNum = _subaninum;

		if (mAnimNodeNum > 0) {
			mAnimNode = new DrzAnimationNode[mAnimNodeNum];

			for (int i = 0; i < mAnimNodeNum; i++)
				mAnimNode[i] = new DrzAnimationNode();
		}
	}
}