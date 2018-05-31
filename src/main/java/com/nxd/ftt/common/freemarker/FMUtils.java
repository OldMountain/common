package com.nxd.ftt.common.freemarker;

import freemarker.template.Configuration;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FMUtils
 *
 * @author luhangqi
 * @date 2018/2/5
 */
public class FMUtils {

    private static Pattern regex = Pattern.compile("#\\{[\\s\\S]*\\}");
    public static Properties prop;
    public static Map<String, Object> map = new HashMap<>();
    public static Configuration cfg;
    public static String objectName;

    public static String getNow(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }

    /**
     * yyyy/MM/dd
     *
     * @return
     */
    public static String getNow1() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        return df.format(new Date());
    }

    /**
     * yyyy-MM-dd
     *
     * @return
     */
    public static String getNow2() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    /**
     * 获取资源路径
     * @return
     */
    public static String getRootPath(){
        return Thread.currentThread().getContextClassLoader().getResource("").getPath();
    }

    /**
     * 读取配置文件
     *
     * @param propPath
     */
    public static Properties getProperties(String propPath) {
        prop = new Properties();
        // 以下方法读取属性文件会缓存问题
        // InputStream in = PropertiesUtils.class
        // .getResourceAsStream("/config.properties");
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(getRootPath() + propPath));
            prop.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }


    /**
     * 获取代码生成的文件路径
     *
     * @param key
     * @return
     */
    public static String getPropPath(String key) {
        String path = prop.getProperty(key);
        Matcher matchers = regex.matcher(path);
        int count = 0;
        while (matchers.find()) {
            String newPath = matchers.group(count).replace("#{", "");
            newPath = newPath.replace("}", "");
            path = path.replace(matchers.group(count), prop.getProperty(newPath));
            count++;
        }
        return path;
    }

    public static void savePath(Map<String, Object> map) {
        FMUtils.getFilePath("entity", map);
        FMUtils.getFilePath("dao", map);
        FMUtils.getFilePath("service", map);
        FMUtils.getFilePath("impl", map);
        FMUtils.getFilePath("mapper", map);
        FMUtils.getFilePath("controller", map);
        FMUtils.getFilePath("list", map);
        FMUtils.getFilePath("edit", map);
        FMUtils.getFilePath("view", map);
    }

    /**
     * 获取包名或文件相对路径
     *
     * @param name
     * @return
     */
    public static String getFilePath(String name, Map<String, Object> map) {
        String filePath = FMUtils.getPropPath("template." + name + ".path");
        String path = filePath.replaceAll("\\\\", "/");
        if (path.startsWith(prop.getProperty("src.java"))) {
            path = path.replace(prop.getProperty("src.java"), "");
            path = path.replaceAll("/", ".");
            if (path.startsWith(".")) {
                path = path.substring(1);
            }
            map.put(name, convertName("java", TempEnum.getValue(name)));
        } else if (path.startsWith(prop.getProperty("src.resources"))) {
            path = path.replace(prop.getProperty("src.resources"), "");
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            map.put(name, convertName("xml", TempEnum.getValue(name)));
        } else if (path.startsWith(prop.getProperty("src.webapp"))) {
            path = path.replace(prop.getProperty("src.webapp"), "");
            if (path.startsWith("/")) {
                path = path.substring(1);
                if (!path.endsWith("/")) {
                    path += "/";
                }
            }
            map.put(name, convertName("jsp", TempEnum.getValue(name)));
        }
        map.put(name + "Package", path);
        return path;
    }

    /**
     * 去掉后缀
     *
     * @param fileName
     * @return
     */
    public static String removeExt(String fileName) {
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        return fileName;
    }

    //组合命名
    public static String convertName(String etx, String... names) {
        StringBuilder builder = new StringBuilder();
        if ("java".equals(etx) || "xml".equals(etx)) {
            builder.append(String.valueOf(map.get("objectName")));
            for (int i = 0; i < names.length; i++) {
                if ("entity".equalsIgnoreCase(names[i])) {
                    break;
                }
                builder.append(toUpperFristChar(names[i]));
            }
        } else {
            builder.append(String.valueOf(map.get("objectName")).toLowerCase());
            for (int i = 0; i < names.length; i++) {
                builder.append("_");
                builder.append(names[i]);
            }
        }
//        builder.append(".");
//        builder.append(etx);
        return builder.toString();
    }

    /**
     * 首字母大写
     *
     * @param value
     * @return
     */
    public static String toUpperFristChar(String value) {
        char[] chars = value.toCharArray();
        int c = (int) chars[0];
        if(c > 96 && c < 123){
            chars[0] -= 32;
        }
        return String.valueOf(chars);
    }
}
