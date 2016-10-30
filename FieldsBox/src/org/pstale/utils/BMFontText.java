package org.pstale.utils;

import org.pstale.fields.Field;
import org.pstale.loader.FieldLoader;

/**
 * 生成BMFont所需的所有字符
 * @author yanmaoyuan
 *
 */
public class BMFontText {

	static String[] texts = {
		"载入", "地图", "解析", "网格线", "显示隐藏",
		"所有", "列表", "区域", "音乐", "音量", "音效",
		"背景", "传送门", "门户", "刷怪点", "进度", "百分比",
		"坐标系", "打开关闭", "环境", "摄像机", "移动", "速度",
		"跳转", "正在", "完毕", "成功", "失败"
	};
	public static void main(String[] args) {
		StringBuffer buffer = new StringBuffer();
		
		// GUI字符
		for(String str : texts) {
			buffer.append(str);
		}
		// 所有怪物名字
		
		// 所有NPC名字
		
		// 所有装备名字
		
		// 所有地图名字
		Field[] fields = new FieldLoader().load();

		for(Field f : fields) {
			buffer.append(f.getTitle());
		}
		
		System.out.println(buffer.toString());
	}

}
