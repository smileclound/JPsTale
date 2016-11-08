package org.pstale.asset.struct;

public class TEXTURE {
	public String Name;// [64];
	public String NameA;// [64];
	int Width, Height;
	int UsedTime;
	int UseCounter;// 这个变量是给缓存器的标志位，记录这个Texture是否已经使用。
	int MapOpacity; // 是否透明( TRUE , FALSE )
	int TexSwapMode; // ( TRUE / FALSE )
	TEXTURE TexChild;
}