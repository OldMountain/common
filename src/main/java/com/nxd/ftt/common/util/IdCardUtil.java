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

    private static String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/";

    private static Pattern PATTERN = Pattern.compile("<td><a href='[0-9,/]*\\.html'>[\u4e00-\u9fa5]+<br/>");

    private static List<Map<String, String>> list = new ArrayList<>();


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

    public static String getProvince() {
        List<Map<String, String>> provinces = getContent(URL + "index.html", 0, 3);
        System.out.println(provinces.size());
        return "";
    }

    public static void main(String[] args) {
        getProvince();
    }

    public static List<Map<String, String>> getContent(String link, int count, int endCount) {
        try {
            count++;

            URL url = new URL(link);
            URLConnection con = url.openConnection();
            con.connect();
            InputStream in = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gbk"));
            StringBuilder builder = new StringBuilder();
            String chars = "";
            while ((chars = reader.readLine()) != null) {
                builder.append(chars);
            }
            String content = builder.toString();
            Matcher matcher = PATTERN.matcher(content);
            while (matcher.find()) {
                String g = matcher.group();
                String subLink = g.substring(g.indexOf("'") + 1, g.lastIndexOf("'"));
                System.out.println(subLink);
                if (count == endCount) {
                    Map<String, String> codeMap = new HashMap<>();
                    codeMap.put(subLink.substring(subLink.indexOf("/") + 1, subLink.indexOf(".html")), subLink);
                    list.add(codeMap);
                    break;
                } else {
                    getContent(URL + subLink, count, endCount);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
