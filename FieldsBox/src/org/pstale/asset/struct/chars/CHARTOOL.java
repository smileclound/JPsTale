package org.pstale.asset.struct.chars;

import org.pstale.asset.struct.OBJ3D;
import org.pstale.asset.struct.PAT3D;

public class CHARTOOL {
	OBJ3D ObjBip;
	PAT3D PatTool;
	int dwItemCode;

	int SizeMax, SizeMin;

	int ColorBlink;
	short[] sColors = new short[4];
	int DispEffect;
	int BlinkScale;
	int EffectKind;

	int TexMixCode, TexScroll;
}
