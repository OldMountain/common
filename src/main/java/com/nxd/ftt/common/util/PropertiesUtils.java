/**
 * @Title: PropertiesUtils.java
 * @Package com.havebugs.common.util.file
 * Copyright: Copyright (c) 2015
 * Company:HAVEBUGS
 * @author Havebugs-John
 * @date 2015-3-1 下午3:21:15
 * @version V1.0
 */
package com.nxd.ftt.common.util;

import java.io.*;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * @author Havebugs-John
 * @ClassName: PropertiesUtils
 * @Description: * 对属性文件操作的工具类 获取，新增，修改 注意： 以下方法读取属性文件会缓存问题,在修改属性文件时，不起作用， 　InputStream in =
 * PropertiesUtils.class.getResourceAsStream("/config.properties"); 　解决办法： 　String savePath =
 * PropertiesUtils.class.getResource("/config.properties").getPath();
 * @date 2015-3-1 下午3:30:32
 */
public class PropertiesUtils {

    private static final Properties prop = new Properties();

    /**
     * getProperties(这里用一句话描述这个方法的作用)
     *
     * @param @return 设定文件
     * @return Properties 返回类型
     * @throws
     * @Title: getProperties
     */
    public static Properties getProperties() {
        InputStream in = null;
        try {
            if (prop.isEmpty()) {
                String savePath = FTools.getClassPath("config.properties");
                in = new BufferedInputStream(new FileInputStream(savePath));
                prop.load(in);
            }
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

    public static Properties getProperties(String fileName) {
        Properties properties = new Properties();
        InputStream in = FTools.getStream(fileName);
        InputStreamReader read = null;
        try {
            read = new InputStreamReader(in, "UTF-8");
            properties.load(read);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    /**
     * findPropertiesKey(获取属性文件的数据 根据key获取值)
     *
     * @param @param  key
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     * @Title: findPropertiesKey
     */
    public static String findPropertiesKey(String key) {
        try {
            if (prop.isEmpty()) {
                getProperties();
            }
            return prop.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findProperty(String key, String... strings) {
        try {
            if (prop.isEmpty()) {
                getProperties();
            }
            String property = prop.getProperty(key);
            if (strings != null && strings.length > 0) {
                MessageFormat mf = new MessageFormat(property);
                property = mf.format(strings);
            }
            return property;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * modifyProperties(写入properties信息)
     *
     * @param @param key
     * @param @param value 设定文件
     * @return void 返回类型
     * @throws
     * @Title: 写入properties信息
     */
    public static void modifyProperties(String key, String value) {
        try {
            // 从输入流中读取属性列表（键和元素对）
            Properties prop = getProperties();
            prop.setProperty(key, value);
            String path = PropertiesUtils.class.getResource("/config.properties").getPath();
            FileOutputStream outputFile = new FileOutputStream(path);
            prop.store(outputFile, "modify");
            outputFile.flush();
            outputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
