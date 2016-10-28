package org.pstale.gui;

import javax.swing.JFrame;

import org.pstale.loader.FieldLoader;
import org.pstale.loader.SpcLoader;
import org.pstale.loader.SpmLoader;
import org.pstale.loader.SppLoader;

public class Main extends JFrame {
	
	private static final long serialVersionUID = 1L;
	FieldLoader fieldLoader;
	SppLoader sppLoader;
	SpmLoader spmLoader;
	SpcLoader spcLoader;
	
	/**
	 * 构造方法，初始化窗口
	 */
	public Main() {
		
		this.setTitle("区域管理器");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void start() {
		fieldLoader = new FieldLoader();
		fieldLoader.load();
		
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

}
