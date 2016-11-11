drop table if exists AI;

drop table if exists AmbientPos;

drop table if exists Attack;

drop table if exists BossList;

drop table if exists Character;

drop table if exists Container;

drop table if exists Creature;

drop table if exists Defense;

drop table if exists Drops;

drop table if exists Field;

drop table if exists FieldGate;

drop table if exists Increase;

drop table if exists Item;

drop table if exists ItemInfo;

drop table if exists ItemSpecial;

drop table if exists Job;

drop table if exists Monster;

drop table if exists MonsterList;

drop table if exists MoreDrops;

drop table if exists PotionEffect;

drop table if exists Regen;

drop table if exists "Require";

drop table if exists Resistance;

drop table if exists RespawnPoint;

drop table if exists Speed;

drop table if exists StageObject;

drop table if exists StartPoint;

drop table if exists WarpGate;

/*==============================================================*/
/* Table: Attack                                                */
/*==============================================================*/
create table Attack
(
   Damage               int,
   Shooting_Range       int,
   Attack_Speed         int,
   Attack_Rating        int,
   Critical_Hit         int,
   Magic_Matery         float
);

/*==============================================================*/
/* Table: Container                                             */
/*==============================================================*/
create table Container
(
   Space                int
);

/*==============================================================*/
/* Table: Defense                                               */
/*==============================================================*/
create table Defense
(
   Absorb               float,
   Defense              int,
   BlockRating          float,
   Flee                 int,
   DamageStunPers       int
);

/*==============================================================*/
/* Table: Increase                                              */
/*==============================================================*/
create table Increase
(
   Increase_Life        int,
   Increase_Mana        int,
   Increase_Stamina     int
);

/*==============================================================*/
/* Table: PotionEffect                                          */
/*==============================================================*/
create table PotionEffect
(
   Mana                 int,
   Life                 int,
   Stamina              int
);

/*==============================================================*/
/* Table: Regen                                                 */
/*==============================================================*/
create table Regen
(
   Mana_Regen           float,
   Life_Regen           float,
   Stamina_Regen        float
);

/*==============================================================*/
/* Table: Resistance                                            */
/*==============================================================*/
create table Resistance
(
   Bionic               int,
   Earth                int,
   Fire                 int,
   Ice                  int,
   Lighting             int,
   Poison               int,
   Water                int,
   Wind                 int
);


/*==============================================================*/
/* Table: Speed                                                 */
/*==============================================================*/
create table Speed
(
   Speed                float
);
