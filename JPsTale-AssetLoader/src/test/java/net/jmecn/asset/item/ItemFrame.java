package net.jmecn.asset.item;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import net.jmecn.asset.ItemInitilize;

public class ItemFrame extends JFrame {

	public static void main(String[] args) {
		new ItemFrame();
	}
	
	private int height = 250;
	private int width = 500;
	
	private JLabel label = null;
	private JButton button = null;
	private JProgressBar processBar = null;

	public ItemFrame() {
		// 标题和大小
		this.setTitle("装备数据转换器");
		this.setSize(500, 250);
		this.setContentPane(getMainPanel());
		
		// 居中
		Dimension screenSize = this.getToolkit().getScreenSize();
		int left = (screenSize.width - width)/2;
		int top = (screenSize.height - height)/2;
		this.setLocation(left, top);

		// 显示
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private JPanel getMainPanel() {
		JPanel contentPanel = new JPanel();
		
		JPanel p1= new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("文件夹"));
		p1.add(new JTextField(30));
		p1.add(getButton());
		
		processBar = new JProgressBar();
		processBar.setPreferredSize(new Dimension(445, 20));
		processBar.setValue(0);
		
		JPanel p2= new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(processBar);
		
		label = new JLabel();
		JPanel p3= new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3.add(label);
		
		contentPanel.add(p1);
		contentPanel.add(p2);
		contentPanel.add(p3);
		
		return contentPanel;
	}
	
	private JButton getButton() {
		if (button == null) {
			button = new JButton("加载");
			button.addActionListener(listener);
		}
		
		return button;
	}
	
	private ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			new Thread(){
				public void run() {
					label.setText("开始解析..");
					button.setEnabled(false);
					processBar.setValue(10);
					ItemInitilize itemInit = new ItemInitilize();
					itemInit.init();
					
					label.setText("共解析装备数量:" + itemInit.getList().size());

					processBar.setValue(50);
					if (itemInit.validate() == false) {
						itemInit.localeItemName();
						
						ItemJSON json = new ItemJSON();
						
						int count = itemInit.getList().size();
						for(int i=0; i<count; i++) {
							ItemInfo info = itemInit.getList().get(i);
							try {
								// 文件名
								String filename = info.code.substring(1, 6).toUpperCase()+".json";
								
								// 生成文件内容
								String content = json.jsonItemInfo(info);
								
								// 保存文件
								PrintStream ps = new PrintStream(
										new FileOutputStream(
												new File(json.getFolder() + filename)));
								ps.print(content);
								ps.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							int v = i*50/count;
							processBar.setValue(50 + v);
						}
						label.setText("JSON 数据生成完毕..");
						
					}
					processBar.setValue(100);
					
					button.setEnabled(true);
				}
			}.start();
			
		}
	};

}
