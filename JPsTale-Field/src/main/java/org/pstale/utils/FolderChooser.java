package org.pstale.utils;

import java.io.File;

import javax.swing.JFileChooser;

public class FolderChooser {

    JFileChooser chooser;

    public FolderChooser() {
        chooser = new JFileChooser();

        if (new File("D:/Priston Tale/0_素材/Client").isDirectory()) {
            chooser.setCurrentDirectory(new File("D:/Priston Tale/0_素材/Client"));
        } else if (new File("F:/1_DEVELOP/3_素材").isDirectory()) {
            chooser.setCurrentDirectory(new File("F:/1_DEVELOP/3_素材"));
        } else if (new File("models").isDirectory()) {
            chooser.setCurrentDirectory(new File("models"));
        }
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("请选择根目录");
    }

    public File getFile() {
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        return chooser.getSelectedFile();
    }

    public static void main(String[] args) {
        new FolderChooser();
    }
}
