package com.jme3.asset.maxase;

import com.jme3.asset.maxase.material.MatsScriptCode;

/**
 * under developing. Most of them are useless for now.
 * @author yanmaoyuan
 *
 */
public interface CONSTANT {
	/*D3D BlendType*/
	int BS_SCRIPT_MAX = 7;
	
	// Modulate
	int D3DTOP_MODULATE = 4; // multiply args together
	int D3DTOP_MODULATE2X = 5; // multiply and 1 bit
	int D3DTOP_MODULATE4X = 6; // multiply and 2 bits
	// Add
	int D3DTOP_ADD = 7; // add arguments together
	int D3DTOP_ADDSIGNED = 8; // add with -0.5 bias
	int D3DTOP_ADDSIGNED2X = 9; // as above but left 1 bit
	int D3DTOP_SUBTRACT = 10; // Arg1 - Arg2, with no saturation

	String[] szMapStageScript = {// 7
			"BS_MODULATE:", 
			"BS_MODULATE2X:", 
			"BS_MODULATE4X:", 
			"BS_ADD:",
			"BS_ADDSIGNED:", 
			"BS_ADDSIGNED2X:", 
			"BS_SUBTRACT:" };
	int[] BsStageScript = {// 7
			D3DTOP_MODULATE, 
			D3DTOP_MODULATE2X, 
			D3DTOP_MODULATE4X, 
			D3DTOP_ADD,
			D3DTOP_ADDSIGNED, 
			D3DTOP_ADDSIGNED2X, 
			D3DTOP_SUBTRACT };
	
	int FS_SCRIPT_MAX = 19;
	String[] szMapFormScript = {// 19
			"FS_NONE:",
			"FS_FORMX:",
			"FS_FORMY:",
			"FS_FORMZ:",
			"FS_SCROLL:",
			"FS_REFLEX:",
	
			"FS_SCROLL2:",
			"FS_SCROLL3:",
			"FS_SCROLL4:",
			"FS_SCROLL5:",
			"FS_SCROLL6:",
			"FS_SCROLL7:",
			"FS_SCROLL8:",
			"FS_SCROLL9:",
			"FS_SCROLL10:",
	
			"FS_SCROLLSLOW1:",
			"FS_SCROLLSLOW2:",
			"FS_SCROLLSLOW3:",
			"FS_SCROLLSLOW4:"
		};
	
	/* SCRIPT */
	int MATS_SCRIPT_MAX = 17;
	
	int sMATS_SCRIPT_WIND = 1;
	int sMATS_SCRIPT_ANIM2 = 2;
	int sMATS_SCRIPT_ANIM4 = 4;
	int sMATS_SCRIPT_ANIM8 = 8;
	int sMATS_SCRIPT_ANIM16 = 16;
	int sMATS_SCRIPT_WINDZ1 = 0x0020;
	int sMATS_SCRIPT_WINDZ2 = 0x0040;
	int sMATS_SCRIPT_WINDX1 = 0x0080;
	int sMATS_SCRIPT_WINDX2 = 0x0100;
	int sMATS_SCRIPT_WATER = 0x0200;
	int sMATS_SCRIPT_NOTVIEW = 0x0400;
	int sMATS_SCRIPT_PASS = 0x0800;
	int sMATS_SCRIPT_NOTPASS = 0x1000;
	int sMATS_SCRIPT_RENDLATTER = 0x2000;
	int sMATS_SCRIPT_BLINK_COLOR = 0x4000;
	int sMATS_SCRIPT_CHECK_ICE = 0x8000;
	int sMATS_SCRIPT_ORG_WATER = 0x10000;
	
	MatsScriptCode[] MaterialFormScript = {// 17
			new MatsScriptCode("wind:", sMATS_SCRIPT_WIND),
			new MatsScriptCode("anim2:", sMATS_SCRIPT_ANIM2),
			new MatsScriptCode("anim4:", sMATS_SCRIPT_ANIM4),
			new MatsScriptCode("anim8:", sMATS_SCRIPT_ANIM8),
			new MatsScriptCode("anim16:", sMATS_SCRIPT_ANIM16),
			new MatsScriptCode("wind_z1:", sMATS_SCRIPT_WINDZ1),
			new MatsScriptCode("wind_z2:", sMATS_SCRIPT_WINDZ2),
			new MatsScriptCode("wind_x1:", sMATS_SCRIPT_WINDX1),
			new MatsScriptCode("wind_x2:", sMATS_SCRIPT_WINDX2),
			new MatsScriptCode("water:", sMATS_SCRIPT_WATER),
			new MatsScriptCode("wall:", sMATS_SCRIPT_NOTVIEW),
			new MatsScriptCode("pass:", sMATS_SCRIPT_PASS),
			new MatsScriptCode("notpass:", sMATS_SCRIPT_NOTPASS),
			new MatsScriptCode("render_latter:", sMATS_SCRIPT_RENDLATTER),
			new MatsScriptCode("BLINK_COLOR:", sMATS_SCRIPT_BLINK_COLOR),
			new MatsScriptCode("ice:", sMATS_SCRIPT_CHECK_ICE),
			new MatsScriptCode("orgwater:", sMATS_SCRIPT_ORG_WATER) };
	
	int SMMAT_STAT_CHECK_FACE = 0x00000001;
	/*BLEND MODE*/
	int MATS_BLEND_MAX = 5;
	int SMMAT_BLEND_NONE = 0x00,
		SMMAT_BLEND_ALPHA = 0x01,
		SMMAT_BLEND_COLOR = 0x02,
		SMMAT_BLEND_SHADOW = 0x03,
		SMMAT_BLEND_LAMP = 0x04,
		SMMAT_BLEND_ADDCOLOR = 0x05,
		SMMAT_BLEND_INVSHADOW = 0x06;
	
	MatsScriptCode[] MaterialFormBlend = {// 5
			new MatsScriptCode("BLEND_ALPHA:", SMMAT_BLEND_ALPHA),
			new MatsScriptCode("BLEND_COLOR:", SMMAT_BLEND_COLOR),
			new MatsScriptCode("BLEND_ADDCOLOR:", SMMAT_BLEND_ADDCOLOR),
			new MatsScriptCode("BLEND_SHADOW:", SMMAT_BLEND_SHADOW),
			new MatsScriptCode("BLEND_LAMP:", SMMAT_BLEND_LAMP) };
	
	int	MAX_MAP_BLINK_COLOR_TIME = 8;

	String[] szBlinkTimeScript =
	{
		"BLINK_TIME1:",
		"BLINK_TIME2:",
		"BLINK_TIME3:",
		"BLINK_TIME4:",
		"BLINK_TIME5:",
		"BLINK_TIME6:",
		"BLINK_TIME7:",
		"BLINK_TIME8:",
	};

	int[] dwBlinkTimeCode =
	{
	    9, 10, 11, 12, 13, 14, 15, 16,
	};
}
