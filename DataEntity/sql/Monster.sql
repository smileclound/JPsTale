/*==============================================================*/
/* Table: Monster                                               */
/*==============================================================*/
create table Monster
(
   id                   int,
   Name                 varchar(256),
   Clazz                int,
   Brood                int,
   Level                int,
   ActiveHour           int,
   RespawnGroup         int,
   AllSeeDrop           bool,
   Quantity             int,
   Exp                  int
);

/*==============================================================*/
/* Table: AI                                                    */
/*==============================================================*/
create table AI
(
   Nature               int,
   IQ                   int,
   Real_Sight           int,
   Talks                text,
   Move_Speed           float,
   MoveRange            int,
   SpAttackPercetage    int,
   SkillDamage          int,
   SkillDistance        int,
   SkillRange           int,
   SkillRating          int,
   SkillCurse           int,
   PotionPercent        int,
   PotionCount          int
);

/*==============================================================*/
/* Table: Drops                                                 */
/*==============================================================*/
create table Drops
(
   ItemCode             varchar(6),
   probability          int
);

/*==============================================================*/
/* Table: MoreDrops                                             */
/*==============================================================*/
create table MoreDrops
(
   ItemCode             varchar(6),
   probability          int
);