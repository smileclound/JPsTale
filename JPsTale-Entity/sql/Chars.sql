/*==============================================================*/
/* Table: Character                                             */
/*==============================================================*/
create table Character
(
   Name                 varchar(256),
   Brood                int,
   HeadModel            varchar(256),
   BodyModel            varchar(256),
   CharSoundCode        int,
   JobCode              int,
   Level                int,
   Strength             int,
   Spirit               int,
   Talent               int,
   Dexterity            int,
   Health               int,
   Exp                  int,
   next_exp             int,
   Money                int,
   Weight               int
);

/*==============================================================*/
/* Table: Job                                                   */
/*==============================================================*/
create table Job
(
   Name                 varchar(256),
   LocalName            varchar(20),
   BitCode              int,
   FieldCode            int,
   Brood                int,
   Sight                int,
   LifeFunc             int,
   ManaFunc             int,
   StaminaFunc          int,
   DamageFunc1          int,
   DamageFunc2          int,
   DamageFunc3          int
);