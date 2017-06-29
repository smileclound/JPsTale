package org.pstale.asset.struct.item;

public class Item {

    public int code;
    public String name; // Item Name

    /*-------------------------*
     *	It is set during initialization
     *--------------------------*/
    public String category; // The final category
    public int width;
    public int height; // Item size
    public int Class; // The type of item
    public String folder; // Items loaded on a file path
    public String dorpItem; // Items when dropped
    public int SetModelPosi; // Position the item to be set
    public int sound; // Items sound
    public int WeaponClass;

    // --------------------------

    int Flag; // Items Flag
    int x, y; // The item is drawn coordinates
    int SetX, SetY; // It binds to which the item is set

    // TODO LPDIRECTDRAWSURFACE4 lpItem; // Pointer necessary to load the
    // picture
    int ItemPosition; // 装备位置（双手，右手，左手，盔甲...）

    int PotionCount; // 药水计数
    int NotUseFlag; // 禁用标识
    int SellPrice; // 物品出售价格

    int OldX, OldY;
    // TODO LPDIRECTDRAWSURFACE4 lpTempItem;

    // TODO ItemInfo sItemInfo;

    public Item() {
    }

    public Item(int cODE, String itemNameIndex, String lastCategory, int w, int h, String itemFilePath, int class1,
            String dorpItem, int setModelPosi, int soundIndex, int weaponClass) {
        this.code = cODE;
        this.name = itemNameIndex;
        this.category = lastCategory;
        this.width = w;
        this.height = h;
        this.Class = class1;
        this.folder = itemFilePath;
        this.dorpItem = dorpItem;
        this.SetModelPosi = setModelPosi;
        this.sound = soundIndex;
        this.WeaponClass = weaponClass;
    }

    public Item(int cODE, String itemNameIndex, String lastCategory, int w, int h, String itemFilePath, int class1,
            String dorpItem, int setModelPosi, int soundIndex) {
        this.code = cODE;
        this.name = itemNameIndex;
        this.category = lastCategory;
        this.width = w;
        this.height = h;
        this.folder = itemFilePath;
        this.Class = class1;
        this.dorpItem = dorpItem;
        this.SetModelPosi = setModelPosi;
        this.sound = soundIndex;
    }
}