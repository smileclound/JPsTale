package org.pstale.asset.struct.item;

public class ItemRequire {
    public int level; // 等级
    public int strength; // 力量
    public int spirit; // 精神
    public int talent; // 才能
    public int dexterity; // 敏捷
    public int health; // 体质

    public ItemRequire(int lv, int str, int spirit, int talent, int dex, int vit) {
        super();
        this.level = lv;
        this.strength = str;
        this.spirit = spirit;
        this.talent = talent;
        this.dexterity = dex;
        this.health = vit;
    }

    public ItemRequire() {
    }

}