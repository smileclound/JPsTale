/*==============================================================*/
/* Table: Item                                                  */
/*==============================================================*/
create table Item
(
   FieldCode            int,
   Name                 varchar(256),
   FinalCategory        varchar(6),
   height               int,
   size                 int,
   itemFilePath         varchar(10),
   class1               int,
   dorpItem             varchar(6),
   setModelPosi         Vector3f,
   soundIndex           int,
   weaponClass          int,
   ItemPosition         int
);

/*==============================================================*/
/* Table: ItemInfo                                              */
/*==============================================================*/
create table ItemInfo
(
   Name                 varchar(256),
   LocalName            varchar(20),
   Weight               int,
   Price                int,
   GenDay               date,
   UniqueItem           bool,
   EffectColor          ColorRGBA,
   EffectBlink          int,
   ScaleBlink           int,
   DispEffect           int
);

/*==============================================================*/
/* Table: "Require"                                             */
/*==============================================================*/
create table "Require"
(
   Level                int,
   Strength             int,
   Spirit               int,
   Talent               int,
   Dexterity            int,
   Health               int
);

/*==============================================================*/
/* Table: ItemSpecial                                           */
/*==============================================================*/
create table ItemSpecial
(
   Default_Job_Name     varchar(10),
   Random_Job_Name      varchar(256),
   Lev_Damage           int,
   Lev_Hit_Rating       int,
   Lev_Life             int,
   Lev_Mana             int,
   Lev_Sta              int
);
