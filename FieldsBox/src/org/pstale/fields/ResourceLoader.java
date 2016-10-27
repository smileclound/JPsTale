package org.pstale.fields;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * 服务端数据加载基类
 * @author yanmaoyuan
 *
 * @param <T>
 */
public abstract class ResourceLoader<T> {
	
	private boolean handleToken = false;
	
	protected File file = null;
	protected BufferedReader reader = null;
	protected String line;
	protected String[] token;
	
	private String charset = "gbk";

	public void setCharset(String charset) {
		if (charset != null) {
			this.charset = charset;
		}
	}
	protected List<T> list = new ArrayList<T>();

	public List<T> getList() {
		return list;
	}
	
	/**
	 * 文件名过滤器
	 */
	private FilenameFilter filter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			return ResourceLoader.this.accept(dir, name);
		}
	};

	/**
	 * 文件名过滤规则
	 * 
	 * @param dir
	 * @param name
	 * @return
	 */
	protected abstract boolean accept(File dir, String name);
	
	/**
	 * 文件夹名称
	 * @return
	 */
	protected abstract String getFolder();
	
	/**
	 * 解析数据
	 * 
	 * @param file
	 * @return
	 */
	protected abstract T decode() throws IOException;
	
	/**
	 * 验证数据有效性
	 * 
	 * @return
	 */
	protected abstract boolean validate();
	
	/**
	 * 初始化，加载数据
	 */
	public void init() {
		File dir = new File(getFolder());

		// 判断文件夹是否存在
		if (dir.exists() && dir.isDirectory()) {

			// 遍历文件
			File[] files = dir.listFiles(filter);// 读取文件列表
			list = new ArrayList<T>();;// 清空列表
			for (int i=0; i<files.length; i++) {
				file = files[i];
				if (file.isFile()) {

					T t = null;
					try {
						// 加载文件
						reader = new BufferedReader(
								new InputStreamReader(
										new FileInputStream(file), charset));

						// 解析文件
						t = decode();
						if (t instanceof RespawnList) {
							((RespawnList)t).File = file.getName();
						}

						// 关闭文件流
						reader.close();
						reader = null;
					} catch (IOException e) {
						e.printStackTrace();
						t = null;
					}

					if (t != null) {
						list.add(t);
					}
				}
			}
			
		} else {
			dir.mkdirs();
			return;
		}
	}

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
			System.out.println(file.getName()); // TODO for test
			e.printStackTrace();
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
			System.out.println(file.getName());
			e.printStackTrace();
		}
		return value;
	}
}
