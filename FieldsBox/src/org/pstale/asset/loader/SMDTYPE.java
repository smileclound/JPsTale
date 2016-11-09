package org.pstale.asset.loader;

/**
 * 精灵的3D数据类型
 * @author yanmaoyuan
 *
 */
public enum SMDTYPE {
	/**
	 * 这种类型的smd文件保存了地图的数据。
	 */
	STAGE3D,
	/**
	 * 从地图数据中单独取出网格数据。
	 */
	STAGE3D_COLLISION,
	/**
	 * 从地图是中提取出可视部分。
	 */
	STAGE3D_VISUAL,
	/**
	 * 这种文件仅存储了PAT3D结构体中的骨骼动画，不包含任何材质。 后缀名为smb
	 */
	PAT3D,
	/**
	 * 这种文件用于存储了角色、怪物、NPC、舞台物体的数据，包含了网格、材质等数据。
	 */
	PAT3D_VISUAL,
	/**
	 * 这种文件和上一种类似，但是包含骨骼。 若文件名为 Field/iron/i2-bip04_ani.smd，骨骼文件为
	 * Field/iron/i2-bip04_ani.smb，2个文件只有后缀名不同，要先加载BONE，然后再加载PAT3D才能正确绑定骨骼。
	 */
	PAT3D_BIP,
	/**
	 * 这是角色、怪物、NPC等带有复杂动画模型的文件类型。先解析INX文件获得动画索引，然后再加载具体的模型。
	 */
	INX;
}