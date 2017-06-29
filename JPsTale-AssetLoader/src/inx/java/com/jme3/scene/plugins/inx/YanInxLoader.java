package com.jme3.scene.plugins.inx;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.jme3.scene.Node;
import com.jme3.scene.plugins.ase.AseKey;
import com.jme3.scene.plugins.inx.anim.DrzAnimation;
import com.jme3.scene.plugins.inx.anim.DrzAnimationSet;
import com.jme3.scene.plugins.inx.anim.DrzInxMeshInfo;
import com.jme3.scene.plugins.inx.base.AbstractLoader;
import com.jme3.scene.plugins.inx.mesh.DrzMesh;

/**
 * 精灵的inx索引文件导入插件
 * @author yanmaoyuan
 *
 */
public class YanInxLoader extends AbstractLoader {
	String smdFile;
	String smbFile;
	String inxFile;
	String chainInxFile;
	String sharedInxFile;
	
	protected Node rootNode = new Node();
	
	public HashMap<Integer, DrzAnimationSet> mAnimationSetMap = new HashMap<Integer, DrzAnimationSet>();

	public Object parse(InputStream inputStream) throws IOException {
		
		int length = inputStream.available();

		if (length <= 67083) {
			System.out.println("Error: can't read inx-file (invalid file content)\n");
			return null;
		}

		getByteBuffer(inputStream);
		
		inxFile = key.getName();
		
		smdFile = getString();
		buffer.position(64);
		smbFile = getString();
		
		if (smdFile.length() > 0) {
			smdFile = changeName(smdFile);
		}

		if (smbFile.length() > 0) {
			smbFile = changeName(smbFile);
		}

		// 解析inx文件
		if (buffer.limit() <= 67084) { // old inx file
			buffer.position(61848);
			sharedInxFile = getString();
			handleShared();
			readAnimFromOld();
		} else { // new inx file (KPT)
			buffer.position(88472);
			sharedInxFile = getString();
			handleShared();
			readAnimFromNew();
		}
		
		// Read Animation from smb
		if (smbFile.length() > 0) {
			// 后缀名改为smb
			int n = smbFile.lastIndexOf(".");
			String str = smbFile.substring(0, n);
			smbFile = str + ".smb";
			
			try {
				manager.loadAsset(new AseKey(smbFile));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Read Mesh from smd
		if (smdFile.length() > 0) {
			// 后缀名改为smd
			int n = smdFile.lastIndexOf(".");
			String str = smdFile.substring(0, n);
			smdFile = str + ".smd";
			
			try {
				return manager.loadAsset(new AseKey(smdFile));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		// mesh.CreateMesh();
		
		return rootNode;
	}
	
	
	/**
	 * 共享动画数据
	 */
	private void handleShared() {
		if (sharedInxFile.length() <= 0)
			return;
		// 后缀名改为inx
		int n = sharedInxFile.lastIndexOf(".");
		String str = sharedInxFile.substring(0, n);
		sharedInxFile = str + ".inx";

		if (sharedInxFile.length() > 0) {
			sharedInxFile = changeName(sharedInxFile);

			// 读取共享的动画
			File file = new File(sharedInxFile);
			if (file.exists()) {
				try {
					InputStream inputStream = new FileInputStream(file);
					int length = inputStream.available();

					if (length <= 67083) {
						System.err.println("Error: can't read inx-file (invalid file content):" + length);
					} else {
						getByteBuffer(inputStream);

						buffer.position(64);
						smbFile = getString();
						if (smbFile.length() > 0) {
							smbFile = changeName(smbFile);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.err.println("Error: " + sharedInxFile + " not exists.");
			}
		}
	}

	private void readAnimFromOld() {
		// Read The Mesh Def
		readMeshDef();

		buffer.position(61836);
		int AnimationCount = getShort() - 10;

		int AnimationOffset = 1596;

		int SubAnimationNum = 0;
		for (int i = 0; i < AnimationCount; i++) {
			buffer.position(AnimationOffset + (i * 120) + 116);
			int temp_max_sub_ani = getUnsignedInt();
			if (temp_max_sub_ani > SubAnimationNum) {
				SubAnimationNum = temp_max_sub_ani;
			}
		}

		// 解析动画索引
		if (SubAnimationNum > 0) {

			for (int i = 0; i < AnimationCount; i++) {
				buffer.position(AnimationOffset + (i * 120));
				int AnimationId = getInt();

				if (AnimationId < 1) // no more Animations
					break;

				DrzAnimationSet CurrentAnimationSet = new DrzAnimationSet();

				// Set AnimationSetID
				CurrentAnimationSet.AnimationTypeId = AnimationId;

				int[] val = new int[2];
				
				buffer.position(AnimationOffset + (i * 120) + 4);// current animation starts at this frame
				val[0] = buffer.get()&0xFF;
				buffer.position(AnimationOffset + (i * 120) + 6);
				val[1] = buffer.get()&0xFF;
				
				CurrentAnimationSet.AnimationStartKey = (val[1] << 8) + val[0];

				buffer.position(AnimationOffset + (i * 120) + 16);// current animation end at this frame
				val[0] = buffer.get()&0xFF;
				buffer.position(AnimationOffset + (i * 120) + 18);
				val[1] = buffer.get()&0xFF;
				CurrentAnimationSet.AnimationEndKey1 = (val[1] << 8) + val[0];

				CurrentAnimationSet.Repeat = false;
				buffer.position(AnimationOffset + (i * 120) + 108);
				int iRepeat = getInt();
				if (iRepeat == 1) {
					CurrentAnimationSet.Repeat = true;
				}

				buffer.position(AnimationOffset + (i * 120) + 112);
				CurrentAnimationSet.UnkChar = buffer.getChar();

				buffer.position(AnimationOffset + (i * 120) + 116);
				CurrentAnimationSet.SubAnimationIndex = getInt();
				if (CurrentAnimationSet.SubAnimationIndex > 0) {
					CurrentAnimationSet.SubAnimationIndex--;
				}

				// Add AnimationSet
				CurrentAnimationSet.AnimationIndex = i;
				mAnimationSetMap.put(i, CurrentAnimationSet);
			}
		}
	}

	private void readAnimFromNew() {
		// Read The Mesh Def
		readMeshDef();

		buffer.position(88460);
		int AnimationCount = getShort() - 10;

		int AnimationOffset = 2116;

		int SubAnimationNum = 0;
		for (int i = 0; i < AnimationCount; i++) {
			buffer.position(AnimationOffset + (i * 172) + 168);
			int temp_max_sub_ani = getUnsignedInt();
			if (temp_max_sub_ani > SubAnimationNum) {
				SubAnimationNum = temp_max_sub_ani;
			}
		}

		// 解析动画索引
		if (SubAnimationNum > 0) {
			for (int i = 0; i < AnimationCount; i++) {
				buffer.position(AnimationOffset + (i * 172));
				int AnimationId = getInt();

				if (AnimationId < 1) // no more Animations
					break;

				DrzAnimationSet CurrentAnimationSet = new DrzAnimationSet();

				// Set AnimationSetID
				CurrentAnimationSet.AnimationTypeId = AnimationId;

				int[] val = new int[2];
				
				buffer.position(AnimationOffset + (i * 172) + 4);// current animation starts at this frame
				val[0] = buffer.get()&0xFF;
				buffer.position(AnimationOffset + (i * 172) + 6);
				val[1] = buffer.get()&0xFF;
				
				CurrentAnimationSet.AnimationStartKey = 160 * ((val[1] << 8) + val[0]);

				buffer.position(AnimationOffset + (i * 172) + 16);// current animation end at this frame
				val[0] = buffer.get()&0xFF;
				buffer.position(AnimationOffset + (i * 172) + 18);
				val[1] = buffer.get()&0xFF;
				CurrentAnimationSet.AnimationEndKey1 = 160 * ((val[1] << 8) + val[0]);
				
				buffer.position(AnimationOffset + (i * 172) + 24);// secound end key, downt know why
				val[0] = buffer.get()&0xFF;
				buffer.position(AnimationOffset + (i * 172) + 26);
				val[1] = buffer.get()&0xFF;
				CurrentAnimationSet.AnimationEndKey2 = 160 * ((val[1] << 8) + val[0]);

				CurrentAnimationSet.Repeat = false;
				buffer.position(AnimationOffset + (i * 172) + 160);
				int iRepeat = getInt();
				if (iRepeat == 1) {
					CurrentAnimationSet.Repeat = true;
				}

				buffer.position(AnimationOffset + (i * 172) + 164);
				CurrentAnimationSet.UnkChar = buffer.getChar();

				buffer.position(AnimationOffset + (i * 172) + 168);
				CurrentAnimationSet.SubAnimationIndex = getInt();
				if (CurrentAnimationSet.SubAnimationIndex > 0) {
					CurrentAnimationSet.SubAnimationIndex--;
				}

				// Add AnimationSet
				CurrentAnimationSet.AnimationIndex = i;
				mAnimationSetMap.put(i, CurrentAnimationSet);
			}
		}
	}

	/**
	 * 读取网格定义
	 */
	private void readMeshDef() {
		int MeshDefOffset = 192;

		for (int i = 0; i < 28; i++) {
			buffer.position(MeshDefOffset + i * 68);
			int MeshDefNum = getInt();

			if (MeshDefNum > 0) {
				DrzInxMeshInfo subMesh = new DrzInxMeshInfo();
				subMesh.type = 1;
				buffer.position(MeshDefOffset + i * 68 + 4);
				subMesh.meshName1 = getString();
				buffer.position(MeshDefOffset + i * 68 + 20);
				subMesh.meshName2 = getString();
				buffer.position(MeshDefOffset + i * 68 + 36);
				subMesh.meshName3 = getString();
				buffer.position(MeshDefOffset + i * 68 + 52);
				subMesh.meshName4 = getString();
				
			}
		}
	}
}
