package org.pstale.desktop;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.pstale.app.LoaderApp;
import org.pstale.state.AxisAppState;
import org.pstale.state.GuiLoaderAppState;
import org.pstale.state.LoaderAppState;
import org.pstale.utils.FileChooser;
import org.pstale.utils.FolderChooser;
import org.pstale.utils.SkeletonToTree;

import com.jme3.animation.AnimControl;
import com.jme3.animation.Skeleton;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeCanvasContext;

/**
 * 主窗口
 * 
 * @author yanmaoyuan
 * 
 */
public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	private JmeCanvasContext context;
	private Canvas canvas;
	private LoaderApp app;

	private TextArea console;
	private FileChooser fileChooser = new FileChooser();
	private FolderChooser folderChooser = new FolderChooser();
	
	private JComboBox<String> combo;
	private JTree tree;

	public Main() {
		this.setTitle("Model Viewer");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				app.stop();
			}
		});

		setupUI();
		createMenu();

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * 界面布局
	 */
	private void setupUI() {

		Container main = getContentPane();
		
		Container canvasPanel = new JPanel();
		canvasPanel.setLayout(new BorderLayout());
		main.add(canvasPanel, BorderLayout.CENTER);
		
		// jME3渲染层
		createCanvas();
		canvasPanel.add(canvas, BorderLayout.CENTER);

		// 底部控制台
		console = new TextArea();
		console.setBackground(Color.WHITE);
		console.setEditable(false);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(console);
		canvasPanel.add(scrollPane, BorderLayout.SOUTH);

		// 用自己的重载的OutputStream创建一个PrintStream
		PrintStream printStream = new PrintStream(new MyOutputStream());
		// 指定标准输出到自己创建的PrintStream
		System.setOut(printStream);
		System.setErr(printStream);

		// 右侧的面板
		JTabbedPane tabs = new JTabbedPane();
		tabs.setPreferredSize(new Dimension(240, 0));
		tabs.add("控制面板", createCamera());
		
		main.add(tabs, BorderLayout.EAST);

	}

	/**
	 * 将数据输出到一个TextArea中。
	 * @author yanmaoyuan
	 *
	 */
	public class MyOutputStream extends OutputStream {
		public void write(int arg0) throws IOException {
			// 写入指定的字节，忽略
		}

		public void write(byte data[]) throws IOException {
			// 追加一行字符串
			console.append(new String(data));
		}

		public void write(byte data[], int off, int len) throws IOException {
			// 追加一行字符串中指定的部分，这个最重要
			console.append(new String(data, off, len));
			// 移动TextArea的光标到最后，实现自动滚动
			console.setCaretPosition(console.getText().length());
		}
	}

	/**
	 * 创建菜单
	 */
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("文件(F)");
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menuFile);

		final JMenuItem itemLoad = new JMenuItem("导入模型文件(L)");
		itemLoad.setMnemonic(KeyEvent.VK_L);
		itemLoad.setAccelerator(KeyStroke.getKeyStroke("L"));
		menuFile.add(itemLoad);
		itemLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadModel();
			}
		});

		final JMenuItem itemSaveAs = new JMenuItem("导入平面图(S)");
		itemSaveAs.setMnemonic(KeyEvent.VK_S);
		itemSaveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		menuFile.add(itemSaveAs);
		itemSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
				loadGui();
			}
		});

		menuFile.add(new JSeparator());

		JMenuItem itemExit = new JMenuItem("退出(X)");
		itemSaveAs.setMnemonic(KeyEvent.VK_X);
		itemExit.setAccelerator(KeyStroke.getKeyStroke("ctrl W"));
		menuFile.add(itemExit);
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
				app.stop();
			}
		});

		JMenu menuView = new JMenu("视图(V)");
		menuView.setMnemonic(KeyEvent.VK_V);
		menuBar.add(menuView);

		final JMenuItem itemAxis = new JMenuItem("关闭坐标轴");
		itemAxis.setMnemonic(KeyEvent.VK_F4);
		menuView.add(itemAxis);
		itemAxis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				app.enqueue(new Callable<Void>() {
					public Void call() {
						AxisAppState axis = app.getStateManager().getState(AxisAppState.class);
						if (axis.toggleAxis()) {
							itemAxis.setText("关闭坐标轴");
						} else {
							itemAxis.setText("打开坐标轴");
						}
						return null;
					}
				});
			}
		});

		JMenuItem itemWireframe = new JMenuItem("Wireframe");
		itemWireframe.setMnemonic(KeyEvent.VK_F3);
		menuView.add(itemWireframe);
		itemWireframe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				wireframe();
			}
		});

		JMenu menuOption = new JMenu("选项(O)");
		menuOption.setMnemonic(KeyEvent.VK_O);
		menuBar.add(menuOption);

		JMenuItem itemSetRootPath = new JMenuItem("设置资源根目录");
		menuOption.add(itemSetRootPath);
		itemSetRootPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setRootPath();
			}
		});

		JMenu menuHelp = new JMenu("帮助(H)");
		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuBar.add(menuHelp);

		JMenuItem itemAbout = new JMenuItem("关于(A)");
		itemAbout.setMnemonic(KeyEvent.VK_A);
		menuHelp.add(itemAbout);
		itemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(null, 
						new Object[]{"W/S-摄像机前/后移动", "A/D-摄像机左/右移动", "Q/Z-摄像机上/下移动", "C-查看摄像机位置参数", "M-查看内存参数", "F5-打开/关闭状态监控"},
						"操作说明",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

	/**
	 * 创建jME3的Canvas
	 */
	private void createCanvas() {
		AppSettings settings = new AppSettings(true);
		settings.setWidth(800);
		settings.setHeight(600);

		app = new LoaderApp(this);
		app.setPauseOnLostFocus(false);
		app.setSettings(settings);
		app.createCanvas();
		app.startCanvas();

		context = (JmeCanvasContext) app.getContext();
		canvas = context.getCanvas();
		canvas.setSize(settings.getWidth(), settings.getHeight());
	}

	/**
	 * 创建摄像机面板
	 * @return
	 */
	private Container createCamera() {
		JPanel panel = new JPanel(new BorderLayout());
		
		// 摄像机控制
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(p1, BorderLayout.SOUTH);

		p1.add(new JLabel("镜头移动速度:"));
		final JLabel spdLb = new JLabel("50");
		p1.add(spdLb);
		
		final JSlider slider = new JSlider(JSlider.HORIZONTAL, 5, 100, 50);
		p1.add(slider);
		slider.setMajorTickSpacing(10);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int speed = slider.getValue();
				spdLb.setText(speed+"");
				setMoveSpeed(speed);
			}
		});
		
		// 骨骼
		JScrollPane scrollPane = new JScrollPane();
		tree = new JTree();
		scrollPane.setViewportView(tree);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		// 选择动画面板
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(p2, BorderLayout.NORTH);
		
		p2.add(new JLabel("动画:"));
		combo = new JComboBox<String>();
		p2.add(combo);
		JButton btn = new JButton("播放");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj = combo.getSelectedItem();
				if (obj != null) {
					String name = (String)obj;
					play(name);
				}
			}
		});
		p2.add(btn);
		
		return panel;
	}

	/**
	 * 播放动画
	 * @param name
	 */
	protected void play(final String name) {
		app.enqueue(new Callable<Void>() {
			public Void call() {
				LoaderAppState state = app.getStateManager().getState(LoaderAppState.class);
				state.play(name);
				return null;
			}
		});
	}
	
	/**
	 * 开关网格
	 */
	void wireframe() {
		app.enqueue(new Callable<Void>() {
			public Void call() {
				LoaderAppState state = app.getStateManager().getState(LoaderAppState.class);
				state.wireframe();
				return null;
			}
		});
	}
	
	/**
	 * 开关坐标系
	 */
	void axis() {
		
	}

	/**
	 * 导入模型
	 */
	private void loadModel() {
		File file = fileChooser.getFile();
		if (file != null) {
			final String path = file.getAbsolutePath();
			app.enqueue(new Callable<Void>() {
				public Void call() {
					combo.removeAllItems();
					
					LoaderAppState state = app.getStateManager().getState(LoaderAppState.class);
					state.loadModel(path);
					
					AnimControl ac = state.getAnimControl();
					if (ac != null) {
						Skeleton ske = ac.getSkeleton();
						SkeletonToTree stt = new SkeletonToTree();
						tree.setModel(stt.make(ske));
					}
					
					return null;
				}
			});

		}
	}
	
	/**
	 * 导入模型
	 */
	private void loadGui() {
		File file = fileChooser.getFile();
		if (file != null) {
			final String path = file.getAbsolutePath();
			app.enqueue(new Callable<Void>() {
				public Void call() {
					combo.removeAllItems();
					
					GuiLoaderAppState state = app.getStateManager().getState(GuiLoaderAppState.class);
					state.loadModel(path);
					
					return null;
				}
			});

		}
	}
	
	/**
	 * 设置游戏根目录
	 */
	private void setRootPath() {
		File file = folderChooser.getFile();
		if (file != null) {
			final String path = file.getAbsolutePath();
			app.enqueue(new Callable<Void>() {
				public Void call() {
					LoaderAppState state = app.getStateManager().getState(LoaderAppState.class);
					state.setRootpath(path);
					
					return null;
				}
			});

		}
	}
	
	/**
	 * 设置摄像机的移动速度
	 * @param speed
	 */
	private void setMoveSpeed(final int speed) {
		app.enqueue(new Callable<Void>() {
			public Void call() {
				app.getFlyByCamera().setMoveSpeed(speed);
				return null;
			}
		});
		
	}

	public void setAnimList(Collection<String> collection) {
		List<String> names = new ArrayList<String>();
		names.addAll(collection);
		names.sort(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				int i = a.indexOf(" ");
				int j = b.indexOf(" ");
				int n = Integer.parseInt(a.substring(0, i));
				int m = Integer.parseInt(b.substring(0, j));
				return n-m;
			}});
		for(String name : names) {
			combo.addItem(name);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JPopupMenu.setDefaultLightWeightPopupEnabled(false);
				new Main();
			}
		});
	}

}
