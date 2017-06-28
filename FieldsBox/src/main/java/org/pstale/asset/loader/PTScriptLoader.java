package org.pstale.asset.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;

/**
 * 读取精灵的服务端脚本文件
 * @author yanmaoyuan
 *
 * @param <T> 可能是Monster/NPC/Item
 */
public abstract class PTScriptLoader<T> implements AssetLoader {
	static Logger log = Logger.getLogger(PTScriptLoader.class);
	
	private boolean handleToken = false;
	
	protected BufferedReader reader = null;
	protected String line;
	protected String[] token;
	
	private String charset = "gbk";
	
	/**
	 * 初始化，加载数据
	 */
	@Override
	public T load(AssetInfo assetInfo) throws IOException {
		// 加载文件
		reader = new BufferedReader(new InputStreamReader(assetInfo.openStream(), charset));

		// 解析文件
		T t = decode();

		// 关闭文件流
		reader.close();
		reader = null;
		return t;
	}

	public void setCharset(String charset) {
		if (charset != null) {
			this.charset = charset;
		}
	}

	/**
	 * 解析数据
	 * 
	 * @param file
	 * @return
	 */
	protected abstract T decode() throws IOException;
	
	/**
	 * 读取下一行文本
	 * 
	 * @throws IOException
	 */
	protected boolean nextLine() throws IOException {
		handleToken = false;
		boolean flag = false;

		if (reader != null && (line = reader.readLine()) != null) {
			line = line.trim();
			token = line.split("\\s+");// 拆解成token
			flag = true;
		}

		return flag;
	}

	/**
	 * 比较命令行，默认检查1个参数
	 * @param token
	 * @return
	 */
	protected boolean startWith(final String token) {
		return startWith(token, 1);
	}
	
	/**
	 * 比较命令行
	 * @param token
	 * @param argCnt 参数数量
	 * @return
	 */
	protected boolean startWith(final String token, final int argCnt) {
		boolean startToken = false;
		for(String start : token.split("\\|")) {
			if (start.equals(this.token[0])) {
				startToken = true;
				break;
			}
		}
		boolean flag = !handleToken && startToken && this.token.length > argCnt;
		if (flag) {
			handleToken = true;
		}
		return flag;
	}
	/**
	 * 读取文本数据
	 * @return
	 */
	protected String getString() {
		String value = line.substring(token[0].length()).trim();
		return value;
	}
	
	/**
	 * 读取整数
	 * @param index
	 * @return
	 */
	protected int getInt() {
		return getInt(0);
	}
	
	/**
	 * 读取整数
	 * @param index
	 * @return
	 */
	protected int getInt(int index) {
		int value = 0;
		// B_32_Ghoul.inf中，怪物格挡率为6%。。。多了一个%号。
		try {
			value = Integer.parseInt(token[1 + index]);
		} catch (NumberFormatException e) {
			log.error("数值解析失败", e);
		}
		return value;
	}
	
	/**
	 * 读取浮点数
	 * @param index
	 * @return
	 */
	protected float getFloat() {
		return getFloat(0);
	}
	
	/**
	 * 读取浮点数
	 * @param index
	 * @return
	 */
	protected float getFloat(int index) {
		return Float.parseFloat(token[1 + index]);
	}
	
	/**
	 * 读取短整数
	 * @param index
	 * @return
	 */
	protected short getShort() {
		return getShort(0);
	}
	
	/**
	 * 读取短整数
	 * @param index
	 * @return
	 */
	protected short getShort(int index) {
		short value = 0;
		try {
			value = Short.parseShort(token[1 + index]);
		} catch (Exception e) {
			log.error("数值解析失败", e);
		}
		return value;
	}
}
