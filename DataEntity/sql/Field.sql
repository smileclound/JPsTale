/*==============================================================*/
/* Table: Field                                                 */
/*==============================================================*/
create table Field
(
   id                   int,
   Title                varchar(10),
   ModelName            varchar(256),
   MapName              varchar(256),
   TitleName            varchar(256),
   state                int,
   backImageCode1       int,
   backImageCode2       int,
   backImageCode3       int,
   backMusicCode        int,
   Event                int,
   posWarpOut           Vector3f,
   limitLevel           int,
   Center               Vector2f,
   FieldCode            int,
   ServerCode           int
);

/*==============================================================*/
/* Table: AmbientPos                                            */
/*==============================================================*/
create table AmbientPos
(
   x                    int,
   y                    int,
   z                    int,
   round                int,
   AmbientNum           int
);

/*==============================================================*/
/* Table: StageObject                                           */
/*==============================================================*/
create table StageObject
(
   ObjName              varchar(256),
   bipAnimation         bool
);

/*==============================================================*/
/* Table: StartPoint                                            */
/*==============================================================*/
create table StartPoint
(
   x                    int,
   y                    int
);

/*==============================================================*/
/* Table: FieldGate                                             */
/*==============================================================*/
create table FieldGate
(
   x                    int,
   y                    int,
   z                    int,
   toField              int
);

/*==============================================================*/
/* Table: WarpGate                                              */
/*==============================================================*/
create table WarpGate
(
   x                    int,
   y                    int,
   z                    int,
   height               int,
   size                 int,
   limitLevel           int,
   SpecialEffect        int
);