package com.nxd.ftt.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtils
 *
 * @author luhangqi
 * @date 2018/5/17
 */
public class DateUtils {

    private static String DAY_YEAR_MONTH_DAY_1 = "yyyy-MM-dd";

    public static String formateNowDay(){
        DateFormat df = new SimpleDateFormat(DAY_YEAR_MONTH_DAY_1);
        return df.format(new Date());
    }
}
