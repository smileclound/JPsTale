#smStage3d

##加载过程

###概述
首先加载的是FIELD的定义，这个过程是在客户端和服务器的代码中进行的，没有配置文件。

当玩家即将进入某个地区时，客户端会加载这个地图的模型。模型文件存储在每个FIELD对象实例中。

模型文件默认是ASE模型，但是客户端在加载一次ASE模型后，地图的数据会保存在smStage3d对象中，客户端会将其直接保存成一个smd文件，下次加载的时候可以节省解析ASE的时间。

###流程

1. 检测缓存中是否已经加载过这个地图模型
2. 实例化smStage3d对象
3. 设置光照角度、亮度、对比度
4. 读取地图主体数据`AddLoaderStage( lpstg , szStageFile );`
5. 读取舞台物体**可能存在**的多个`smStgObj`对象。根据是有含有动画的标志`Bip`，调用的读取函数不同。
6. 读取纹理、材质数据
7. 缓存地图

详细流程来自解析`playmain.cpp`源码。

	smSTAGE3D *LoadStageFromField( sFIELD *lpField , sFIELD *lpSecondField )
	{
		smSTAGE3D *lpstg;
		char *szStageFile;
		int cnt;
		int	Bip;
		char	szBuff[128];
	
		szStageFile = lpField->szName;
	
		if ( lpField==StageField[0] ) return smGameStage[0];
		if ( lpField==StageField[1] ) return smGameStage[1];
	
		lpstg = new smSTAGE3D;
	
		lpstg->VectLight.x = smConfig.MapLightVector.x;
		lpstg->VectLight.y = smConfig.MapLightVector.y;
		lpstg->VectLight.z = smConfig.MapLightVector.z;
	
		lpstg->Bright		= smConfig.MapBright;
		lpstg->Contrast		= smConfig.MapContrast;
	
		AddLoaderStage( lpstg , szStageFile );
	
		if ( !lpstg->StageObject ) {
			wsprintf( szBuff , "Stage Loading Error ( %s )",szStageFile );
			Record_ClinetLogFile( szBuff );
			delete lpstg;
			return NULL;
		}

		for( cnt=0;cnt<lpField->StgObjCount;cnt++ ) {
			Bip = lpField->GetStageObjectName( cnt , szBuff );
			if ( szBuff[0] ) {
				if ( Bip )
					lpstg->StageObject->AddObjectFile( szBuff , szBuff );
				else
					lpstg->StageObject->AddObjectFile( szBuff );
			}
		}
	
		if ( lpField->StgObjCount && lpD3DDevice ) {
			ReadTextures();
			lpstg->smMaterialGroup->CheckMatreialTextureSwap();	
		}
	
		if ( lpSecondField && lpSecondField==StageField[0] ) {
			if ( smGameStage[1] ) delete smGameStage[1];
			smGameStage[1] = lpstg;
			StageField[1] = lpField;
			LoadFieldMap( 1, lpField , lpstg );
		}
		else {
			if ( !lpSecondField || lpSecondField==StageField[1] ) {
				if ( smGameStage[0] ) delete smGameStage[0];
				smGameStage[0] = lpstg;
				StageField[0] = lpField;
				LoadFieldMap( 0, lpField , lpstg );
			}
		}
	
	
		if ( StageField[0] ) lstrcpy( szGameStageName[0] , StageField[0]->szName );
		if ( StageField[1] ) lstrcpy( szGameStageName[1] , StageField[1]->szName );
	
	
		DWORD	dwTime = GetCurrentTime();
	
		if ( dwLastRecvGameServerTime && dwLastRecvGameServerTime<dwTime ) dwLastRecvGameServerTime=dwTime;
		if ( dwLastRecvGameServerTime2 && dwLastRecvGameServerTime2<dwTime ) dwLastRecvGameServerTime2 = dwTime;
		if ( dwLastRecvGameServerTime3 && dwLastRecvGameServerTime3<dwTime ) dwLastRecvGameServerTime3 = dwTime;
		if ( dwLastRecvGameServerTime4 && dwLastRecvGameServerTime4<dwTime ) dwLastRecvGameServerTime4 = dwTime;
	
		if ( AreaServerMode ) {
			if ( lpWSockServer_Area[0] ) lpWSockServer_Area[0]->dwDeadLockTime = dwTime;
			if ( lpWSockServer_Area[1] ) lpWSockServer_Area[1]->dwDeadLockTime = dwTime;
		}
	
		return lpstg;
	}

###保存过程

地图模型的存储函数记录在`smlib3d/smStage3d.cpp`源码中，内存中的smSTAGE3D对象被保存到一个smd文件中。该文件只有网格和材质，没有动画数据。

	static char *szSMDFileHeader = "SMD Stage data Ver 0.72";
	
	int	smSTAGE3D::SaveFile( char *szFile )
	{
		HANDLE	hFile;
		DWORD	dwAcess;
		int		cnt,cnt2,slen;
		int		pFile;
	//	int		size;
	
		smDFILE_HEADER	FileHeader;
	
		lstrcpy( FileHeader.szHeader , szSMDFileHeader );
	
		Head = FALSE;
	
		if ( smMaterialGroup )
			FileHeader.MatCounter = smMaterialGroup->MaterialCount;
		else
			FileHeader.MatCounter = 0;
	
	
		pFile  = sizeof( smDFILE_HEADER );// + sizeof( smPAT3D );
	
		FileHeader.MatFilePoint = pFile;
	
		if ( smMaterialGroup ) 
			pFile+= smMaterialGroup->GetSaveSize();
	
		FileHeader.First_ObjInfoPoint = pFile;
	
		hFile = CreateFile( szFile , GENERIC_WRITE , FILE_SHARE_READ|FILE_SHARE_WRITE, NULL, OPEN_ALWAYS , FILE_ATTRIBUTE_NORMAL , NULL );
		if ( hFile == INVALID_HANDLE_VALUE ) return FALSE;
	
		WriteFile( hFile , &FileHeader , sizeof( smDFILE_HEADER ) , &dwAcess , NULL );
	
		WriteFile( hFile , &Head	, sizeof( smSTAGE3D )				, &dwAcess , NULL );
	
		if ( smMaterialGroup ) smMaterialGroup->SaveFile( hFile );
	
		WriteFile( hFile , Vertex	, sizeof( smSTAGE_VERTEX )* nVertex	, &dwAcess , NULL );
		WriteFile( hFile , Face		, sizeof( smSTAGE_FACE )* nFace		, &dwAcess , NULL );
		WriteFile( hFile , TexLink	, sizeof( smTEXLINK )	* nTexLink	, &dwAcess , NULL );
		if ( nLight>0 ) 
			WriteFile( hFile , smLight	, sizeof( smLIGHT3D )	* nLight	, &dwAcess , NULL );
	
		for( cnt2=0;cnt2<MAP_SIZE;cnt2++) {
			for( cnt=0;cnt<MAP_SIZE;cnt++ ) {
				if (StageArea[ cnt ][ cnt2 ]) {
					slen = (StageArea[cnt][cnt2][0]+1) ;
					WriteFile( hFile , &slen , sizeof(int) , &dwAcess , NULL );
					WriteFile( hFile , StageArea[cnt][cnt2]	, slen * sizeof( WORD ), &dwAcess , NULL );
				}
			}
		}
	
		CloseHandle( hFile );
	
		return TRUE;
	}

读取过程是反过来的：

	int	smSTAGE3D::LoadFile( char *szFile )
	{
		HANDLE	hFile;
		DWORD	dwAcess;
		int		cnt,cnt2;
		int		size;
		int		slen;
		int		wbCnt;
		smTEXLINK	*lpOldTexLink;
		int		SubTexLink;
	
		smDFILE_HEADER	FileHeader;
	
		hFile = CreateFile( szFile , GENERIC_READ , FILE_SHARE_READ|FILE_SHARE_WRITE, NULL, OPEN_EXISTING , FILE_ATTRIBUTE_NORMAL , NULL );
	
		size=ReadFile( hFile , &FileHeader , sizeof( smDFILE_HEADER ) , &dwAcess , NULL );
	
		if ( lstrcmp( FileHeader.szHeader , szSMDFileHeader )!=0 ) {
			CloseHandle( hFile );
			return FALSE;
		}
	
		ReadFile( hFile , &Head			, sizeof( smSTAGE3D )				, &dwAcess , NULL );
		lpOldTexLink = TexLink;
	
		if ( FileHeader.MatCounter ) {
			smMaterialGroup = new smMATERIAL_GROUP;
			smMaterialGroup->LoadFile( hFile );
			smMaterial = smMaterialGroup->smMaterial;
		}
	
		Vertex	= new smSTAGE_VERTEX[ nVertex ];
		ReadFile( hFile , Vertex	, sizeof( smSTAGE_VERTEX ) * nVertex	, &dwAcess , NULL );
	
		Face	= new smSTAGE_FACE[ nFace ];
		ReadFile( hFile , Face		, sizeof( smSTAGE_FACE ) * nFace		, &dwAcess , NULL );
	
		TexLink = new smTEXLINK[ nTexLink ];
		ReadFile( hFile , TexLink	, sizeof( smTEXLINK ) * nTexLink		, &dwAcess , NULL );
	
		if ( nLight>0 ) {
			smLight = new smLIGHT3D[nLight];
			ReadFile( hFile , smLight	, sizeof( smLIGHT3D ) * nLight		, &dwAcess , NULL );
		}
	
	
		SubTexLink = TexLink-lpOldTexLink;
	
		for( cnt=0;cnt<nTexLink;cnt++) {
			if ( TexLink[cnt].NextTex ) {
				SubTexLink = TexLink[cnt].NextTex-lpOldTexLink;
				TexLink[cnt].NextTex = TexLink + SubTexLink;
			}
		}
		for( cnt=0;cnt<nFace;cnt++) {
			if ( Face[cnt].lpTexLink ) {
				SubTexLink = Face[cnt].lpTexLink-lpOldTexLink;
				Face[cnt].lpTexLink = TexLink + SubTexLink;
			}
		}
	
		StageObject	=	new smSTAGE_OBJECT;
	
		lpwAreaBuff = new WORD[ wAreaSize ];
		wbCnt = 0;
	
		for( cnt2=0;cnt2<MAP_SIZE;cnt2++) {
			for( cnt=0;cnt<MAP_SIZE;cnt++ ) {
				if (StageArea[ cnt ][ cnt2 ]) {
					ReadFile( hFile , &slen	, sizeof(int) , &dwAcess , NULL );
					StageArea[cnt][cnt2] = &lpwAreaBuff[ wbCnt ];
					ReadFile( hFile , StageArea[cnt][cnt2]	, slen*sizeof(WORD) , &dwAcess , NULL );
					wbCnt += slen;
				}
			}
		}
	
		CloseHandle( hFile );
	
		CalcSumCount++; 
	
		return TRUE;
	}

##数据结构
###smSTAGE3D数据结构
说明：smStage3D类中没有虚函数，不用考虑成员函数占的内存，因此数据结构只列出了成员变量的定义。
数据结构：

	#define ADD_TEMPFACE	2048
	#define ADD_TEMPVERTEX	2048
	#define MAP_SIZE		256
	
	class smSTAGE3D {
	public:
		DWORD	Head;
	
		WORD *StageArea[MAP_SIZE][MAP_SIZE];	//备开喊 其捞胶 蔼 滚欺 府胶飘
		POINT *AreaList;						//备开喊 其捞胶 府胶飘 烙矫历厘 
		int AreaListCnt;						//备开 其捞胶狼 目款磐
	
		int	MemMode;							//皋葛府 汲沥 葛靛 ( 抗沥 )
	
		DWORD SumCount;							// 坊歹傅 媒农 墨款磐 
		int CalcSumCount;						// 楷魂 锅龋 墨款磐
	
		smSTAGE_VERTEX		*Vertex;			//滚咆胶 府胶飘
		smSTAGE_FACE		*Face;				//其捞胶 府胶飘
		smTEXLINK			*TexLink;			//咆胶媚 谅钎 府胶飘
		smLIGHT3D			*smLight;			//炼疙 汲沥
	
		smMATERIAL_GROUP	*smMaterialGroup;	//皋飘府倔 弊缝
	
		smSTAGE_OBJECT		*StageObject;		//甘狼 备开惑狼 坷宏璃飘甸
		smMATERIAL			*smMaterial;		//皋飘府倔 府胶飘 器牢磐
	
		int nVertex;							//滚咆胶 荐
		int nFace;								//其捞胶 荐
		int nTexLink;							//咆胶媚谅钎 楷搬 荐
		int	nLight;								//炼疙 荐
	
		int	nVertColor;								//滚咆胶 祸 涝仿 墨款磐
	
		int	Contrast;								//盲档 ( 急疙档 )
		int Bright;									//疙档 ( 灌扁 沥档 )
	
		POINT3D VectLight;							//溅捞靛侩 扼捞飘 氦磐
	
		WORD	*lpwAreaBuff;						//备开喊 历厘 滚欺
		int		wAreaSize;							//备开喊 历厘 滚欺 农扁
		RECT	StageMapRect;						//傈眉 STAGE啊 粮犁窍绰 备开 困摹
	};


###smSTAGE3D构造方法

	smSTAGE3D::smSTAGE3D()
	{
		VectLight.x = fONE;
		VectLight.y = -fONE;
		VectLight.z = fONE/2;
	
		Bright		= DEFAULT_BRIGHT;
		Contrast	= DEFAULT_CONTRAST;
	
		Head = FALSE;
	
		smLight = 0;
		nLight = 0;
		MemMode = 0;
	}
	
	
	smSTAGE3D::smSTAGE3D( int nv , int nf )
	{
		VectLight.x = fONE;
		VectLight.y = -fONE;
		VectLight.z = fONE/2;
	
		Bright		= DEFAULT_BRIGHT;
		Contrast	= DEFAULT_CONTRAST;
	
		Head = FALSE;
	
		smLight = 0;
		nLight = 0;
		MemMode = 0;
	
		Init( nv, nf );
	}
	
	void smSTAGE3D::Init(void)
	{
		nLight   = 0;
		nTexLink = 0;
		nFace    = 0;
		nVertex  = 0;
	
		::ZeroMemory( StageArea, sizeof(StageArea) );
	
		AreaList		= NULL;
		Vertex			= NULL;
		Face			= NULL;
		TexLink			= NULL;
		smLight			= NULL;
		smMaterialGroup = NULL;
		StageObject     = NULL;
		smMaterial      = NULL;
		lpwAreaBuff     = NULL;
	}
	
	int smSTAGE3D::Init( int nv , int nf )
	{
		Vertex	=		new smSTAGE_VERTEX[ nv ]; 
		Face	=		new smSTAGE_FACE[ nf ];
		TexLink =		new smTEXLINK[ nf * 4 ];
		StageObject	=	new smSTAGE_OBJECT;
	
		nVertex		= 0;
		nFace		= 0;
		nTexLink	= 0;
	
		nVertColor	= 0;
	
		SumCount = 0;
		CalcSumCount = 0;
	
		smMaterialGroup	= 0;
		lpwAreaBuff	= 0;
	
		StageMapRect.top = 0;
		StageMapRect.bottom = 0;
		StageMapRect.left = 0;
		StageMapRect.right = 0;
	
		clearStageArea();
	
		return TRUE;
	}

###smStgObj数据结构


	#define		MAX_PATTERN		256
	#define		MAX_STAGEOBJ		1024
	
	
	struct smSTAGE_OBJ3D {
	
		smPAT3D *BipPattern;
		smPAT3D *Pattern;
		POINT3D Posi;
		POINT3D Angle;
	
		smSTAGE_OBJ3D *LinkObj;
		int sum;
		int LastDrawTime;
	
	};
	
	
	class smSTAGE_OBJECT {
	public:
		smSTAGE_OBJ3D *ObjectMap[MAP_SIZE][MAP_SIZE];
	
		smSTAGE_OBJ3D mObj[ MAX_STAGEOBJ ];
		int nObj;
		POINT3D Camera;
		POINT3D CameraAngle;
	
		int SumCnt;
	};

###smStgObj构造方法

	smSTAGE_OBJECT::smSTAGE_OBJECT()
	{
		int x,y;
		nObj = 0;
	
		for(y=0;y<MAP_SIZE;y++) {
			for(x=0;x<MAP_SIZE;x++) {
				ObjectMap[x][y] = 0;
			}
		}
	
		SumCnt = 0;
	}

###smSTAGE_OBJECT::AddObjectFile
一个在读取smSTAGE3D时被调用的函数。

	int smSTAGE_OBJECT::AddObjectFile( char *szFile , char *szBipFile )
	{
		int w,h;
		smSTAGE_OBJ3D *obj;
		smPAT3D *Pat;
		smPAT3D *BipPat;
		int x,y,z;
	
		if ( nObj>=MAX_STAGEOBJ ) return FALSE;
	
		if ( szBipFile ) {
			smASE_SetPhysique( 0 );
			BipPat = smASE_ReadBone( szBipFile );
			smASE_SetPhysique( BipPat );
			if ( !BipPat ) return FALSE;
		}
		else {
			smASE_SetPhysique( 0 );
			BipPat = 0;
		}
	
		Pat = smASE_Read( szFile );
		if ( !Pat ) {
			if ( BipPat ) {
				delete BipPat;
				BipPat = 0;
			}
			return FALSE;
		}
	
		mObj[ nObj ].Pattern = Pat;
		mObj[ nObj ].BipPattern = BipPat;
	
		x = Pat->obj3d[0]->Tm._41;
		y = Pat->obj3d[0]->Tm._43;
		z = Pat->obj3d[0]->Tm._42;
	
		mObj[ nObj ].Posi.x = x;
		mObj[ nObj ].Posi.y = y;
		mObj[ nObj ].Posi.z = z;
	
		mObj[ nObj ].Angle.x = 0;
		mObj[ nObj ].Angle.y = 0;
		mObj[ nObj ].Angle.z = 0;
	
		mObj[ nObj ].sum = 0;
		mObj[ nObj ].LinkObj = 0;
		mObj[ nObj ].LastDrawTime = GetCurrentTime();
	
		w = (x>>(6+FLOATNS))&0xFF;
		h = (z>>(6+FLOATNS))&0xFF;
	
		if ( ObjectMap[w][h]==0 ) {
			ObjectMap[w][h] = &mObj[nObj];
		}
		else {
			obj = ObjectMap[w][h];
			while( obj!=0 ) {
				if ( obj->LinkObj==0 ) break;
				obj = obj->LinkObj;
			}
			obj->LinkObj = &mObj[nObj];
		}
	
		nObj++;
	
		return TRUE;
	}

##公共部分

###smRead3d.h

	char *GetString(char *q , char *p);
	char *GetWord(char *q , char *p);
	//颇老 捞抚栏肺 何磐 叼泛配府甫 茫酒 汲沥
	char *SetDirectoryFromFile( char *filename );
	
	//颇老 茫扁
	char *smFindFile( char *szfile , char *FileExt=0 , DWORD *lpFileLen=0 );
	
	//颇老疙狼 犬厘磊甫 函版茄促
	char *ChangeFileExt( char *filename , char *FileExt );
	
	//乔瘤农 汲沥 蔼阑 汲沥 ( 葛胆 颇老俊 利侩瞪 )
	void  smASE_SetPhysique( smPAT3D *p );
	//乔瘤农 汲沥 蔼阑 备窃 ( 葛胆 颇老俊 利侩瞪 )
	smPAT3D *smASE_GetPhysique();
	
	smPAT3D *smASE_ReadBone( char *file );
	smPAT3D *smASE_Read( char *file , char *szModelName=0 );
	smPAT3D* smASE_TalkReadBone( char *file );
	
	
	//葛胆 俊聪皋捞记 单捞鸥甫 根顶 钦模促
	smPAT3D *smASE_MergeBone( char *szMeshFile, char **FileList, int FileCnt, int ReadType = 1 );
	
	int smMAP3D_ReadASE( char *file );
	
	//公措 葛胆 颇老阑 佬绢 柯促
	smSTAGE3D *smSTAGE3D_ReadASE( char *file , smSTAGE3D *smStagep = NULL );
	
	//磊悼 Mesh单捞鸥 历厘/犁肺靛 汲沥
	void smSetMeshReload( int flag , int MeshSave =0 );
	
	#define sMATS_SCRIPT_WIND		1
	#define sMATS_SCRIPT_ANIM2		2
	#define sMATS_SCRIPT_ANIM4		4
	#define sMATS_SCRIPT_ANIM8		8
	#define sMATS_SCRIPT_ANIM16		16
	#define sMATS_SCRIPT_WINDZ1		0x0020
	#define sMATS_SCRIPT_WINDZ2		0x0040
	#define sMATS_SCRIPT_WINDX1		0x0080
	#define sMATS_SCRIPT_WINDX2		0x0100
	#define sMATS_SCRIPT_WATER		0x0200
	#define sMATS_SCRIPT_NOTVIEW	0x0400
	#define sMATS_SCRIPT_PASS		0x0800
	#define sMATS_SCRIPT_NOTPASS	0x1000
	#define sMATS_SCRIPT_RENDLATTER	0x2000
	#define sMATS_SCRIPT_BLINK_COLOR		0x4000
	#define sMATS_SCRIPT_CHECK_ICE			0x8000
	#define sMATS_SCRIPT_ORG_WATER			0x10000

###smRead3d.cpp
全部的代码太复杂，这里只记录跟smd和smb有关的文件。

	char *szFileModelBip = "smb";
	char *szFileModel = "smd";
	
	//颇老 茫扁
	char *smFindFile( char *szfile , char *FileExt , DWORD *lpFileLen )
	{
		HANDLE hFindHandle;
		WIN32_FIND_DATA	fd;
		WIN32_FIND_DATA	fd2;
	
		char *szFileName;
	
	
		if ( FileExt ) {
			//犬厘磊 函版 秦辑 茫澜
			szFileName = ChangeFileExt( szfile , FileExt );
		}
		else
			szFileName = szfile;
	
		//颇老阑 茫澜( 犬厘磊 函版 颇老 )
		hFindHandle = FindFirstFile( szFileName , &fd );
		if ( hFindHandle==INVALID_HANDLE_VALUE ) {
			FindClose( hFindHandle );
			return FALSE;
		}
		FindClose( hFindHandle );
	
		if ( lpFileLen ) *lpFileLen = fd.nFileSizeLow;
	
		//厚背且 颇老捞 绝绰 版快 颇老捞 窍唱牢 版快烙
		if ( !FileExt ) {
	 		return szFileName;
		}
	
		//盔夯 颇老阑 茫澜
		hFindHandle = FindFirstFile( szfile , &fd2 );
		if ( hFindHandle==INVALID_HANDLE_VALUE ) {
			FindClose( hFindHandle );
			return	szFileName;
		}
	
		//盔夯颇老捞 货肺 盎脚 官诧 颇老牢 版快 颇老茫扁 角菩肺 倒妨淋
		if ( CompareFileTime( &fd.ftLastWriteTime , &fd2.ftLastWriteTime )<0 ) {
			return FALSE;
		}
	
		return szFileName;
	}
	
	
	
	////////////////////////////////////////////////////////////
	////////// SMD/SMB颇老阑 阂矾甸咯 PAT3D肺 父电促 ///////////
	////////////////////////////////////////////////////////////
	
	smPAT3D *smReadModel( char *file , char *szModelName )
	{
		smPAT3D *pat;
		int result;
	
		pat	= new smPAT3D;
	
		result = pat->LoadFile( file , szModelName );
	
		if ( result==FALSE ) {
			delete pat;
			return NULL;
		}
	
		return pat;
	}
	
	smPAT3D *smReadModel_Bip( char *file )
	{
		smPAT3D *pat;
		int result;
	
		pat	= new smPAT3D;
	
		result = pat->LoadFile( file );
	
		if ( result==FALSE ) {
			delete pat;
			return NULL;
		}
	
		return pat;
	}

###smPAT3D

	
	class smPAT3D {
		DWORD		Head;
	public:
		smOBJ3D		*obj3d[128];
		BYTE		TmSort[128];					// 俊聪皋捞记 拌魂 鉴辑 (傅农 饭骇鉴)
	
		smPAT3D		*TmParent;
	
		smMATERIAL_GROUP	*smMaterialGroup;		//皋飘府倔 弊缝
	
		int MaxFrame;
		int Frame;
	
		int SizeWidth , SizeHeight;					// 臭捞 承捞 狼 弥措摹 
	
		int nObj3d;
		LPDIRECT3DTEXTURE2 *hD3DTexture;
	
		POINT3D Posi;
		POINT3D Angle;
		POINT3D CameraPosi;
	
		int dBound;							// 官款爹 胶其绢 蔼 ^2
		int Bound;							// 官款爹 胶其绢 蔼
	
		smFRAME_POS TmFrame[OBJ_FRAME_SEARCH_MAX];		//咯矾俺狼 橇饭烙颇老 楷搬 沥焊烙
		int TmFrameCnt;
	
		int		TmLastFrame;
		POINT3D TmLastAngle;
	};

###读写smPAT3D

	static char *szSMDFileHeader = "SMD Model data Ver 0.62";
	
	//单捞鸥甫 颇老肺 历厘
	int	smPAT3D::SaveFile( char *szFile )
	{
	
		HANDLE	hFile;
		DWORD	dwAcess;
		int		cnt;
		int		pFile;
		int		size;
	
		smDFILE_HEADER	FileHeader;
		smDFILE_OBJINFO *FileObjInfo;
	
	
		lstrcpy( FileHeader.szHeader , szSMDFileHeader );
	
		//庆歹 力累
		if ( smMaterialGroup )
			FileHeader.MatCounter = smMaterialGroup->MaterialCount;
		else
			FileHeader.MatCounter = 0;
	
		FileHeader.ObjCounter = nObj3d;
	
	
		FileObjInfo = new smDFILE_OBJINFO [ nObj3d ];
	
		//颇老 器牢磐
		pFile  = sizeof( smDFILE_HEADER ) + sizeof( smDFILE_OBJINFO ) * nObj3d;// + sizeof( smPAT3D );
	
		FileHeader.MatFilePoint = pFile;
	
		//皋飘府倔 单捞鸥 历厘 农扁
		if ( smMaterialGroup ) 
			pFile+= smMaterialGroup->GetSaveSize();
	
		FileHeader.First_ObjInfoPoint = pFile;
	
		FileHeader.TmFrameCounter = TmFrameCnt;
		memcpy( FileHeader.TmFrame , TmFrame , sizeof( smFRAME_POS ) * OBJ_FRAME_SEARCH_MAX );
	
	
		//器窃茄 OBJ器牢磐尔 沥焊 包访 喉钒
		for( cnt=0;cnt<nObj3d;cnt++) {
			lstrcpy( FileObjInfo[cnt].szNodeName , obj3d[cnt]->NodeName );
			size = obj3d[cnt]->GetSaveSize();
			FileObjInfo[cnt].ObjFilePoint = pFile;
			FileObjInfo[cnt].Length = size;
	
			pFile += size;
		}
	
		//颇老肺 历厘
		hFile = CreateFile( szFile , GENERIC_WRITE , FILE_SHARE_READ|FILE_SHARE_WRITE, NULL, OPEN_ALWAYS , FILE_ATTRIBUTE_NORMAL , NULL );
		if ( hFile == INVALID_HANDLE_VALUE ) return FALSE;
	
		//庆歹 历厘
		WriteFile( hFile , &FileHeader , sizeof( smDFILE_HEADER ) , &dwAcess , NULL );
		WriteFile( hFile , FileObjInfo , sizeof( smDFILE_OBJINFO ) * nObj3d, &dwAcess , NULL );
		//WriteFile( hFile , &Head , sizeof( smPAT3D ), &dwAcess , NULL );
	
		//皋飘府倔 单捞鸥 历厘
		if ( smMaterialGroup ) smMaterialGroup->SaveFile( hFile );
	
		//坷宏璃飘 单捞鸥 历厘
		for( cnt=0;cnt<nObj3d;cnt++) {
			size = obj3d[cnt]->SaveFile( hFile );
			if ( !size ) {		//俊矾促!
				CloseHandle( hFile );
				delete FileObjInfo;
				return FALSE;
			}
		}
	
		//勤甸 摧备 辆丰
		CloseHandle( hFile );
		delete FileObjInfo;
	
		return TRUE;
	}
	
	
	//颇老俊辑 单捞鸥甫 肺靛
	int	smPAT3D::LoadFile( char *szFile , char *szNodeName )
	{
		HANDLE	hFile;
		DWORD	dwAcess;
		int		cnt;
		int		size;
		smOBJ3D	*obj;
		smPAT3D *BipPat;
	
		smDFILE_HEADER	FileHeader;
		smDFILE_OBJINFO *FileObjInfo;
	
		Init();
	
		BipPat = smASE_GetPhysique();
	
		hFile = CreateFile( szFile , GENERIC_READ , FILE_SHARE_READ|FILE_SHARE_WRITE, NULL, OPEN_EXISTING , FILE_ATTRIBUTE_NORMAL , NULL );
	
		//弊缝狼 郴侩阑 佬绢咳
		size=ReadFile( hFile , &FileHeader , sizeof( smDFILE_HEADER ) , &dwAcess , NULL );
	
		//庆歹啊 撇覆 ( 滚傈捞 撇府芭唱.. )
		if ( lstrcmp( FileHeader.szHeader , szSMDFileHeader )!=0 ) {
			//勤甸 摧备 辆丰
			CloseHandle( hFile );
			return FALSE;
		}
	
		FileObjInfo = new smDFILE_OBJINFO [ FileHeader.ObjCounter ];
		size=ReadFile( hFile , FileObjInfo , sizeof( smDFILE_OBJINFO ) * FileHeader.ObjCounter, &dwAcess , NULL );
	
		TmFrameCnt = FileHeader.TmFrameCounter;
		memcpy( TmFrame , FileHeader.TmFrame , sizeof( smFRAME_POS ) * OBJ_FRAME_SEARCH_MAX );
	
		//皋飘府倔 佬绢咳
		if ( FileHeader.MatCounter ) {
			smMaterialGroup = new smMATERIAL_GROUP;
			smMaterialGroup->LoadFile( hFile );
		}
	
		if ( szNodeName ) {
			//坷宏璃飘 1俺父 阂矾咳
			for(cnt=0;cnt<FileHeader.ObjCounter;cnt++) {
				if ( lstrcmpi( szNodeName , FileObjInfo[cnt].szNodeName )==0 ) {
					obj = new smOBJ3D;
					if ( obj ) {
						SetFilePointer( hFile , FileObjInfo[cnt].ObjFilePoint , NULL , FILE_BEGIN );
						obj->LoadFile( hFile , BipPat );
						AddObject( obj );
					}
					break;
				}
			}
		}
		else {
			//葛电 坷宏璃飘 阂矾咳
			for(cnt=0;cnt<FileHeader.ObjCounter;cnt++) {
				obj = new smOBJ3D;
				if ( obj ) {
					obj->LoadFile( hFile , BipPat );
					AddObject( obj );
				}
			}
			LinkObject();
		}
	
		TmParent = BipPat;
	
		//勤甸 摧备 辆丰
		CloseHandle( hFile );
		delete FileObjInfo;
	
		return TRUE;
	}

###smObj3d
	
	#define OBJ_FRAME_SEARCH_MAX		32
	#define OBJ_HEAD_TYPE_NEW_NORMAL				0x80000000
	
	// 坊歹傅侩 滚咆胶 器牢磐
	class smPOINT3D {
	public:
	
		smVERTEX	*pVertex;			// 谅钎 器牢飘
	
		int			rx , ry , rz;		// 雀傈 谅钎
		int			wx , wy , wz;		// 岿靛 谅钎
		int			sx , sy , sz;
		int			ox , oy , oz;
	
		int			X,Y,Z;				// 墨皋扼 矫痢 谅钎
		int			x2d,y2d;			//坊歹傅 瞪 2D 谅钎
		BYTE		Clip2d[4];			//拳搁观 滚咆胶 犬牢
	
		smPOINT3D();
		smPOINT3D( smVERTEX *pv );
		~smPOINT3D();
	
		void SetTo( smVERTEX *pv );
		void xform2d();
		void GlobalRotate(int *trig);
		//   瘤开 吝缴 谅钎 捞悼
		void Move(int dx, int dy, int dz);
		//   墨皋扼 谅钎
		void GlobalXform();
	
	};
	
	
	//TM 器瘤记 
	struct smFRAME_POS {
		int StartFrame;
		int EndFrame;
		int PosNum;
		int PosCnt;
	};
	
	struct smDFILE_HEADER {
		char szHeader[24];
		int ObjCounter;
		int MatCounter;
		int MatFilePoint;
		int First_ObjInfoPoint;
		int TmFrameCounter;
		smFRAME_POS TmFrame[OBJ_FRAME_SEARCH_MAX];		//咯矾俺狼 橇饭烙颇老 楷搬 沥焊烙
	};
	
	struct smDFILE_OBJINFO {
		char szNodeName[32];
		int Length;
		int ObjFilePoint;
	};
	
	struct SMotionStEndInfo
	{
		DWORD	StartFrame;
		DWORD	EndFrame;
	};
	
	class smOBJ3D {
		DWORD		Head;
	public:
	
		smVERTEX	*Vertex;				// 滚咆胶
		smFACE		*Face;					// 其捞胶
		smTEXLINK	*TexLink;				//咆胶媚 谅钎 府胶飘
	
		smOBJ3D		**Physique;				// 阿 滚咆胶狼 乔瘤农 坷宏璃飘
	
		smVERTEX	ZeroVertex;				// 坷宏璃飘 吝居 滚咆胶 蔼
	
		int maxZ,minZ;
		int maxY,minY;
		int maxX,minX;
	
		int dBound;							// 官款爹 胶其绢 蔼 ^2
		int Bound;							// 官款爹 胶其绢 蔼
	
		int MaxVertex;
		int MaxFace;
	
		int nVertex;
		int nFace;
	
		int nTexLink;
	
		int ColorEffect;					// 祸惑瓤苞 荤侩 蜡公
		DWORD ClipStates;					// 努府俏 付胶农 ( 阿 努府俏喊 荤侩 蜡公 ) 
	
		POINT3D Posi;
		POINT3D CameraPosi;
		POINT3D Angle;
		int	Trig[8];
	
		// 局聪皋捞记 包访
		char	NodeName[32];		// 坷宏璃飘狼 畴靛 捞抚
		char	NodeParent[32];		// 何葛 坷宏璃飘狼 捞抚
		smOBJ3D	*pParent;			// 何葛 坷宏璃飘 器牢磐
	
		smMATRIX	Tm;				// 扁夯 TM 青纺
		smMATRIX	TmInvert;		// Tm 狼 开青纺 
		//smMATRIX	TmResult;		// 局聪皋捞记 青纺
		smFMATRIX	TmResult;		// 局聪皋捞记 青纺
		smMATRIX	TmRotate;		// 扁夯利 雀傈 青纺 
	
		smMATRIX	mWorld;			// 岿靛谅钎 函券 青纺
		smMATRIX	mLocal;			// 肺漠谅钎 函券 青纺
	
		int		lFrame;				// 弥饶 橇饭烙
	
		float	qx,qy,qz,qw;		// 雀傈 孽磐聪攫
		int		sx,sy,sz;			// 胶纳老 谅钎
		int		px,py,pz;			// 器瘤记 谅钎
	
		smTM_ROT	*TmRot;			// 橇饭烙喊 雀傈 局聪皋捞记
		smTM_POS	*TmPos;			// 橇饭烙喊 器瘤记 局聪皋捞记
		smTM_SCALE	*TmScale;		// 橇饭烙喊 胶纳老 局聪皋捞记
	
		smFMATRIX	*TmPrevRot;		// 橇饭烙喊 局聪皋捞记 青纺
	
		int TmRotCnt;
		int TmPosCnt;
		int TmScaleCnt;
	
		//TM 橇饭烙 辑摹 ( 橇饭烙捞 腹栏搁 茫扁啊 塞惦 )
		smFRAME_POS TmRotFrame[OBJ_FRAME_SEARCH_MAX];		//ROT橇饭烙 [橇饭烙 , 困摹]
		smFRAME_POS TmPosFrame[OBJ_FRAME_SEARCH_MAX];		//POS橇饭烙 [橇饭烙 , 困摹]
		smFRAME_POS TmScaleFrame[OBJ_FRAME_SEARCH_MAX];		//SCALE橇饭烙 [橇饭烙 , 困摹]
		int TmFrameCnt;									//TM橇饭烙 墨款磐 (傈眉肮荐)
	};

###读写smObj3D

	
	//颇老肺 历厘且 农扁甫 固府 掘绰促
	int smOBJ3D::GetSaveSize()
	{
		int size;
	
		size	 = sizeof( smOBJ3D );
		size	+= sizeof( smVERTEX ) * nVertex;
		size	+= sizeof( smFACE ) * nFace;
		size	+= sizeof( smTEXLINK ) * nTexLink;
	
		size	+= sizeof( smTM_ROT ) * TmRotCnt;
		size	+= sizeof( smTM_POS ) * TmPosCnt;
		size	+= sizeof( smTM_SCALE ) * TmScaleCnt;
		size	+= sizeof( smMATRIX ) * TmRotCnt;
	
		if ( Physique )
			size += 32 * nVertex;
	
		return size;
	}
	
	//颇老肺 历厘
	int smOBJ3D::SaveFile( HANDLE hFile )
	{
		DWORD	dwAcess;
		char	szBuff[64];
		int		cnt;
		int		size;
	
		Head = 0x41424344;
	
		//######################################################################################
		//累 己 磊 : 坷 康 籍
		Head |= OBJ_HEAD_TYPE_NEW_NORMAL;
		//######################################################################################
	
		size =	WriteFile( hFile , &Head	, sizeof( smOBJ3D )					, &dwAcess , NULL );
		size+=	WriteFile( hFile , Vertex	, sizeof( smVERTEX )	* nVertex	, &dwAcess , NULL );
		size+=	WriteFile( hFile , Face		, sizeof( smFACE )		* nFace		, &dwAcess , NULL );
		size+=	WriteFile( hFile , TexLink	, sizeof( smTEXLINK )	* nTexLink	, &dwAcess , NULL );
	
		size+=	WriteFile( hFile , TmRot	, sizeof( smTM_ROT )	* TmRotCnt	, &dwAcess , NULL );
		size+=	WriteFile( hFile , TmPos	, sizeof( smTM_POS )	* TmPosCnt	, &dwAcess , NULL );
		size+=	WriteFile( hFile , TmScale	, sizeof( smTM_SCALE )	* TmScaleCnt, &dwAcess , NULL );
		size+=	WriteFile( hFile , TmPrevRot, sizeof( smMATRIX )	* TmRotCnt	, &dwAcess , NULL );
	
		if ( Physique ) {
			for( cnt=0; cnt<nVertex; cnt++ ) {
				ZeroMemory( szBuff , 32 );
				lstrcpy( szBuff , Physique[cnt]->NodeName );
				size+=	WriteFile( hFile , szBuff, 32 , &dwAcess , NULL );
			}
		}
	
		return size;
	}
	//颇老肺 何磐 单捞鸥 肺靛
	int smOBJ3D::LoadFile( HANDLE hFile , smPAT3D *PatPhysique )
	{
		DWORD	dwAcess;
		char	*szBuff;
		int		cnt;
		int		len;
		smTEXLINK	*lpOldTexLink;
		int		SubTexLink;
		
		len=ReadFile( hFile , &Head			, sizeof( smOBJ3D )					, &dwAcess , NULL );
	
		lpOldTexLink = TexLink;
	
		//货肺款 皋葛府 喉钒 且寸
		Vertex	= new smVERTEX[ nVertex ];
		len+= ReadFile( hFile , Vertex		, sizeof( smVERTEX ) * nVertex		, &dwAcess , NULL );
	
		Face	= new smFACE[ nFace ];
		len+= ReadFile( hFile , Face		, sizeof( smFACE ) * nFace			, &dwAcess , NULL );
	
		TexLink = new smTEXLINK[ nTexLink ];
		len+= ReadFile( hFile , TexLink		, sizeof( smTEXLINK ) * nTexLink	, &dwAcess , NULL );
	
		TmRot		= new smTM_ROT[ TmRotCnt ];
		len+= ReadFile( hFile , TmRot		, sizeof( smTM_ROT ) * TmRotCnt		, &dwAcess , NULL );
	
		TmPos		= new smTM_POS[ TmPosCnt ];
		len+= ReadFile( hFile , TmPos		, sizeof( smTM_POS ) * TmPosCnt		, &dwAcess , NULL );
	
		TmScale		= new smTM_SCALE[ TmScaleCnt ];
		len+= ReadFile( hFile , TmScale		, sizeof( smTM_SCALE ) * TmScaleCnt	, &dwAcess , NULL );
	
		//######################################################################################
		//累 己 磊 : 坷 康 籍
		TmPrevRot	= new smFMATRIX[ TmRotCnt ];	
		len+= ReadFile( hFile , TmPrevRot	, sizeof( smFMATRIX ) * TmRotCnt		, &dwAcess , NULL );
		//######################################################################################
	
	
		//咆胶媚 谅钎啊 绢靛饭胶 器牢磐肺 登 乐栏骨肺 货肺 焊沥窃
		SubTexLink = TexLink-lpOldTexLink;
	
		for( cnt=0;cnt<nTexLink;cnt++) {
			if ( TexLink[cnt].NextTex ) {
				SubTexLink = TexLink[cnt].NextTex-lpOldTexLink;
				TexLink[cnt].NextTex = TexLink + SubTexLink;
			}
		}
	
		for( cnt=0;cnt<nFace;cnt++) {
			if ( Face[cnt].lpTexLink ) {
				SubTexLink = Face[cnt].lpTexLink-lpOldTexLink;
				Face[cnt].lpTexLink = TexLink + SubTexLink;
			}
		}
	
		if ( Physique && PatPhysique ) {
			Physique = new smOBJ3D *[ nVertex ];
			szBuff = new char[ nVertex * 32 ];
			len+= ReadFile( hFile , szBuff	, nVertex * 32		, &dwAcess , NULL );
	
			for( cnt=0; cnt<nVertex ; cnt++ ) {
				Physique[cnt] = PatPhysique->GetObjectFromName( szBuff+cnt*32 );
			}
	
			delete szBuff;
		}
	
		return len;
	}

###smType.h


	#define MAP_SIZE			256
	#define MAP_CLIP			0xFF	//(AND 0xFF)
	#define MAX_CELL			4096
	#define MAPTEXTURE_SIZE		64
	#define RAYCLIP_ANGLE		ANGLE_45+ANGLE_1
	//#define RAYCLIP_ANGLE		260
	#define RAYCLIP_ADD			5
	
	#define ANGCLIP				ANGLE_MASK
	#define CLIP_OUT			-32767
	#define SMFLAG_NONE			0xFFFFFFFF
	/*
	#define	fONE		512
	#define FLOATNS		9
	#define FLOATDS		7
	*/
	
	
	///////////////////// 绊沥 家荐痢 窜困 //////////////////
	#define	fONE		256
	#define FLOATNS		8
	#define FLOATDS		8
	
	//MATRIX侩 16厚飘 家荐痢
	#define wfONE		32768
	#define wFLOATS		15
	#define wSHIFT_FLOAT	(wFLOATS-FLOATNS)
	
	///////////////////// 甘 农扁 包访 蔼甸 ///////////////////
	#define SizeMAPCELL			64			//甘惑狼 1 CELL 角力 农扁
	#define ShiftMAPCELL_MULT	6		    //甘惑狼 1 CELL 角力 农扁 浆橇飘 蚌
	#define ShiftMAPCELL_DIV	6		    //甘惑狼 1 CELL 角力 农扁 浆橇飘 唱串
	
	#define SHIFT_MAPHEIGHT		(ShiftMAPCELL_MULT-3)
	#define AND_SizeMAPCELL		(SizeMAPCELL-1)
	#define OR_SizeMAPCELL		(0xFFFFFFFF-AND_SizeMAPCELL)
	
	//////////////////// 坊歹傅 棺 硅版 扁夯 灌扁 ////////////////////
	#define DEFAULT_CONTRAST			300
	#define DEFAULT_BRIGHT				160
	
	
	
	////////////////  smRENDER 努府俏 夸备 惑怕 蔼 ( ClipStatus ) //////////////////
	#define SMCLIP_NEARZ		0x00000001
	#define SMCLIP_FARZ			0x00000002
	#define SMCLIP_LEFT			0x00000004
	#define SMCLIP_RIGHT		0x00000008
	#define SMCLIP_TOP			0x00000010
	#define SMCLIP_BOTTOM		0x00000020
	#define SMCLIP_TEXTURE		0x00000040
	#define SMCLIP_DISPLAYOUT	0x00000080
	#define SMCLIP_DISPLAYIN	0x00000100
	
	#define SMCLIP_VIEWPORT		( SMCLIP_NEARZ | SMCLIP_FARZ | SMCLIP_LEFT | SMCLIP_RIGHT | SMCLIP_TOP | SMCLIP_BOTTOM | SMCLIP_DISPLAYOUT )
	
	////////////////// RGBA 硅凯 //////////////////////
	#define SMC_A				3
	#define SMC_R				2
	#define SMC_G				1
	#define SMC_B				0
	
	
	
	//######################################################################################
	// 累 己 磊 : 坷 康 籍
	// 角厘丛捞 函版 窍扼绊 窍继澜.
	// 咆胶媚 弥措摹
	
	#include "..\\nettype.hpp"
	#ifdef _W_SERVER
	#define MAX_TEXTURE		8000
	#else
	#define MAX_TEXTURE		5000
	#endif
	//######################################################################################
	
	
	
	
	
	//绊沥 家荐痢 青纺
	struct smMATRIX { 
	    int _11, _12, _13, _14; 
	    int _21, _22, _23, _24; 
	    int _31, _32, _33, _34; 
	    int _41, _42, _43, _44; 
	};
	
	//绊沥 家荐痢 青纺2
	struct smEMATRIX { 
	    int _11, _12, _13, _14; 
	    int _21, _22, _23, _24; 
	    int _31, _32, _33, _34; 
	    int _41, _42, _43, _44; 
	};
	
	
	//何悼 家荐 Double 屈 青纺
	struct smDMATRIX { 
	    double _11, _12, _13, _14; 
	    double _21, _22, _23, _24; 
	    double _31, _32, _33, _34; 
	    double _41, _42, _43, _44; 
	};
	
	//何悼 家荐 float 屈 青纺
	struct smFMATRIX { 
	    float _11, _12, _13, _14; 
	    float _21, _22, _23, _24; 
	    float _31, _32, _33, _34; 
	    float _41, _42, _43, _44; 
	};
	
	struct smRGB {
		DWORD r,g,b;
	};
	struct smLIGHTLEVEL {
		BYTE		cr,cg,cb;
		BYTE		sr,sg,sb;
	};
	
	struct smLIGHT3D {
		int type;
		int x,y,z;
		int Range;
		short r,g,b;
	
	};
	
	#define	smLIGHT_TYPE_NIGHT		0x00001
	#define	smLIGHT_TYPE_LENS		0x00002
	#define	smLIGHT_TYPE_PULSE2		0x00004				// 静捞绰 镑捞 绝澜. ???
	//######################################################################################
	//累 己 磊 : 坷 康 籍
	#define	SMLIGHT_TYPE_OBJ		0x00008
	//######################################################################################
	#define	smLIGHT_TYPE_DYNAMIC	0x80000
	
	
	
	struct POINT3D {
		int x,y,z;
	};
	
	struct TPOINT3D {
		int x,y,z;
		float u,v;
		float zb;
		BYTE	bCol[4];
		BYTE	bSpe[4];
	};
	
	struct smLINE3D {
		POINT3D sp;
		POINT3D ep;
	};
	
	struct smTRECT {
		float u0,v0;
		float u1,v1;
		float u2,v2;
		float u3,v3;
	};
	
	struct smTPOINT {
		int u,v;				
	};
	
	struct smFTPOINT {
		float u,v;				
	};
	
	
	
	// 3阿 弃府帮狼 咆胶媚 谅钎 客 楷搬 器牢飘
	struct smTEXLINK {
		float	u[3],v[3];	
		DWORD	*hTexture;
		smTEXLINK	*NextTex;
	};
	
	struct smVERTEX{
		int x,y,z;
		int nx,ny,nz;
	};
	
	struct smFACE{
		WORD v[4];			// a,b,c , matrial
		smFTPOINT t[3];		// 咆胶媚 器牢飘
	
		smTEXLINK	*lpTexLink;		// 咆胶媚 楷搬 谅钎 
	
	};
	
	struct smTM_ROT {
		int frame;
		float x,y,z,w;
	};
	struct smDTM_ROT {
		int frame;
		double x,y,z,w;
	};
	
	//######################################################################################
	//累 己 磊 : 坷 康 籍
	struct smTM_POS {
		int   frame;
		float x, y, z;
	};
	//######################################################################################
	
	struct smTM_SCALE {
		int frame;
		int x,y,z;
	};
	
	
	// 坊歹傅阑 困茄 滚咆胶
	struct smRENDVERTEX {
	
		// 拌魂等 滚咆胶
		int		tx,ty,tz;			// Transrate 墨皋扼 谅钎
		DWORD	ClipStatus;			// 努府俏 夸备 惑怕
	//	short	xy[2];				// 2D 函券 谅钎
		float	xy[2];				// 2D 函券 谅钎
		float	zb;					// Z Buffer 表捞
		float	rhw;				// rhw
	
		//######################################################################################
		//累 己 磊 : 坷 康 籍
		int		nx,ny,nz;
		//######################################################################################
	
		short	sLight[4];			// 蝴拌魂 滚欺 (RGBA)
	
		BYTE	bCol[4];			// 滚咆胶 祸惑 (RGBA)
		BYTE	bSpe[4];			// 滚咆胶 Specular (RGBA)
	
		void	*lpSourceVertex;	//盔夯 滚咆胶 器牢磐
	};
	
	// 坊歹傅阑 困茄 其捞胶
	struct smRENDFACE {
	
		smRENDVERTEX	*lpRendVertex[3];	// a,b,c
		DWORD			Matrial;			// 皋飘府倔
		smTEXLINK		*lpTexLink;			// 咆胶媚 楷搬 谅钎 
		DWORD			ClipStatus;			// 努府俏 夸备 惑怕
		smRENDFACE		*NexRendFace;		// 促澜 坊歹傅其捞胶 器牢磐
	
	};
	
	//坊歹傅且 蝴 备炼眉
	struct smRENDLIGHT {
		int type;
		int x,y,z;
		int rx,ry,rz;
		int Range;
		int dRange;
		int r,g,b,a;
	};
	
	
	//皋飘府倔喊 坊歹傅 其捞胶狼 楷搬眉
	struct smRENDMATRIAL {
	
		int RegistRendList;					// 坊歹傅 府胶飘俊 殿废 咯何 钎矫
	
		// 老馆屈
		DWORD		MatrialCounter;			// 坊歹傅且 其捞胶狼 荐 ( 皋飘府倔 寸 )
		smRENDFACE	*StartFace;				// 坊歹傅且 矫累 其捞胶 器牢磐
		smRENDFACE	*LastLinkFace;			// 付瘤阜栏肺 楷搬茄 其捞胶
	
		// 咆胶媚 努赋屈
		DWORD		TexClip_MatrialCounter;	// 坊歹傅且 其捞胶狼 荐 ( 皋飘府倔 寸 )
		smRENDFACE	*TexClip_StartFace;		// 坊歹傅且 矫累 其捞胶 器牢磐
		smRENDFACE	*TexClip_LastLinkFace;	// 付瘤阜栏肺 楷搬茄 其捞胶
	};
	
	//咆胶媚 康开
	struct smTEXRECT {
		float	left,	right;
		float	top,	bottom;
	};
	
	//2D 捞固瘤 其捞胶
	struct smFACE2D {
		int			x, y, z;			// 谅钎
		int			width , height;		// 捞固瘤 农扁
		smTEXRECT	TexRect;			// 咆胶媚 康开
		int			MatNum;				// 皋飘府倔 锅龋
		int			Transparency;		// 捧疙档
		int			r,g,b;
	};
	
	
	// 滚咆胶
	struct smSTAGE_VERTEX {
		DWORD			sum;				// 弥辟 楷魂 锅龋 
		smRENDVERTEX	*lpRendVertex;		// 坊歹傅阑 困茄 滚咆胶 器牢磐
	
		// 家胶 滚咆胶
		int				x,y,z;				// 岿靛 谅钎
		//BYTE			bDef_Color[4];		// 扁夯 祸惑 (RGBA)(Color )
		//BYTE			bDef_Specular[4];	// 扁夯 祸惑 (RGBA)(Specular )
	
		short			sDef_Color[4];		// 扁夯 祸惑 ( RGBA )
	
	};	// 泅犁 农扁 28 官捞飘
	
	
	// 其捞胶
	struct smSTAGE_FACE {
		DWORD	sum;				// 弥辟 楷魂 锅龋
		int		CalcSum;			// 扁鸥 楷魂 锅龋
	
		WORD	Vertex[4];			// a,b,c,Matrial
	
		smTEXLINK	*lpTexLink;		// 咆胶媚 楷搬 谅钎 
	
		short	VectNormal[4];			// Cross氦磐( Normal )  ( nx , ny , nz , [0,1,0]氦磐 Y ); 
		//int		NormalY;			// ( 0,1,0 ) 氦磐狼 DotProduct 蔼
	};
	
	
	
	
	#define		CONFIG_KEY_MONSTER_MAX		5
	
	//扁夯 汲沥 备炼眉
	struct smCONFIG {
	
		int		WinMode;					//拳搁 葛靛
		POINT	ScreenSize;					//拳搁 秦惑档
		DWORD	ScreenColorBit;				//祸惑 厚飘
		
		int		TextureQuality;				//咆胶媚 前龙
		int		BGM_Mode;					//硅版 澜厩 荤侩 
		int		NetworkQuality;				//匙飘况农 前龙
		int		WeatherSwitch;				// pluto 秦寇 厚
	
		char	szFile_BackGround[2][64];	//硅版 拳搁
		char	szFile_Menu[64];			//皋春 拳搁
		char	szFile_Player[64];			// 林牢傍
		POINT	Posi_Player;
		char	szFile_Enemy[64];			// 唱慧仇
		POINT	Posi_Enemy;
	
		char	szFile_Stage[64];				//瘤屈
		POINT	Posi_Stage;						//瘤屈 矫累 困摹
		char	szFile_StageObject[100][64];	//瘤屈俊 棵扼啊绰 坷宏璃飘
		int		StageObjCnt;					//瘤屈 坷宏璃飘 肮荐
	
		//######################################################################################
		//累 己 磊 : 坷 康 籍
		int		IsStageAniObject[100];
		//######################################################################################
	
		//脚痹 甘 阂矾 棵锭父 利侩凳
		int		MapBright;						//灌扁
		int		MapContrast;					//急疙档
		POINT3D	MapLightVector;					//氦磐
	
		char	szServerIP[32];					//辑滚林家
		DWORD	dwServerPort;					//辑滚器飘
		char	szDataServerIP[32];				//单捞鸥 辑滚林家
		DWORD	dwDataServerPort;				//单捞鸥 辑滚器飘
		char	szUserServerIP[32];				//蜡历 辑滚林家
		DWORD	dwUserServerPort;				//蜡历 辑滚器飘
		char	szExtendServerIP[32];			//犬厘 辑滚林家
		DWORD	dwExtendServerPort;				//犬厘 辑滚器飘
	
		DWORD	DebugMode;						//叼滚彪 葛靛 汲沥
	
	
		char	szCmdOpenMonster[CONFIG_KEY_MONSTER_MAX][32];		//积己矫懦 阁胶磐 捞抚
		int		CmdMonsterCount;
	};
	
	
	
	
	#define MOTION_LIST_MAX			32
	#define MOTION_INFO_MAX			512
	#define	MOTION_TOOL_MAX			52
	#define	MOTION_SKIL_MAX			8
	
	
	//######################################################################################
	//累 己 磊 : 坷 康 籍
	// 悼累阑 罚歹窍霸 厚啦俊 狼秦辑 (% 蔼俊 狼茄.)  累悼阑 且锭俊.
	// 倔奔 悼累狼 2 窜拌肺 (钎沥) (富窍扁).  <- 钎沥 葛记 钦媚辑 100%, 富窍扁 葛记 钦媚辑 100%
	// NPC  悼累篮 1 窜拌肺 累悼 钦聪促.      <- 葛电 葛记 钦媚辑 100%.
	#define NPC_MOTION_INFO_MAX		 30
	#define TALK_MOTION_INFO_MAX	 30
	
	// MotionFrame  蔼捞.
	// 角厘丛狼 单捞鸥狼 版快绰.  "MotionFrame-1" 肺 荤侩.
	// 郴啊 父电巴篮. (倔奔)  MotionFrame 蔼 弊措肺 利侩. 
	#define TALK_MOTION_FILE_MAX	 2
	#define TALK_MOTION_FILE		 0
	#define FACIAL_MOTION_FILE		 1
	//######################################################################################
	
	
	struct smMOTIONINFO {
		DWORD	State;							//惑怕 TRUE搁 蜡瓤
		
		//######################################################################################
		//累 己 磊 : 坷 康 籍
		DWORD	MotionKeyWord_1;
		DWORD	StartFrame;						//矫累 橇饭烙
		DWORD	MotionKeyWord_2;
		DWORD	EndFrame;						//辆丰 橇饭烙
		//######################################################################################
	
		DWORD	EventFrame[4];					//捞亥飘 积己 橇饭烙
	
		int		ItemCodeCount;					//秦寸 酒捞袍 府胶飘 墨款磐 ( 0 绝澜  -1 傈眉 秦寸 )
		BYTE	ItemCodeList[MOTION_TOOL_MAX];	//秦寸 酒捞袍 内靛 府胶飘 
		DWORD	dwJobCodeBit;					//秦寸 流诀喊 厚飘 付胶农
		BYTE	SkillCodeList[MOTION_SKIL_MAX];	//秦寸 胶懦 锅龋
		//BYTE	JobCodeList[MOTION_JOB_MAX];	//秦寸 胶懦 锅龋
	
		int		MapPosition;					//秦寸 甘 利侩 ( 0-包拌绝澜 付阑 1 - 鞘靛 2 )
	
		DWORD	Repeat;							//馆汗 咯何
		CHAR	KeyCode;						//悼累 矫累 虐
		int		MotionFrame;					//葛记 楷搬 颇老 锅龋
	};
	
	struct _MODELGROUP {
		int ModelNameCnt;
		char szModelName[4][16];
	};
	
	struct smMODELINFO {
	
		char	szModelFile[64];
		char	szMotionFile[64];
		char	szSubModelFile[64];
	
		_MODELGROUP	HighModel;
		_MODELGROUP	DefaultModel;
		_MODELGROUP	LowModel;
	
		smMOTIONINFO	MotionInfo[MOTION_INFO_MAX];
		DWORD	MotionCount;
	
		//######################################################################################
		//累 己 磊 : 坷 康 籍
		DWORD			FileTypeKeyWord;
		DWORD			LinkFileKeyWord;
		//######################################################################################
	
		//######################################################################################
		//累 己 磊 : 坷 康 籍
		char			szLinkFile[64];
		//######################################################################################
	
		//######################################################################################
		//累 己 磊 : 坷 康 籍
		// ******************** 林狼 ******************** 
		// 弊 颇老 2俺啊 *.ini  *.in  颇老疙捞 鞍篮 版快. 笛促 鞍篮 捞抚狼 *.inx 肺 唱坷扁东矫.
		// 颇老疙捞 崔扼具 钦聪促.
		char			szTalkLinkFile[64];	
		char			szTalkMotionFile[64];
		smMOTIONINFO	TalkMotionInfo[ TALK_MOTION_INFO_MAX ];
		DWORD			TalkMotionCount;
	
		int				NpcMotionRate[NPC_MOTION_INFO_MAX];
		int				NpcMotionRateCnt[ 100 ];
	
		int				TalkMotionRate[ TALK_MOTION_INFO_MAX ];
		int				TalkMotionRateCnt[ TALK_MOTION_FILE_MAX ][ 100 ];
		//######################################################################################	
	};
	
	
	
	
	extern BYTE VRKeyBuff[256];
	
	
	
	
	
	struct smTEXPOINT {
		float	u,v;
		DWORD	hTexture;
	};
	
	struct smFCOLOR {
		float	r,g,b;
	};
	
	//咆胶媚 甘 坊歹傅 谅钎 加己
	#define smTEXSTATE_FS_NONE		0
	#define smTEXSTATE_FS_FORMX		1
	#define smTEXSTATE_FS_FORMY		2
	#define smTEXSTATE_FS_FORMZ		3
	#define smTEXSTATE_FS_SCROLL	4
	#define smTEXSTATE_FS_REFLEX	5
	
	#define smTEXSTATE_FS_SCROLL2	6
	#define smTEXSTATE_FS_SCROLL3	7
	#define smTEXSTATE_FS_SCROLL4	8
	#define smTEXSTATE_FS_SCROLL5	9
	#define smTEXSTATE_FS_SCROLL6	10
	#define smTEXSTATE_FS_SCROLL7	11
	#define smTEXSTATE_FS_SCROLL8	12
	#define smTEXSTATE_FS_SCROLL9	13
	#define smTEXSTATE_FS_SCROLL10	14
	
	#define smTEXSTATE_FS_SCROLLSLOW1	15
	#define smTEXSTATE_FS_SCROLLSLOW2	16
	#define smTEXSTATE_FS_SCROLLSLOW3	17
	#define smTEXSTATE_FS_SCROLLSLOW4	18
	
	
	/////////////////////////// Texture Map 勤甸 备炼 //////////////////////////
	struct smTEXTUREHANDLE {
		char Name[64];
		char NameA[64];
	    LPDIRECT3DTEXTURE2 lpD3DTexture;
		LPDIRECTDRAWSURFACE4 lpDDSurface;
	
	    LPDIRECT3DTEXTURE2 lpD3DTextureLarge;		//奴荤捞令狼 咆胶媚 ( 矫胶袍 皋葛府客狼 胶恳栏肺 掘绢咙 )
		LPDIRECTDRAWSURFACE4 lpDDSSysMemory;		//矫胶袍 皋葛府 咆胶媚
	
		int Width , Height;
		int UsedTime;
	
		int UseCounter;
	
		int	MapOpacity;								//甘 坷乔矫萍 咯何 ( TRUE , FALSE )
	
		DWORD	TexSwapMode;						//胶恳 咆胶媚甫 荤侩咯何 ( TRUE / FALSE )
	
		smTEXTUREHANDLE *TexChild;
	};
	
	
	/////////////////////////// ASE 俊辑 佬绢 甸牢 皋飘府倔 //////////////////////////
	struct ASE_MATERIAL {
		int			Regist;						//smMATERIAL 俊 殿废 咯何
		int			RegistNum;					//smMATERIAL 俊 殿废等 锅龋
	
		int			TextureCounter;				//厚飘甘 荤侩 肮荐
		DWORD		UseCounter;					//荤侩 墨款磐 ( 荤侩荐啊 0 捞搁 力芭 格利 )
	
		smFCOLOR	Diffuse;					// Diffuse 祸惑
		float		Transparency;				// 捧疙档
		float		SelfIllum;					// 磊眉 惯堡
		DWORD		TwoSide;					// 剧搁 钎矫 葛靛 ( 第笼腮搁 钎矫 咯何 )
		DWORD		ScriptState;				// 胶农赋飘肺 罐篮 加己
	
		char		BITMAP[8][64];				// Bitmap 捞抚
	
		DWORD		BitmapStateState[8];		// 厚飘甘 胶抛捞瘤 加己
		DWORD		BitmapFormState[8];			// 厚飘甘 汽 加己
	
		char		MAP_OPACITY[64];			// 馆捧疙 甘 捞抚
	
		float		UVW_U_OFFSET[8];			// 甘 OFFSET
		float		UVW_V_OFFSET[8];			//
		float		UVW_U_TILING[8];			// 甘 TILING
		float		UVW_V_TILING[8];
		float		UVW_ANGLE[8];				// 甘 ANGLE
	
		int			SubPoint;					// 辑宏 皋飘府倔 锅龋
		int			BlendType;					//喉坊靛 鸥涝
	};
	
	
	
	//////////////////////////////// 皋飘府倔  //////////////////////////////
	struct smMATERIAL {
		DWORD			InUse;						//皋飘府倔 荤侩 咯何
		DWORD			TextureCounter;				//咆胶媚 墨款磐
		smTEXTUREHANDLE	*smTexture[8];				//咆胶媚 勤甸 府胶飘
		//加己
		DWORD			TextureStageState[8];		//弊府扁 加己 
		DWORD			TextureFormState[8];		//汽 加己  
		int				ReformTexture;				//咆胶媚 府汽加己 犬牢
	
		int				MapOpacity;					//甘 坷乔矫萍 咯何 ( TRUE , FALSE )
	
		//老馆利牢 加己
		DWORD			TextureType;				//咆胶媚 鸥涝 ( 钢萍雇胶 / 局聪皋捞记 )
		DWORD			BlendType;					//宏罚靛 屈侥 ( SMMAT_BLEND_XXXX )
	
		DWORD			Shade;						//溅捞爹 规侥 ( 畴溅捞靛 / 弊肺溅捞靛 )
		DWORD			TwoSide;					//剧搁 荤侩 咯何
		DWORD			SerialNum;					//皋飘府倔俊 措茄 绊蜡 锅龋
	
		smFCOLOR		Diffuse;					// Diffuse 祸惑
		float			Transparency;				// 捧疙档
		float			SelfIllum;					// 磊眉 惯堡
	
		int				TextureSwap;				// 咆胶媚 胶客俏
		int				MatFrame;					// 荤侩橇饭烙 ( 荤侩矫 悼扁甫 嘎眠扁 困窃 )
		int				TextureClip;				// 胶客俏侩 咆胶媚 努赋蜡公 ( TRUE 搁 咆胶媚 努府俏 倾啊 )
	
		//皋浆 包访 加己
		int				UseState;					// 侩档 ( 荤侩 加己 )
		int				MeshState;					// MESH狼 龙皑 加己蔼 
	
		//Mesh 函屈 包访 汲沥
		int				WindMeshBottom;				// 官恩阂扁 皋浆 函屈 矫累 蔼
	
		//俊聪皋捞记 咆胶媚 加己
		smTEXTUREHANDLE	*smAnimTexture[32];			//局聪皋捞记 咆胶媚 勤甸 府胶飘
		DWORD			AnimTexCounter;				//局固匙捞记 咆胶媚 墨款磐
		DWORD			FrameMask;					//局聪皋捞记侩 橇饭烙 付胶农
		DWORD			Shift_FrameSpeed;			//橇饭烙 刘啊 加档 ( 鸥捞赣甫 Shift 窍咯 拌魂 )
		DWORD			AnimationFrame;				//橇饭烙 锅龋 ( 绊沥老 版快 橇饭烙蔼 / SMTEX_AUTOANIMATION 篮 磊悼 )
	
	
	};
	
	//////////////// 皋飘府倔 加己 汲沥 蔼甸 ////////////////////
	
	//TextureType 利侩
	#define SMTEX_TYPE_MULTIMIX		0x0000
	#define SMTEX_TYPE_ANIMATION	0x0001
	
	
	//Texture 俊聪皋捞记 橇饭烙吝 磊悼老 版快
	#define SMTEX_AUTOANIMATION		0x100
	
	
	//MeshState 利侩
	//官蹿 眉农 咯何
	#define SMMAT_STAT_CHECK_FACE	0x00000001
	
	//
	#define SMMAT_BLEND_NONE		0x00
	#define SMMAT_BLEND_ALPHA		0x01
	#define SMMAT_BLEND_COLOR		0x02
	#define SMMAT_BLEND_SHADOW		0x03
	#define SMMAT_BLEND_LAMP		0x04
	#define SMMAT_BLEND_ADDCOLOR	0x05
	#define SMMAT_BLEND_INVSHADOW	0x06
	
	
	
	////////////////////////////////////////////////////////////////
	
	
	
	//////////////// ASE 扼捞飘 坷宏璃飘 ////////////////////
	struct smASE_LIGHT {
	
		int	x,y,z;
		int r,g,b;
	
		int Size;
		int Range;
		int	Dist;
		int Type;
	};
	
	
	//墨皋扼 眠利 谅钎
	struct eCAMERA_TRACE {
		int x,y,z;					//角力 墨皋扼 谅钎
		int tx,ty,tz;				//格钎痢 谅钎
	
		smEMATRIX	eRotMatrix;		//雀傈 拌魂等 青纺
		int AngX,AngY;				//螟沥等 阿档 ( 坷瞒 裹困怒 )
	};

###材质


	class smMATERIAL_GROUP {
		DWORD	Head;
	public:
		smMATERIAL *smMaterial;
		DWORD MaterialCount;
	
		int ReformTexture;		//府汽且 咆胶媚 蜡公
	
		int MaxMaterial;
	
		int LastSearchMaterial;
		char szLastSearchName[64];
	};

	#define TEXFILENAME_SIZE	64
	
	//历厘且 单捞鸥狼 农扁甫 固府 舅妨淋
	int smMATERIAL_GROUP::GetSaveSize()
	{
		int size;
		DWORD cnt ,tcnt;
		int	len,alen;
	
		size	 = sizeof( smMATERIAL_GROUP );
	
		for(cnt=0;cnt<MaterialCount;cnt++) {
			size+= sizeof( smMATERIAL );
			if ( smMaterial[cnt].InUse ) {
				size += sizeof(int);					//咆胶媚 捞抚 辨捞啊 甸绢哎 int屈
				for( tcnt=0; tcnt<smMaterial[cnt].TextureCounter ; tcnt++) {
					len = lstrlen( smMaterial[cnt].smTexture[tcnt]->Name )+1;
					alen = lstrlen( smMaterial[cnt].smTexture[tcnt]->NameA )+1;
					size += len;
					size += alen;
				}
	
				for( tcnt=0; tcnt<smMaterial[cnt].AnimTexCounter ; tcnt++) {
					len = lstrlen( smMaterial[cnt].smAnimTexture[tcnt]->Name )+1;
					alen = lstrlen( smMaterial[cnt].smAnimTexture[tcnt]->NameA )+1;
					size += len;
					size += alen;
				}
	
			}
		}
		return size;
	}
	
	//单捞鸥甫 颇老肺 历厘 
	int smMATERIAL_GROUP::SaveFile( HANDLE hFile )
	{
		DWORD	dwAcess;
		DWORD cnt ,tcnt;
		int	len;
		int size;
	
		size =	WriteFile( hFile , &Head	, sizeof( smMATERIAL_GROUP )	, &dwAcess , NULL );
	
		for(cnt=0;cnt<MaterialCount;cnt++) {
			//皋飘府倔 历厘
			size+= WriteFile( hFile , &smMaterial[cnt] , sizeof( smMATERIAL )	, &dwAcess , NULL );
	
			if ( smMaterial[cnt].InUse ) {
	
				//咆胶媚 颇老 格废俊 措茄 捞抚 辨捞 拌魂
				len = 0;
				for( tcnt=0; tcnt<smMaterial[cnt].TextureCounter ; tcnt++) {
					len += lstrlen( smMaterial[cnt].smTexture[tcnt]->Name )+1;
					len += lstrlen( smMaterial[cnt].smTexture[tcnt]->NameA )+1;
				}
				for( tcnt=0; tcnt<smMaterial[cnt].AnimTexCounter ; tcnt++) {
					len += lstrlen( smMaterial[cnt].smAnimTexture[tcnt]->Name )+1;
					len += lstrlen( smMaterial[cnt].smAnimTexture[tcnt]->NameA )+1;
				}
				//巩磊凯 辨捞 固府 拌魂
				size+= WriteFile( hFile , &len , sizeof(int) , &dwAcess , NULL );
	
				//咆胶媚 颇老 捞抚阑 楷加利栏肺 历厘
				for( tcnt=0; tcnt<smMaterial[cnt].TextureCounter ; tcnt++) {
					len = lstrlen( smMaterial[cnt].smTexture[tcnt]->Name )+1;
					size+= WriteFile( hFile , smMaterial[cnt].smTexture[tcnt]->Name , len , &dwAcess , NULL );
					len = lstrlen( smMaterial[cnt].smTexture[tcnt]->NameA )+1;
					size+= WriteFile( hFile , smMaterial[cnt].smTexture[tcnt]->NameA , len , &dwAcess , NULL );
				}
	
				for( tcnt=0; tcnt<smMaterial[cnt].AnimTexCounter ; tcnt++) {
					len = lstrlen( smMaterial[cnt].smAnimTexture[tcnt]->Name )+1;
					size+= WriteFile( hFile , smMaterial[cnt].smAnimTexture[tcnt]->Name , len , &dwAcess , NULL );
					len = lstrlen( smMaterial[cnt].smAnimTexture[tcnt]->NameA )+1;
					size+= WriteFile( hFile , smMaterial[cnt].smAnimTexture[tcnt]->NameA , len , &dwAcess , NULL );
				}
	
			}
		}
	
		return size;
	}
	
	//颇老俊辑 单捞鸥甫 肺靛 
	int smMATERIAL_GROUP::LoadFile( HANDLE hFile )
	{
		DWORD	dwAcess;
		DWORD	cnt ,tcnt;
		int		StrLen;
		int		size;
		char	szNameBuff[4096];
		char	*lpNameBuff;
		char	*szName , *szNameA;
	
		//弊缝狼 郴侩阑 佬绢咳
		size=ReadFile( hFile , &Head , sizeof( smMATERIAL_GROUP ) , &dwAcess , NULL );
	
		//皋飘府倔 皋葛府 棱澜
		smMaterial = new smMATERIAL[ MaterialCount ];
	
		for(cnt=0;cnt<MaterialCount;cnt++) {
			//皋飘府倔 单捞鸥 佬绢咳
			size+=	ReadFile( hFile , &smMaterial[cnt] , sizeof( smMATERIAL ) , &dwAcess , NULL );
	
			if ( smMaterial[cnt].InUse ) {
				//咆胶媚 匙烙 滚欺 农扁 佬澜
				size+=	ReadFile( hFile , &StrLen , sizeof( int ) , &dwAcess , NULL );
				//咆胶媚 匙烙 滚欺 农扁父怒 单捞鸥 佬澜 
				size+=	ReadFile( hFile , szNameBuff , StrLen, &dwAcess , NULL );
	
				lpNameBuff = szNameBuff;
	
				//咆胶媚 汲沥
				for( tcnt=0; tcnt<smMaterial[cnt].TextureCounter ; tcnt++) {
					szName = lpNameBuff;
					lpNameBuff += lstrlen( szName )+1;
					szNameA = lpNameBuff;
					lpNameBuff += lstrlen( szNameA )+1;
	
					if ( szNameA[0] )
						smMaterial[cnt].smTexture[tcnt] = smTexture.Add( szName , szNameA );
					else
						smMaterial[cnt].smTexture[tcnt] = smTexture.Add( szName );
				}
	
				//俊聪皋捞记 咆胶媚 汲沥
				for( tcnt=0; tcnt<smMaterial[cnt].AnimTexCounter ; tcnt++) {
					szName = lpNameBuff;
					lpNameBuff += lstrlen( szName )+1;
					szNameA = lpNameBuff;
					lpNameBuff += lstrlen( szNameA )+1;
	
					if ( szNameA[0] )
						smMaterial[cnt].smAnimTexture[tcnt] = smTexture.Add( szName , szNameA );
					else
						smMaterial[cnt].smAnimTexture[tcnt] = smTexture.Add( szName );
				}
			}
	
		}
	
		return size;
	}
