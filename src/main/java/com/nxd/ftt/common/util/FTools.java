package com.nxd.ftt.common.util;

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
     * @return
     */
    public static String getClassPath(){
        return String.valueOf(Thread.currentThread().getContextClassLoader().getResource("/").getPath());
    }

    /**
     * 运算 加法
     * @param num1
     * @param num2
     * @return
     */
    public static String add(Object num1,Object num2){
        BigDecimal numDec1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numDec2 = new BigDecimal(String.valueOf(num2));
        BigDecimal result = numDec1.add(numDec2);
        return result.toString();
    }

    /**
     * 运算 连加
     * @param strings
     * @return
     */
    public static String addBatch(String... strings){
        BigDecimal result = new BigDecimal(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            BigDecimal numDec2 = new BigDecimal(strings[i]);
            result = result.add(numDec2);
        }
        return result.toString();
    }

    /**
     * 运算 减法
     * @param num1
     * @param num2
     * @return
     */
    public static String sub(Object num1,Object num2){
        BigDecimal numDec1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numDec2 = new BigDecimal(String.valueOf(num2));
        BigDecimal result = numDec1.subtract(numDec2);
        return result.toString();
    }

    /**
     * 运算 乘法
     * @param num1
     * @param num2
     * @return
     */
    public static String mul(Object num1,Object num2){
        BigDecimal numDec1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numDec2 = new BigDecimal(String.valueOf(num2));
        BigDecimal result = numDec1.multiply(numDec2);
        return result.toString();
    }

    /**
     * 运算 除法，默认保留两位小数
     * @param num1
     * @param num2
     * @return
     */
    public static String div(Object num1,Object num2){
        if("0".equals(String.valueOf(num2))){
            throw new ArithmeticException("除数不能为0");
        }
        BigDecimal numDec1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numDec2 = new BigDecimal(String.valueOf(num2));
        BigDecimal result = null;
        try {
            result = numDec1.divide(numDec2);
        } catch (ArithmeticException a) {
            result = numDec1.divide(numDec2,2,BigDecimal.ROUND_HALF_DOWN);
        }
        return String.valueOf(result);
    }

    public static String div(Object num1,Object num2,int scale){
        if (scale < 0){
            throw new IllegalArgumentException("精确位数不能小于0");
        }
        if("0".equals(String.valueOf(num2))){
            throw new ArithmeticException("除数不能为0");
        }
        BigDecimal numDec1 = new BigDecimal(String.valueOf(num1));
        BigDecimal numDec2 = new BigDecimal(String.valueOf(num2));
        BigDecimal result;
        try {
            result = numDec1.divide(numDec2,scale,BigDecimal.ROUND_HALF_DOWN);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ArithmeticException("除法异常");
        }
        return result.toString();
    }


}
