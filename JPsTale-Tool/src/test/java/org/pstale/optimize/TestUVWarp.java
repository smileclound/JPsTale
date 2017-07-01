package org.pstale.optimize;

import static org.pstale.utils.GridMesh.MAP_SIZE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoadException;
import com.jme3.asset.AssetManager;
import com.jme3.asset.DesktopAssetManager;
import com.jme3.scene.plugins.smd.SMDTYPE;
import com.jme3.scene.plugins.smd.SmdKey;
import com.jme3.scene.plugins.smd.SmdLoader;
import com.jme3.scene.plugins.smd.stage.Stage;
import com.jme3.scene.plugins.smd.stage.StageFace;

@SuppressWarnings("serial")
public class TestUVWarp extends JFrame {

    public static void main(String[] args) {
        new TestUVWarp().setVisible(true);
    }

    static Logger log = Logger.getLogger(TestUVWarp.class);
    // 绘图单元格的大小
    private int GRID_SIZE = 4;
    /**
     * 窗口控件
     */
    private JLabel statsText;// 状态栏
    private JFileChooser chooser;// 文件选择器
    private JCheckBox showMesh;

    // 背景底图
    private BufferedImage gridImg;
    private BufferedImage meshImg;

    // 绘图面板
    private MyPanel canvas;

    private AssetManager assetManager;
    private SmdLoader loader;

    private Stage mesh;// 网格

    public TestUVWarp() {

        // 初始化资源管理器
        assetManager = new DesktopAssetManager();
        assetManager.registerLoader(SmdLoader.class, "smd");
        // 读取地图的smd文件
        loader = new SmdLoader();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            log.warn("Could not set native look and feel.");
        }

        canvas = new MyPanel();

        this.setTitle("Test GridMesh");
        this.setSize(790, 910);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setJMenuBar(getJMenuBar());// 设置菜单
        this.setContentPane(getContentPanel());// 主界面布局
        this.chooser = getFileChooser();// 初始化文件选择器

        canvas.addMouseMotionListener(adapter);
    }

    /**
     * 根据文件名，导入smd文件，并生成网格。
     */
    private void initMesh(String fileName) {
        SmdKey key = new SmdKey(fileName, SMDTYPE.STAGE3D);
        File file = new File(fileName);
        MyAssetInfo info = new MyAssetInfo(assetManager, key, file);
        try {
            mesh = (Stage) loader.load(info);

            drawBackground();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "文件读取失败");
        }
    }

    /**
     * 主界面布局
     * 
     * @return
     */
    private JPanel getContentPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // 画板
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(canvas);
        panel.add(pane, BorderLayout.CENTER);

        // 工具条
        panel.add(getJToolBar(), BorderLayout.NORTH);

        // 状态栏
        JPanel status = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(status, BorderLayout.SOUTH);
        statsText = new JLabel("就绪");
        status.add(statsText);

        return panel;
    }

    /**
     * 菜单
     */
    public JMenuBar getJMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu fMenu = new JMenu("文件(F)");
        bar.add(fMenu);

        JMenuItem exItem = new JMenuItem("导入smd地图");
        exItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
                    return;
                }

                File file = chooser.getSelectedFile();
                String path = file.getAbsolutePath();

                Preferences pref = Preferences.userRoot().node("/org/pstale/test");
                pref.put("lastPath", file.getPath());

                initMesh(path);
            }
        });

        fMenu.add(exItem);

        return bar;
    }

    /**
     * 工具条
     * 
     * @return
     */
    public JToolBar getJToolBar() {
        JToolBar toolBar = new JToolBar("工具条");
        toolBar.setOrientation(JToolBar.HORIZONTAL);
        toolBar.setFloatable(false);
        toolBar.setAlignmentX(5);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEADING));

        final JLabel l1 = new JLabel("比例:");
        toolBar.add(l1);

        final JSlider rowSlider = new JSlider(JSlider.HORIZONTAL, 1, 8, GRID_SIZE);
        rowSlider.setMajorTickSpacing(1);
        rowSlider.setPaintLabels(true);
        // rowSlider.setPaintTicks(true);
        rowSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                GRID_SIZE = rowSlider.getValue();
                canvas.updateCanvas();
            }
        });
        toolBar.add(rowSlider);

        toolBar.addSeparator();

        showMesh = new JCheckBox("显示Mesh");
        showMesh.setSelected(true);
        showMesh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.updateCanvas();
            }
        });
        toolBar.add(showMesh);

        return toolBar;
    }

    /**
     * 文件选择器。 用户只能选择后缀为smd的文件
     * 
     * @return
     */
    private JFileChooser getFileChooser() {
        if (chooser == null) {

            Preferences pref = Preferences.userRoot().node("/org/pstale/test");
            String lastPath = pref.get("lastPath", "");
            if (!lastPath.equals("")) {
                chooser = new JFileChooser(lastPath);
            } else {
                chooser = new JFileChooser();
            }
            chooser.setDialogType(JFileChooser.OPEN_DIALOG);
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("打开地图文件");

            chooser.addChoosableFileFilter(new FileNameExtensionFilter("*.smd", "smd"));
        }
        return chooser;
    }

    /**
     * 绘制三角形网格 将3D的网格绘制到2D平面上。
     */
    private void drawBackground() {
        if (mesh == null)
            return;

        int nMatCnt = mesh.materialGroup.materialCount;

        // 准备画三角形
        int SIZE = 100 * GRID_SIZE;// 正方形的边长
        gridImg = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);

        BufferedImage[] uvs = new BufferedImage[nMatCnt];
        for (int i = 0; i < nMatCnt; i++) {

            if (mesh.materials[i].TextureCounter > 0) {
                uvs[i] = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = (Graphics2D) uvs[i].getGraphics();
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, SIZE, SIZE);

                String name = mesh.materials[i].smTexture[0].Name;
                g.setColor(Color.BLACK);
                g.drawString(name, 0, 20);
            }
        }

        /**
         * 根据三角形的顶点坐标，绘制整个地图的网格。
         */
        Polygon drawTri;// 临时变量，用来表示每个三角形
        int fCount = mesh.nFace;// 面数
        for (int i = 0; i < fCount; i++) {
            // 连续添加3个点，构建一个三角形。
            drawTri = new Polygon();
            StageFace f = mesh.Face[i];

            int matId = f.v[3];

            if (mesh.materials[matId].TextureCounter == 0) {
                continue;
            }

            Graphics2D g = (Graphics2D) uvs[f.v[3]].getGraphics();
            g.setColor(new Color(0.7f, 0.8f, 0.9f));
            if (f.TexLink == null)
                continue;

            for (int j = 0; j < 3; j++) {
                // 取顶点的x、z坐标，映射到2D平面上。
                float x = f.TexLink.u[j];
                float z = f.TexLink.v[j];

                int px = (int) (SIZE * x * 0.05f + SIZE * 0.5f);
                int py = (int) (SIZE * z * 0.05f + SIZE * 0.5f);

                drawTri.addPoint(px, py);
            }
            g.draw(drawTri);
        }

        for (int i = 0; i < nMatCnt; i++) {
            if (mesh.materials[i].TextureCounter > 0) {
                try {
                    ImageIO.write(uvs[i], "png", new File("D:\\uvs\\uvs" + i + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 用于协助SmdLoader定位资源
     * 
     * @author yanmaoyuan
     *
     */
    private class MyAssetInfo extends AssetInfo {
        private File file;

        public MyAssetInfo(AssetManager manager, SmdKey key, File file) {
            super(manager, key);
            this.file = file;
        }

        @Override
        public InputStream openStream() {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                throw new AssetLoadException("Failed to open file: " + file, ex);
            }
        }
    }

    /**
     * 绘图的面板。 先绘制网格底图，然后再绘制激活的网格。
     * 
     * @author yanmaoyuan
     *
     */
    private class MyPanel extends JPanel {

        BufferedImage buffer = new BufferedImage(MAP_SIZE * GRID_SIZE, MAP_SIZE * GRID_SIZE,
                BufferedImage.TYPE_INT_RGB);
        int WIDTH = MAP_SIZE * GRID_SIZE;
        int HEIGHT = MAP_SIZE * GRID_SIZE;

        public MyPanel() {
            resize();
        }

        private void resize() {
            buffer = new BufferedImage(MAP_SIZE * GRID_SIZE, MAP_SIZE * GRID_SIZE, BufferedImage.TYPE_INT_RGB);
            WIDTH = MAP_SIZE * GRID_SIZE;
            HEIGHT = MAP_SIZE * GRID_SIZE;
            setSize(WIDTH, HEIGHT);
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        @Override
        public void update(Graphics g) {
            paint(g);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D) buffer.getGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, WIDTH, HEIGHT);

            /**
             * 绘制背景
             */
            if (showMesh.isSelected()) {
                if (meshImg != null) {
                    g2d.drawImage(meshImg, 0, 0, null);
                }
            } else {
                if (gridImg != null) {
                    g2d.drawImage(gridImg, 0, 0, null);
                }
            }

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            g.drawImage(buffer, 0, 0, null);
        }

        private void updateCanvas() {
            drawBackground();
            canvas.resize();
            canvas.updateUI();
        }

    }

    /**
     * 鼠标移动的监听器
     */
    private MouseAdapter adapter = new MouseAdapter() {

        @Override
        public void mouseMoved(MouseEvent e) {
            // 计算当前在哪个格子内
            int x = e.getX() / GRID_SIZE;
            int y = e.getY() / GRID_SIZE;

            String str = String.format("格子(%3d, %3d)", x, y);
            statsText.setText(str);
        }
    };
}
