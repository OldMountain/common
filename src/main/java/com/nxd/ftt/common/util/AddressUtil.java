package com.nxd.ftt.common.util;

import com.nxd.ftt.common.entity.AddressCode;

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
 * AddressUtil
 *
 * @author luhangqi
 * @date 2018/10/26
 */
public abstract class AddressUtil {

    private static String URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2017/index.html";

    private static Pattern PATTERN = Pattern.compile("<td><a href='[0-9/]*\\.html'>[\u4e00-\u9fa5]+</a>");

    private static Pattern PATTERN_LAST = Pattern.compile("<td>[0-9/]*</td><td>[0-9/]*</td><td>[\u4e00-\u9fa5]+</td>");

    private static List<AddressCode> list = new ArrayList<>();

    private static List<String> errorList = new ArrayList<>();

    public abstract void process(AddressCode code);

    public static void main(String[] args) {
//        System.out.println("");
        new AddressUtil() {
            @Override
            public void process(AddressCode code) {

            }
        }.getContent(URL, 0, 5, "", 0);
//        System.out.println(list.size());
//        for (int i = 0; i < errorList.size(); i++) {
////            getContent(URL + "index.html", 0, 3, "");
//        }
    }

    public List<AddressCode> getContent(String link, int count, int endCount, String name, int parentId) {

        count++;

        StringBuilder builder = null;
        InputStream in = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(link);
            URLConnection con = url.openConnection();
            //连接超时
            con.setConnectTimeout(10 * 1000);
            //网络访问超时
            con.setReadTimeout(10 * 1000);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            in = con.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, "gbk"));
            builder = new StringBuilder();
            String chars = "";
            while ((chars = reader.readLine()) != null) {
                builder.append(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                reOpenConn(link, reader, in, builder);
            } catch (Exception e1) {
                e1.printStackTrace();
                AddressCode code = new AddressCode();
                code.setLevel(count);
                code.setName(link);
                code.setParentId(parentId);
                process(code);
            }
        } finally {
            try {
                in.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (builder != null) {
            String content = builder.toString().replaceAll("<br/>", "");
            Matcher matcher;
            if (count == endCount) {
                matcher = PATTERN_LAST.matcher(content);
            } else {
                matcher = PATTERN.matcher(content);
            }
            while (matcher.find()) {
                String g = matcher.group();

                AddressCode code = new AddressCode();
                code.setLevel(count);
                code.setParentId(parentId);

                if (count == endCount) {
                    Map<String, String> codeMap = new HashMap<>();
                    String[] addr = g.split("</td>");
                    code.setCode(addr[0].split("<td>")[1]);
                    code.setType(addr[1].split("<td>")[1]);
                    code.setName(addr[2].split("<td>")[1]);
//                    codeMap.put(subLink.substring(subLink.indexOf("/") + 1, subLink.indexOf(".html")), tempName);
                    process(code);
                    list.add(code);
                    continue;
                }
                String subLink = g.substring(g.indexOf("'") + 1, g.lastIndexOf("'"));
                String tempName = name + g.split("'>")[1].split("</a>")[0];
                code.setName(g.split("'>")[1].split("</a>")[0]);
                if (count == 1) {
                    code.setCode(subLink.split("\\.")[0]);
                } else if (count < endCount) {
                    code.setCode(subLink.substring(3).split("\\.")[0]);
                } else {
                    break;
                }
                process(code);
                list.add(code);
                String subUrl = link;
                subUrl = subUrl.substring(0, subUrl.lastIndexOf("/") + 1) + subLink;
                getContent(subUrl, count, endCount, tempName, code.getId());
            }
        }

        return list;
    }

    public static String getURL() {
        return URL;
    }

    public void reOpenConn(String link, BufferedReader reader, InputStream in, StringBuilder builder) {
        try {
            Thread.sleep(10 * 1000);
            URL url = new URL(link);
            URLConnection con = url.openConnection();
            //连接超时
            con.setConnectTimeout(10 * 1000);
            //网络访问超时
            con.setReadTimeout(10 * 1000);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            in = con.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in, "gbk"));
            builder = new StringBuilder();
            String chars = "";
            while ((chars = reader.readLine()) != null) {
                builder.append(chars);
            }
        } catch (Exception e) {
            reOpenConn(link, reader, in, builder);
        }
    }
}
