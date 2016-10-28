package org.pstale.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * 进度条
 * 
 * @author yanmaoyuan
 * 
 */
public class Loading extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JProgressBar progressBar;

	public Loading() {
		this.setTitle("载入中..");
		this.setResizable(false);
		
		Container contentPane = getContentPane();
		
		progressBar = new JProgressBar();
		progressBar.setOrientation(JProgressBar.HORIZONTAL);
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(400, 50));
		
		contentPane.add(progressBar, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	/**
	 * 这个线程用于更新进度条
	 */
	Runnable guiThread = new Runnable() {
		@Override
		public void run() {
			ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
			LoadTask task = new LoadTask();
			Future<Void> future = executor.submit(task);
			while(!future.isDone()) {
				progressBar.setValue(task.value);
			}
			
			executor.shutdown();
			close();
		}
	};

	public void start() {
		new Thread(guiThread).start();
	}
	
	public void close() {
		this.dispose();
	}


	public static void main(String[] args) {

		new Loading().start();
	}

}
