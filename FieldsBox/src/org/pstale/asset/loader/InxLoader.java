package org.pstale.asset.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.pstale.asset.struct.PAT3D;

import com.jme3.animation.Animation;
import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;

/**
 * 精灵场景加载器
 * 
 * @author yanmaoyuan
 * 
 */
public class InxLoader extends ByteReader implements AssetLoader {

	static Logger log = Logger.getLogger(InxLoader.class);

	@Override
	public Object load(AssetInfo inso) throws IOException {
		// 文件长度不对
		if (inso.openStream().available() <= 67083) {
			log.warn("Error: can't read inx-file (invalid file content)");
			return null;
		}

		AssetKey<?> key = inso.getKey();
		AssetManager manager = inso.getManager();
		
		getByteBuffer(inso.openStream());

		String smdFile = getString(64);
		String smbFile = getString(64);

		if (smdFile.length() > 0) {
			smdFile = changeName(smdFile);
		}

		if (smbFile.length() > 0) {
			smbFile = changeName(smbFile);
		}

		DrzAnimation anim;
		String sharedInxFile;
		if (buffer.limit() <= 67084) { // old inx file
			buffer.position(61848);
			sharedInxFile = getString();
			handleShared(sharedInxFile);
			anim = readAnimFromOld();
		} else { // new inx file (KPT)
			buffer.position(88472);
			sharedInxFile = getString();
			handleShared(sharedInxFile);
			anim = readAnimFromNew();
		}

		PAT3D BipPattern = null;
		// Read Animation from smb
		if (smbFile.length() > 0) {
			// 后缀名改为smb
			int n = smbFile.lastIndexOf(".");
			String str = smbFile.substring(0, n);
			smbFile = str + ".smb";

			BipPattern = (PAT3D) manager.loadAsset(new SmdKey(key.getFolder() + smbFile, SMDTYPE.PAT3D));
		}

		// Read Mesh from smd
		// 后缀名改为smd
		int n = smdFile.lastIndexOf(".");
		String str = smdFile.substring(0, n);
		smdFile = str + ".smd";

		SmdKey smdKey = new SmdKey(key.getFolder() + smdFile, SMDTYPE.PAT3D_VISUAL);
		smdKey.setBone(BipPattern);

		return manager.loadAsset(smdKey);
	}

	/**
	 * 共享动画数据
	 */
	private void handleShared(String sharedInxFile) {
		if (sharedInxFile == null || sharedInxFile.length() == 0)
			return;

		// 后缀名改为inx
		int n = sharedInxFile.lastIndexOf(".");
		String str = sharedInxFile.substring(0, n);
		sharedInxFile = str + ".inx";

		sharedInxFile = changeName(sharedInxFile);

		log.debug("使用共享的动画数据:" + sharedInxFile);
		// 读取共享的动画
		File file = new File(sharedInxFile);
		if (file.exists()) {
			try {
				InputStream inputStream = new FileInputStream(file);
				int length = inputStream.available();

				if (length <= 67083) {
					log.warn("Error: can't read inx-file (invalid file content):" + length);
				} else {
					getByteBuffer(inputStream);

					buffer.position(64);
					String smbFile = getString();
					if (smbFile.length() > 0) {
						smbFile = changeName(smbFile);
					}

					// FIXME 没有正确使用
					log.debug("使用了共享的骨骼动画:" + smbFile);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			log.warn("Error: " + sharedInxFile + " not exists.");
		}
	}

	class DrzAnimationSet {
		public int AnimationIndex;

		public int AnimationTypeId;

		public double SetStartTime;// 开始时间 * 160
		public double SetEndTime1;// 结束时间 * 160
		public double AnimationDurationTime;// 总时长 * 160

		public int AnimationStartKey;
		public int AnimationEndKey1;
		public int AnimationEndKey2;
		public int AnimationDurationKeys;

		public boolean Repeat;// 是否重复
		public char UnkChar;
		public int SubAnimationIndex;// 对应动画的索引

		public DrzAnimationSet() {

		}

		public DrzAnimationSet(int _ani_type_id, int _start_key, int _end_key,
				boolean _repeat, char _unk_letter, int _sub_ani_index) {
			AnimationStartKey = _start_key;
			AnimationEndKey1 = _end_key;
			Repeat = _repeat;
			UnkChar = _unk_letter;
			SubAnimationIndex = _sub_ani_index;
			AnimationTypeId = _ani_type_id;
		}

		public String toString() {
			String name = getAnimationSetNameById(AnimationTypeId);
			float length = (float) AnimationDurationTime * 160;
			return String.format(
					"[%d %s]SubAnimInx=%d Type=%d 开始帧=%d 结束帧=%d 重复=%b 时间=%.2f",
					AnimationIndex, name, SubAnimationIndex, AnimationTypeId,
					AnimationStartKey, AnimationEndKey1, Repeat, length);
		}

		public String getName() {
			return AnimationIndex + " "
					+ getAnimationSetNameById(AnimationTypeId);
		}

		public float getLength() {
			return (float) AnimationDurationTime * 160;
		}

		public Animation newJmeAnimation() {
			return new Animation(getName(), getLength());
		}
	}

	class DrzAnimation {
		String mAnimationName;

		HashMap<Integer, DrzAnimationSet> mAnimationSetMap = new HashMap<Integer, DrzAnimationSet>();

		List<DrzInxMeshInfo> meshDefInfo = new ArrayList<DrzInxMeshInfo>();

		int mSubAnimationNum;
	}

	/**
	 * 解析动画索引
	 * 
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

		// 解析动画索引
		if (SubAnimationNum > 0) {

			animation.mSubAnimationNum = SubAnimationNum;

			// 临时变量
			int[] tmpInt = new int[2];
			for (int id = 0; id < AnimationCount; id++) {

				/**
				 * 动画的类型，看getAminationSetNameById就知道什么意思了
				 */
				buffer.position(AnimationOffset + (id * 120));

				int AnimationId = getInt();

				if (AnimationId < 1) // no more Animations
					break;

				DrzAnimationSet animSet = new DrzAnimationSet();

				// Set AnimationSetID
				animSet.AnimationTypeId = AnimationId;

				// 开始帧
				buffer.position(AnimationOffset + (id * 120) + 4);// current
																	// animation
																	// starts at
																	// this
																	// frame
				tmpInt[0] = buffer.get() & 0xFF;
				buffer.position(AnimationOffset + (id * 120) + 6);
				tmpInt[1] = buffer.get() & 0xFF;

				animSet.AnimationStartKey = (tmpInt[1] << 8) + tmpInt[0];

				/**
				 * 结束帧
				 */
				buffer.position(AnimationOffset + (id * 120) + 16);// current
																	// animation
																	// end at
																	// this
																	// frame
				tmpInt[0] = buffer.get() & 0xFF;
				buffer.position(AnimationOffset + (id * 120) + 18);
				tmpInt[1] = buffer.get() & 0xFF;

				animSet.AnimationEndKey1 = (tmpInt[1] << 8) + tmpInt[0];

				/**
				 * 动画是否重复播放
				 */
				buffer.position(AnimationOffset + (id * 120) + 108);

				animSet.Repeat = (getInt() == 1);

				buffer.position(AnimationOffset + (id * 120) + 112);

				animSet.UnkChar = buffer.getChar();

				/**
				 * 对应动画的索引号
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
	 * 解析动画索引
	 * 
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

		// 解析动画索引
		if (SubAnimationNum > 0) {
			animation.mSubAnimationNum = SubAnimationNum;

			for (int i = 0; i < AnimationCount; i++) {
				buffer.position(AnimationOffset + (i * 172));
				int AnimationId = getInt();

				if (AnimationId < 1) // no more Animations
					break;

				DrzAnimationSet CurrentAnimationSet = new DrzAnimationSet();

				// Set AnimationSetID
				CurrentAnimationSet.AnimationTypeId = AnimationId;

				int[] val = new int[2];

				buffer.position(AnimationOffset + (i * 172) + 4);// current
																	// animation
																	// starts at
																	// this
																	// frame
				val[0] = buffer.get() & 0xFF;
				buffer.position(AnimationOffset + (i * 172) + 6);
				val[1] = buffer.get() & 0xFF;

				CurrentAnimationSet.AnimationStartKey = 160 * ((val[1] << 8) + val[0]);

				buffer.position(AnimationOffset + (i * 172) + 16);// current
																	// animation
																	// end at
																	// this
																	// frame
				val[0] = buffer.get() & 0xFF;
				buffer.position(AnimationOffset + (i * 172) + 18);
				val[1] = buffer.get() & 0xFF;
				CurrentAnimationSet.AnimationEndKey1 = 160 * ((val[1] << 8) + val[0]);

				buffer.position(AnimationOffset + (i * 172) + 24);// secound end
																	// key,
																	// downt
																	// know why
				val[0] = buffer.get() & 0xFF;
				buffer.position(AnimationOffset + (i * 172) + 26);
				val[1] = buffer.get() & 0xFF;
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

	class DrzInxMeshInfo {
		int type = -1;
		String meshName1;
		String meshName2;
		String meshName3;
		String meshName4;
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

	private String getAnimationSetNameById(int id) {
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

	/*******************************************************
	 * 下面的代码用于根据精灵的数据结构创建JME3的纹理、材质、网格等对象
	 *******************************************************/

	/**
	 * 改变文件的后缀名
	 * 
	 * @param line
	 * @return
	 */
	private String changeName(String line) {
		line = line.replaceAll("\\\\", "/");
		int index = line.lastIndexOf("/");
		if (index != -1) {
			line = line.substring(index + 1);
		}

		return line;
	}

}
