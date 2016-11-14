package org.pstale.asset.struct.chars;

import java.io.IOException;

import org.pstale.asset.struct.Flyweight;
import org.pstale.asset.struct.PAT3D;

import com.jme3.util.LittleEndien;

public class DPAT extends Flyweight {

	DPAT lpTalkLink;

	DPAT smDinaLink;
	PAT3D Pat;

	String patName;// [64]
	int UseCount;
	int dwSpeedFindSum;

	MODELINFO lpModelInfo;

	int LastUsedTime;

	@Override
	public void loadData(LittleEndien in) throws IOException {
		in.readInt();// lpTalkLink
		in.readInt();// smDinaLink
		in.readInt();// Pat
		
		patName = getString(in, 64);
		UseCount = in.readInt();
		dwSpeedFindSum = in.readInt();

		in.readInt();// lpModelInfo
		
		LastUsedTime = in.readInt();
	}
}
