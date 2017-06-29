package org.pstale.asset.struct.chars;

public interface CharInfoConstant {

    public final static int fONE = 256;

    public final static int CHAR_STATE_NPC = 0;
    public final static int CHAR_STATE_ENEMY = 1;
    public final static int CHAR_STATE_USER = 0x80;

    public final static int CHAR_MONSTER_NATURAL = 0x80;
    public final static int CHAR_MONSTER_GOOD = 0x81;
    public final static int CHAR_MONSTER_EVIL = 0x82;

    public final static int CHAR_MONSTER_NORMAL = 0x00;
    public final static int CHAR_MONSTER_UNDEAD = 0x90;
    public final static int CHAR_MONSTER_MUTANT = 0x91;
    public final static int CHAR_MONSTER_DEMON = 0x92;
    public final static int CHAR_MONSTER_MECHANIC = 0x93;

    public final static int CHAR_MONSTER_USER = 0xA0;

    public final static int COMMNAD_USER_WARP = 0x100;
    public final static int COMMNAD_USER_AGINGUP = 0x110;
    public final static int COMMNAD_USER_HOLY_INCANTATION = 0x120;
    public final static int COMMNAD_USER_RESURRECTION = 0x130;
    public final static int COMMNAD_USER_FORCEORB = 0x140;
    public final static int COMMNAD_USER_VALENTO = 0x150;
    public final static int COMMNAD_USER_FIREFLOWER = 0x160;

    public final static int COMMAND_PLAY_BABEL = 0x200;
    public final static int COMMAND_PLAY_BELSS_CASTLE = 0x210;
    public final static int COMMAND_PLAY_PK_FIELD = 0x220;
    public final static int COMMAND_PLAY_KELVEZU = 0x230;

    public final static int FALLITEM_MAX = 200;
    public final static int FALLITEM2_MAX = 3;
    public final static int NPC_MESSAGE_MAX = 20;

    public final static int MONSTER_CLASS_NORMAL = 0;
    public final static int MONSTER_CLASS_BOSS = 1;
    public final static int MONSTER_CLASS_HAMMER = 200;
    public final static int MONSTER_CLASS_GHOST = 300;
}
