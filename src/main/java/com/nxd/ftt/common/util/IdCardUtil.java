package com.nxd.ftt.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 星座查询
 *
 * @author luhangqi
 * @date 2018/10/25
 */
public class IdCardUtil {

    /**
     * 根据身份证号获取星座
     *
     * @param cardId
     * @return
     */
    public static String getConstellation(String cardId) {

        return "";
    }

    public static String card15to18(String cardId) {
        //15 位身份证
        StringBuilder builder = new StringBuilder(cardId);
        if (cardId.length() == 15) {
            builder.insert(6, "19");
        }
        return builder.toString();
    }

}
