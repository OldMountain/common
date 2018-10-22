package com.nxd.ftt.common.util;

import java.io.InputStream;
import java.math.BigDecimal;

/**
 * FTools
 *
 * @author luhangqi
 * @date 2018/1/23
 */
public class FTools {

    /**
     * 获取class路径
     *
     * @return
     */
    public static String getClassPath() {
        return getClassPath(".");
    }

    public static String getClassPath(String source) {
        return String.valueOf(Thread.currentThread().getContextClassLoader().getResource(source).getPath()).replaceAll("%20", " ");
    }

    public static InputStream getStream(String source) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(source);
    }

    /**
     * 运算 加法
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String add(Object num1, Object num2) {
        BigDecimal numDec1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numDec2 = new BigDecimal(String.valueOf(num2));
        BigDecimal result = numDec1.add(numDec2);
        return result.toString();
    }

    /**
     * 运算 连加
     *
     * @param strings
     * @return
     */
    public static String addBatch(String... strings) {
        BigDecimal result = new BigDecimal(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            BigDecimal numDec2 = new BigDecimal(strings[i]);
            result = result.add(numDec2);
        }
        return result.toString();
    }

    /**
     * 运算 减法
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String sub(Object num1, Object num2) {
        BigDecimal numDec1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numDec2 = new BigDecimal(String.valueOf(num2));
        BigDecimal result = numDec1.subtract(numDec2);
        return result.toString();
    }

    /**
     * 运算 乘法
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String mul(Object num1, Object num2) {
        BigDecimal numDec1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numDec2 = new BigDecimal(String.valueOf(num2));
        BigDecimal result = numDec1.multiply(numDec2);
        return result.toString();
    }

    /**
     * 运算 除法，默认保留两位小数
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String div(Object num1, Object num2) {
        if ("0".equals(String.valueOf(num2))) {
            throw new ArithmeticException("除数不能为0");
        }
        BigDecimal numDec1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numDec2 = new BigDecimal(String.valueOf(num2));
        BigDecimal result = null;
        try {
            result = numDec1.divide(numDec2);
        } catch (ArithmeticException a) {
            result = numDec1.divide(numDec2, 2, BigDecimal.ROUND_HALF_DOWN);
        }
        return String.valueOf(result);
    }

    public static String div(Object num1, Object num2, int scale) {
        if ("0".equals(String.valueOf(num2))) {
            throw new ArithmeticException("除数不能为0");
        }
        BigDecimal numDec1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numDec2 = new BigDecimal(String.valueOf(num2));
        BigDecimal result;
        try {
            if (scale < 0) {
                result = numDec1.divide(numDec2);
            }else {
                result = numDec1.divide(numDec2, scale, BigDecimal.ROUND_HALF_DOWN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ArithmeticException("除法异常");
        }
        return result.toString();
    }

    /**
     * 获取后缀名
     *
     * @param fileName
     * @return
     */
    public static String getEtx(String fileName) {
        String etx;
        int index = fileName.lastIndexOf(".");
        if (index > 0) {
            etx = fileName.substring(index + 1);
        } else {
            etx = fileName;
        }
        return etx;
    }

    /**
     * 获取文件名
     *
     * @param fileName
     * @return
     */
    public static String getFileName(String fileName) {
        String etx;
        int start = fileName.lastIndexOf("/");
        int end = fileName.lastIndexOf(".");
        etx = fileName.substring(start == -1 ? 0 : start + 1, end == -1 ? fileName.length() : end);
        return etx;
    }

    /**
     * 获取文件大小
     *
     * @param size
     * @return
     */
    public static String getFileSize(long size){
        return getFileSize(size, -1);
    }

    public static String getFileSize(long size,int scale) {
        StringBuilder str = new StringBuilder();
        if (size < 1024) {
            str.append("1K");
        } else if (size < 1024 * 1024) {
            str.append(div(size, 1024,scale));
            str.append("K");
        } else if (size < 1024 * 1024 * 1024) {
            str.append(div(div(size, 1024,scale), 1024,scale));
            str.append("M");
        } else {
            str.append(div(div(div(size, 1024,scale), 1024,scale), 1024,scale));
            str.append("G");
        }
        return str.toString();
    }

}
