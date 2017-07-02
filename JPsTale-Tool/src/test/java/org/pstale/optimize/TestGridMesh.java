package org.pstale.optimize;

import static com.jme3.scene.VertexBuffer.Type.Index;
import static com.jme3.scene.VertexBuffer.Type.Position;
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
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.prefs.Preferences;

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
import org.pstale.assets.utils.SceneBuilder;
import org.pstale.utils.GridMesh;

import com.jme3.scene.Mesh;
import com.jme3.scene.plugins.smd.stage.Stage;
import com.jme3.util.LittleEndien;

@SuppressWarnings("serial")
public class TestGridMesh extends JFrame {

    public static void main(String[] args) {
        new TestGridMesh().setVisible(true);
    }

    static Logger log = Logger.getLogger(TestGridMesh.class);
    // 绘图单元格的大小
    private int GRID_SIZE = 3;
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

    private Mesh mesh;// 网格
    private FloatBuffer fb;// 顶点数据
    private IntBuffer ib;// 三角形数据

    /****
     * 对网格按格子分块的核心代码。
     */
    private GridMesh grid;

    public TestGridMesh() {

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
        File file = new File(fileName);
        try {
            Stage stage3D = new Stage();
            stage3D.loadFile(new LittleEndien(new FileInputStream(file)));
            
            if (stage3D.nFace > 0) {
                mesh = SceneBuilder.buildCollisionMesh(stage3D);
                grid = new GridMesh(mesh);
                // 获得顶点数据
                fb = (FloatBuffer) mesh.getBuffer(Position).getData();
                ib = (IntBuffer) mesh.getBuffer(Index).getData();
                drawBackground();
                System.gc();
            } else {
                JOptionPane.showMessageDialog(this, "网格面数为0");
            }
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

        // 准备画三角形
        int SIZE = MAP_SIZE * GRID_SIZE;// 正方形的边长
        gridImg = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
        meshImg = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);

        /**
         * 清屏，背景底色画成白色
         */
        Graphics2D g = (Graphics2D) gridImg.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, SIZE, SIZE);

        /**
         * 画上一层单色的网格线
         */
        g.setColor(new Color(0.9f, 0.9f, 0.9f));
        for (int i = 0; i < MAP_SIZE; i++) {
            g.drawLine(0, i * GRID_SIZE, SIZE, i * GRID_SIZE);
            g.drawLine(i * GRID_SIZE, 0, i * GRID_SIZE, SIZE);
        }

        ArrayList<Integer>[][] area = grid.getFaceList();

        // 统计最大面数，用来计算每个方块的颜色灰度值
        int maxCount = 0;
        for (int row = 0; row < MAP_SIZE; row++) {
            for (int col = 0; col < MAP_SIZE; col++) {

                if (area[row][col] == null)
                    continue;
                int n = area[row][col].size();
                if (maxCount < n)
                    maxCount = n;
            }
        }

        // 绘制网格
        for (int row = 0; row < MAP_SIZE; row++) {
            for (int col = 0; col < MAP_SIZE; col++) {

                if (area[row][col] == null)
                    continue;

                int n = area[row][col].size();
                int x = col * GRID_SIZE;
                int y = row * GRID_SIZE;

                if (n != 0) {
                    Color c = new Color(255 * n / maxCount, 255 * (maxCount - n) / maxCount, 0);
                    g.setColor(c);
                    g.fillRect(x, y, GRID_SIZE, GRID_SIZE);
                }
            }
        }

        g = (Graphics2D) meshImg.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, SIZE, SIZE);

        /**
         * 根据三角形的顶点坐标，绘制整个地图的网格。
         */
        g.setColor(new Color(0.7f, 0.8f, 0.9f));
        Polygon drawTri;// 临时变量，用来表示每个三角形
        int fCount = ib.limit() / 3;// 面数
        for (int i = 0; i < fCount; i++) {
            // 连续添加3个点，构建一个三角形。
            drawTri = new Polygon();
            for (int j = 0; j < 3; j++) {
                int vIndex = ib.get(i * 3 + j);
                // 取顶点的x、z坐标，映射到2D平面上。
                float x = fb.get(vIndex * 3);
                float z = fb.get(vIndex * 3 + 2);

                float X = x - grid.getMin().x;
                float Y = z - grid.getMin().z;

                int px = (int) (SIZE * X / grid.getWidth());
                int py = (int) (SIZE * Y / grid.getHeight());

                drawTri.addPoint(px, py);
            }
            g.draw(drawTri);
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

        ArrayList<Integer> faces = null;

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

            /**
             * 绘制参加碰撞检测的面
             */
            if (faces != null && faces.size() > 0) {
                int len = faces.size();

                // 临时变量，用来表示每个三角形
                Polygon drawTri;
                for (int i = 0; i < len; i++) {
                    int f = faces.get(i);

                    // 连续添加3个点，构建一个三角形。
                    drawTri = new Polygon();
                    for (int j = 0; j < 3; j++) {
                        int vIndex = ib.get(f * 3 + j);
                        // 取顶点的x、z坐标，映射到2D平面上。
                        float x = fb.get(vIndex * 3);
                        float z = fb.get(vIndex * 3 + 2);

                        float X = x - grid.getMin().x;
                        float Y = z - grid.getMin().z;

                        int px = (int) (MAP_SIZE * GRID_SIZE * X / grid.getWidth());
                        int py = (int) (MAP_SIZE * GRID_SIZE * Y / grid.getHeight());

                        drawTri.addPoint(px, py);
                    }

                    g2d.setColor(Color.red);
                    g2d.draw(drawTri);
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

            // 尚未导入地图数据
            if (grid == null)
                return;

            long time = System.nanoTime();
            ArrayList<Integer> faces = grid.getFaceList(x, y);
            time = System.nanoTime() - time;

            int len = 0;
            if (faces != null) {
                len = faces.size();
            }

            String str = String.format("格子(%3d, %3d) 三角形:%3d 用时:%.6f毫秒", x, y, len, time / 1000000f);
            statsText.setText(str);

            canvas.faces = faces;
            canvas.updateUI();
        }
    };
}
