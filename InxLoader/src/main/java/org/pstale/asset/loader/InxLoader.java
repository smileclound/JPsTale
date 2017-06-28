package org.pstale.asset.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import com.jme3.animation.Animation;
import com.jme3.asset.maxase.AseKey;

import org.pstale.asset.anim.DrzAnimation;
import org.pstale.asset.anim.DrzAnimationSet;
import org.pstale.asset.anim.DrzInxMeshInfo;
import org.pstale.asset.anim.MotionControl;
import org.pstale.asset.anim.SubAnimation;
import org.pstale.asset.base.AbstractLoader;
import org.pstale.asset.mesh.DrzMesh;

import com.jme3.scene.Node;

/**
 * �����inx�����ļ�������
 * @author yanmaoyuan
 *
 */
public class InxLoader extends AbstractLoader {
	
	String smdFile;
	String smbFile;
	String inxFile;
	String chainInxFile;
	String sharedInxFile;
	
	protected Node rootNode = null;

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

		// ����inx�ļ�
		DrzAnimation anim = null;
		if (buffer.limit() <= 67084) { // old inx file
			buffer.position(61848);
			sharedInxFile = getString();
			handleShared();
			anim = readAnimFromOld();
		} else { // new inx file (KPT)
			buffer.position(88472);
			sharedInxFile = getString();
			handleShared();
			anim = readAnimFromNew();
		}
		
		// Read Animation from smb
		if (smbFile.length() > 0) {
			// ��׺����Ϊsmb
			int n = smbFile.lastIndexOf(".");
			String str = smbFile.substring(0, n);
			smbFile = str + ".smb";
			
			try {
				rootNode = (Node)manager.loadAsset(new SmbKey(smbFile, anim));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Read Mesh from smd
		if (smdFile.length() > 0) {
			// ��׺����Ϊsmd
			int n = smdFile.lastIndexOf(".");
			String str = smdFile.substring(0, n);
			smdFile = str + ".smd";
			
			try {
				if (anim == null) {
					return manager.loadAsset(new AseKey(smdFile));
				} else {
					return manager.loadAsset(new SmdKey(smdFile, anim, rootNode));
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		// mesh.CreateMesh();
		
		return rootNode;
	}
	
	
	/**
	 * ������������
	 */
	private void handleShared() {
		if (sharedInxFile.length() <= 0)
			return;
		// ��׺����Ϊinx
		int n = sharedInxFile.lastIndexOf(".");
		String str = sharedInxFile.substring(0, n);
		sharedInxFile = str + ".inx";

		if (sharedInxFile.length() > 0) {
			sharedInxFile = changeName(sharedInxFile);

			// ��ȡ�����Ķ���
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

	public String getAnimationSetNameById(int id) {
		String ret = "unknown";

		switch (id) {
		case 64:
			ret = "Idle";
			break;
		case 80:
			ret = "Walk";
			break;
		case 96:
			ret = "Run";
			break;
		case 128:
			ret = "Fall";
			break;
		case 256:
			ret = "Attack";
			break;
		case 272:
			ret = "Damage";
			break;
		case 288:
			ret = "Die";
			break;
		case 304:
			ret = "Sometimes";
			break;
		case 320:
			ret = "Potion";
			break;
		case 336:
			ret = "Technique";
			break;
		case 368:
			ret = "Landing (small)";
			break;
		case 384:
			ret = "Landing (large)";
			break;
		case 512:
			ret = "Standup";
			break;
		case 528:
			ret = "Cry";
			break;
		case 544:
			ret = "Hurray";
			break;
		case 576:
			ret = "Jump";
			break;
		}

		return ret;
	}
	
	/**
	 * ������������
	 * @return
	 */
	private DrzAnimation readAnimFromOld() {
		DrzAnimation animation = new DrzAnimation();
		
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

		// ������������
		if (SubAnimationNum > 0) {

			animation.SetSubAnimtionNum(SubAnimationNum);
			
			// ��ʱ����
			int[] tmpInt = new int[2];
			for (int id = 0; id < AnimationCount; id++) {
				
				/**
				 * ���������ͣ���getAminationSetNameById��֪��ʲô��˼��
				 */
				buffer.position(AnimationOffset + (id * 120));
				
				int AnimationId = getInt();

				if (AnimationId < 1) // no more Animations
					break;
				
				DrzAnimationSet animSet = new DrzAnimationSet();

				// Set AnimationSetID
				animSet.AnimationTypeId = AnimationId;

				// ��ʼ֡
				buffer.position(AnimationOffset + (id * 120) + 4);// current animation starts at this frame
				tmpInt[0] = buffer.get()&0xFF;
				buffer.position(AnimationOffset + (id * 120) + 6);
				tmpInt[1] = buffer.get()&0xFF;
				
				animSet.AnimationStartKey = (tmpInt[1] << 8) + tmpInt[0];

				/**
				 * ����֡
				 */
				buffer.position(AnimationOffset + (id * 120) + 16);// current animation end at this frame
				tmpInt[0] = buffer.get()&0xFF;
				buffer.position(AnimationOffset + (id * 120) + 18);
				tmpInt[1] = buffer.get()&0xFF;

				animSet.AnimationEndKey1 = (tmpInt[1] << 8) + tmpInt[0];

				/**
				 * �����Ƿ��ظ�����
				 */
				buffer.position(AnimationOffset + (id * 120) + 108);
				
				animSet.Repeat = (getInt() == 1);

				// TODO δ֪�ַ�
				buffer.position(AnimationOffset + (id * 120) + 112);
				
				animSet.UnkChar = buffer.getChar();

				/**
				 * ��Ӧ������������
				 */
				buffer.position(AnimationOffset + (id * 120) + 116);
				
				int animIndex = getInt();
				if (animIndex > 0) {
					animIndex--;
				}
				animSet.SubAnimationIndex = animIndex;

				animation.mAnimationSetMap.put(id, animSet);
			}
		}
		
		return animation;
	}

	/**
	 * ������������
	 * @return
	 */
	private DrzAnimation readAnimFromNew() {
		DrzAnimation animation = new DrzAnimation();
		
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

		// ������������
		if (SubAnimationNum > 0) {
			animation.SetSubAnimtionNum(SubAnimationNum);

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
				animation.mAnimationSetMap.put(i, CurrentAnimationSet);
			}
		}
		
		return animation;
	}

	/**
	 * ��ȡ������
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