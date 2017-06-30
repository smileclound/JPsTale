package org.pstale.assets;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.apache.log4j.Logger;

/**
 * 图片加密/解密工具类
 * 
 * @author yanmaoyuan
 *
 */
public class ImageDecoder {

    static Logger log = Logger.getLogger(ImageDecoder.class);

    /**
     * 转换TGA文件
     * 
     * @param buffer
     *            从文件头开始的数据，至少18字节
     * @param readable
     *            是否使文件可读?
     */
    public static void convertTGA(byte[] buffer, boolean readable) {
        if (readable) {
            // 解密TGA
            buffer[0] = 0x0;
            buffer[1] = 0x0;
            for (byte i = 2; i < 18; i++) {
                buffer[i] -= (byte) (i * i);
            }
        } else {
            // 加密TGA
            buffer[0] = 0x47;
            buffer[1] = 0x38;
            for (byte i = 2; i < 18; i++) {
                buffer[i] += (byte) (i * i);
            }
        }
    }

    /**
     * 转换BMP文件
     * 
     * @param buffer
     *            从文件头开始的数据，至少16字节
     * @param readable
     *            是否使文件可读?
     */
    public static void convertBMP(byte[] buffer, boolean readable) {
        if (readable) {
            // 解密BMP
            buffer[0] = 0x42;
            buffer[1] = 0x4D;
            for (byte i = 2; i < 14; i++) {
                buffer[i] -= (byte) (i * i);
            }
        } else {
            // 加密BMP
            buffer[0] = 0x41;
            buffer[1] = 0x38;
            for (byte i = 2; i < 14; i++) {
                buffer[i] += (byte) (i * i);
            }
        }

    }

    /**
     * 将指定文件夹下所有bmp和tga图片解码。
     * 
     * @param folder
     */
    public static void decode(String folder) {
        decode(new File(folder));
    }

    public static void decode(File dir) {
        // 判断文件夹是否存在
        if (dir.exists() && dir.isDirectory()) {

            // 遍历bmp文件
            File[] files = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    String str = name.toLowerCase();
                    return str.endsWith(".bmp");
                }
            });// 读取文件列表
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {

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
            }

            // 遍历tga文件
            files = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    String str = name.toLowerCase();
                    return str.endsWith(".tga");
                }
            });// 读取文件列表
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
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
            }
        }
    }

    public static void encode(File dir) {
        // 判断文件夹是否存在
        if (dir.exists() && dir.isDirectory()) {
            // 遍历bmp文件
            File[] files = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    String str = name.toLowerCase();
                    return str.endsWith(".bmp");
                }
            });// 读取文件列表
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
                    try {
                        byte[] buffer = new byte[16];
                        RandomAccessFile raf = new RandomAccessFile(file, "rw");
                        raf.seek(0);
                        raf.readFully(buffer);

                        // 解码
                        if (buffer[0] == 0x42 && buffer[1] == 0x4D) {
                            log.info("Decode " + file.getAbsolutePath());
                            ImageDecoder.convertBMP(buffer, false);
                            raf.seek(0);
                            raf.write(buffer);
                        }

                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            // 遍历tga文件
            files = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    String str = name.toLowerCase();
                    return str.endsWith(".tga");
                }
            });// 读取文件列表
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (file.isFile()) {
                    try {
                        byte[] buffer = new byte[18];
                        RandomAccessFile raf = new RandomAccessFile(file, "rw");
                        raf.seek(0);
                        raf.readFully(buffer);

                        // 解码
                        if (buffer[0] == 0x00 && buffer[1] == 0x00) {
                            log.info("Encode " + file.getAbsolutePath());
                            ImageDecoder.convertTGA(buffer, false);
                            raf.seek(0);
                            raf.write(buffer);
                        }

                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
