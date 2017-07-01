package org.pstale.assets.utils;

public class AssetNameUtils {
    
    /**
     * 获取资源所在的目录。
     * 
     * 例：若文件路径为 "Models/MyModel/MyModel.j3o"，返回 "Models/MyModel/"。
     * @param path 资源路径
     * @return 资源所在的目录
     */
    public static String getFolder(String path) {
        path = path.replaceAll("\\\\", "/");
        int idx = path.lastIndexOf('/');
        if (idx <= 0 || idx == path.length() - 1) {
            return "";
        } else {
            return path.substring(0, idx + 1);
        }
    }
    
    /**
     * 获得简单文件名
     * 
     * 例：若文件路径为 "Models/MyModel/MyModel.j3o"，返回 "MyModel.j3o"。
     * 
     * @param path
     * @return
     */
    public static String getName(String path) {
        path = path.replaceAll("\\\\", "/");
        int idx = path.lastIndexOf('/');
        if (idx != -1) {
            path = path.substring(idx + 1);
        }
        return path;
    }
    
    /**
     * 例：若文件路径为 "Models/MyModel/MyModel.j3o"，返回 "MyModel"。
     * 
     * @param orgin
     * @return
     */
    public static String getSimpleName(final String orgin) {
        if (orgin == null)
            return null;

        // 替换可能存在的window文件夹符号
        String path = orgin.replaceAll("\\\\", "/");

        // 掐头
        int idx = path.lastIndexOf("/");
        if (idx != -1) {
            path = path.substring(idx + 1);
        }

        // 去尾
        idx = path.indexOf(".");
        if (idx != -1) {
            path = path.substring(0, idx);
        }

        return path;
    }

    /**
     * 改变文件名后缀。
     * 
     * @param orgin 资源路径
     * @param ext 变更的后缀名
     * @return
     */
    public static String changeExt(final String orgin, final String ext) {
        String path = orgin;
        path = path.replaceAll("\\\\", "/");

        int idx = path.lastIndexOf('.') + 1;
        String dest = path.substring(0, idx) + ext;

        return dest;
    }
}
