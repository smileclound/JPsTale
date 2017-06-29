package org.pstale.launcher;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.pstale.utils.ImageDecoder;
import org.pstale.utils.WavDecoder;

/**
 * 图片文件解码对话框，给用户显示解码进度。
 * 
 * @author yanmaoyuan
 *
 */
public class DecodeDialog extends JDialog {

    private static final long serialVersionUID = -6794032398317986049L;

    static Logger log = Logger.getLogger(DecodeDialog.class);

    private String clientRoot;

    private JProgressBar bar;

    private ThreadPoolExecutor poolExecutor;

    public DecodeDialog(Frame owner, String root) {
        super(owner, true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        this.clientRoot = root;

        this.setTitle("解码..");
        this.setSize(400, 80);
        this.setResizable(false);
        this.setModal(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // 界面布局
        Container container = this.getContentPane();

        bar = new JProgressBar();
        bar.setBorderPainted(true);
        bar.setStringPainted(true);
        bar.setString("0%");

        container.add(bar, BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                poolExecutor.shutdown();
            }
        });

        poolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1));
        poolExecutor.submit(decodeTask);
    }

    // 假设最深有6层目录
    private int[] dep = { 0, 0, 0, 0, 0, 0, 0 };
    private int[] cur = { 0, 0, 0, 0, 0, 0, 0 };

    private Runnable decodeTask = new Runnable() {
        public void run() {
            File root = new File(clientRoot);

            File[] files = root.listFiles();
            // 一级目录
            int len = files.length;
            dep[0] = len;
            cur[0] = 0;
            for (int i = 0; i < len; i++) {

                File file = files[i];
                setTitle("解码.." + file.getName());
                if (file.isDirectory()) {
                    try {
                        decode(file, 1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                updateProgress(0);
            }

            dispose();
        }
    };

    private int value = 0;
    // bmp文件
    private ArrayList<File> bmps = new ArrayList<File>();
    // tga文件
    private ArrayList<File> tgas = new ArrayList<File>();
    // wav文件
    private ArrayList<File> wavs = new ArrayList<File>();

    private byte[] buffer = new byte[2];

    /**
     * 图片解密
     */
    private void decode(File dir, int depth) throws IOException {

        // 文件夹
        ArrayList<File> dirs = new ArrayList<File>();
        bmps.clear();
        tgas.clear();
        wavs.clear();

        File[] files = dir.listFiles();
        int len = files.length;
        for (int i = 0; i < len; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                dirs.add(file);
            } else {
                String name = file.getName().toLowerCase();
                if (name.endsWith("bmp")) {
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                    in.read(buffer, 0, 2);
                    in.close();

                    if (buffer[0] == 0x41 && buffer[1] == 0x38) {
                        bmps.add(file);
                    }
                } else if (name.endsWith("tga")) {
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                    in.read(buffer, 0, 2);
                    in.close();

                    if (buffer[0] == 0x47 && buffer[1] == 0x38) {
                        tgas.add(file);
                    }
                } else if (name.endsWith("wav")) {
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                    in.read(buffer, 0, 2);
                    in.close();

                    if (buffer[0] == 0x4A && buffer[1] == 0x4F) {
                        wavs.add(file);
                    }
                }
            }
        }

        // 需要解码的文件数量
        len = wavs.size() + bmps.size() + tgas.size() + dirs.size();

        String title = "解码.." + dir.getName();
        setTitle(title);

        if (len == 0)
            return;

        dep[depth] = len;

        int size = bmps.size();
        cur[depth] = 0;
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                decodeBMP(bmps.get(i));

                updateProgress(depth);
            }
        }

        size = tgas.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                decodeTGA(tgas.get(i));

                updateProgress(depth);
            }
        }

        size = wavs.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                decodeWAV(wavs.get(i));

                updateProgress(depth);
            }
        }

        size = dirs.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                decode(dirs.get(i), depth + 1);

                updateProgress(depth);
            }
        }

    }

    private void updateProgress(int depth) {
        cur[depth] += 1;

        float sum = 0;
        float pro = 100;
        for (int i = 0; i <= depth; i++) {
            sum += pro * cur[depth] / dep[depth];
            pro /= dep[depth];
        }
        value = (int) sum;

        if (value == 0) {
            System.out.println(depth);
        }
        bar.setString(value + "%");
        bar.setValue(value);
    }

    private void decodeBMP(File file) {
        try {
            byte[] buffer = new byte[16];
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(0);
            raf.readFully(buffer);

            // 解码
            if (buffer[0] == 0x41 && buffer[1] == 0x38) {
                log.info("Decode " + file.getAbsolutePath());
                ImageDecoder.convertBMP(buffer, true);
                raf.seek(0);
                raf.write(buffer);
            }
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void decodeTGA(File file) {
        try {
            byte[] buffer = new byte[18];
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(0);
            raf.readFully(buffer);

            // 解码
            if (buffer[0] == 0x47 && buffer[1] == 0x38) {
                log.info("Decode " + file.getAbsolutePath());

                ImageDecoder.convertTGA(buffer, true);
                raf.seek(0);
                raf.write(buffer);
            }

            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void decodeWAV(File file) {
        try {
            byte[] buffer = new byte[4];
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            raf.seek(0);
            raf.readFully(buffer);

            // 解码
            if (buffer[0] == 0x4A && buffer[1] == 0x4F) {
                log.info("Decode " + file.getAbsolutePath());
                WavDecoder.convertWAV(buffer, true);
                raf.seek(0);
                raf.write(buffer);
            }

            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
