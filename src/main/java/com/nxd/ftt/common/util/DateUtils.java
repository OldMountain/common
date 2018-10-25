package com.nxd.ftt.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtils
 *
 * @author luhangqi
 * @date 2018/5/17
 */
public class DateUtils {

    private static DateFormat YYYY_MM_DD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static DateFormat MM_DD_FORMAT = new SimpleDateFormat("MM-dd");

    public static String formateNowDay() {
        return YYYY_MM_DD_FORMAT.format(new Date());
    }

    public static String formateNowDay(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }

    public static Long parse(String day){
        Long time = null;
        try {
            time = MM_DD_FORMAT.parse(day).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
