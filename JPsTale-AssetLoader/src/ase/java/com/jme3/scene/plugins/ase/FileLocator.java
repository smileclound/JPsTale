package com.jme3.scene.plugins.ase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoadException;
import com.jme3.asset.AssetLocator;
import com.jme3.asset.AssetManager;

/**
 * I need a file locator ignore lower/upper case.
 * forgive me, I'm used to windows.
 * @author yanmaoyuan
 *
 */
public class FileLocator implements AssetLocator {

    private File root;

    public void setRootPath(String rootPath) {
        if (rootPath == null)
            throw new NullPointerException();
        
        try {
            root = new File(rootPath).getCanonicalFile();
            if (!root.isDirectory()){
                throw new IllegalArgumentException("Given root path \"" + root + "\" is not a directory");
            }
        } catch (IOException ex) {
            throw new AssetLoadException("Root path is invalid", ex);
        }
    }

    @SuppressWarnings("rawtypes")
    private static class FileAssetInfo extends AssetInfo {

        private File file;

		public FileAssetInfo(AssetManager manager, AssetKey key, File file){
            super(manager, key);
            this.file = file;
        }

        @Override
        public InputStream openStream() {
            try{
                return new FileInputStream(file);
            }catch (FileNotFoundException ex){
                // NOTE: Can still happen even if file.exists() is true, e.g.
                // permissions issue and similar
                throw new AssetLoadException("Failed to open file: " + file, ex);
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public AssetInfo locate(AssetManager manager, AssetKey key) {
        String name = key.getName();
        File file = new File(root, name);
        if (file.exists() && file.isFile()){
        	
        	/*
        	 * ԭ������Ƚ���file.getCanonicalPath()��file.getAbsolutePath()��������ǲ�ͬ�Ļ���
        	 * �ͻ��׳�һ��AssetNotFoundException������ʾAsset name doesn't match requirements��
        	 * ԭ���߿����ǿ�����UNIXϵͳ���ļ�����Сд���е����⣬����ȴ���ҷǳ��ǳ����ܣ�������
        	 * ��д������࣬����ɾ������δ��롣
        	 */
            return new FileAssetInfo(manager, key, file);
        }else{
            return null;
        }
    }

}
