/*==============================================================*/
/* Table: Creature                                              */
/*==============================================================*/
create table Creature
(
   LimitMax             int,
   OpenLimit            int,
   OpenInterval         int
);

/*==============================================================*/
/* Table: RespawnPoint                                          */
/*==============================================================*/
create table RespawnPoint
(
   x                    int,
   y                    int,
   enabled              bool
);

/*==============================================================*/
/* Table: BossList                                              */
/*==============================================================*/
create table BossList
(
   BossName             varchar(10),
   Slave                varchar(10),
   SlaveCnt             int,
   OpenTime             varchar(256)
);

/*==============================================================*/
/* Table: MonsterList                                           */
/*==============================================================*/
create table MonsterList
(
   MonName              varchar(10),
   Percentage           int
);
