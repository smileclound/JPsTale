package net.jmecn.asset.item;

public class Item {

	public long CODE;
	String ItemNameIndex; // Item Name

	/*-------------------------*
	 *	It is set during initialization
	 *--------------------------*/
	public String LastCategory; // The final category
	int w;
	int h; // Item size
	String ItemFilePath; // Items loaded on a file path
	long Class; // The type of item
	String DorpItem; // Items when dropped
	long SetModelPosi; // Position the item to be set
	int SoundIndex; // Items sound
	int WeaponClass;

	// --------------------------

	int Flag; // Items Flag
	int x, y; // The item is drawn coordinates
	int SetX, SetY; // It binds to which the item is set

	// TODO LPDIRECTDRAWSURFACE4 lpItem; // Pointer necessary to load the picture
	int ItemPosition; // 装备位置（双手，右手，左手，盔甲...）

	int PotionCount; // 药水计数
	int NotUseFlag; // 禁用标识
	int SellPrice; // 物品出售价格

	int OldX, OldY;
	// TODO LPDIRECTDRAWSURFACE4 lpTempItem;

	ItemInfo sItemInfo;

	public Item() {
	}

	public Item(long cODE, String itemNameIndex, String lastCategory, int w,
			int h, String itemFilePath, long class1, String dorpItem,
			long setModelPosi, int soundIndex, int weaponClass) {
		super();
		CODE = cODE;
		ItemNameIndex = itemNameIndex;
		LastCategory = lastCategory;
		this.w = w;
		this.h = h;
		ItemFilePath = itemFilePath;
		Class = class1;
		DorpItem = dorpItem;
		SetModelPosi = setModelPosi;
		SoundIndex = soundIndex;
		WeaponClass = weaponClass;
	}
	
	public Item(long cODE, String itemNameIndex, String lastCategory, int w,
			int h, String itemFilePath, long class1, String dorpItem,
			long setModelPosi, int soundIndex) {
		super();
		CODE = cODE;
		ItemNameIndex = itemNameIndex;
		LastCategory = lastCategory;
		this.w = w;
		this.h = h;
		ItemFilePath = itemFilePath;
		Class = class1;
		DorpItem = dorpItem;
		SetModelPosi = setModelPosi;
		SoundIndex = soundIndex;
	}
}