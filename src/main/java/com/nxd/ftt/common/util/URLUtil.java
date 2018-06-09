package com.nxd.ftt.common.util;

import com.nxd.ftt.common.exception.CommonException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * URLUtil
 *
 * @author luhangqi
 * @date 2018/6/8
 */
public class URLUtil {

    public static String getDownloadUrl(String url, Pattern pattern) throws IOException {
        URL URL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
        connection.connect();
        InputStream in = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader buffer = new BufferedReader(reader);
        StringBuilder content = new StringBuilder();
        String c = buffer.readLine();
        while (c != null) {
            content.append(c);
            c = buffer.readLine();
        }
        in.close();
        reader.close();
        buffer.close();
        connection.disconnect();
        Matcher matcher = pattern.matcher(content.toString());
        String g = null;
        while (matcher.find()) {
            g = matcher.group();
            break;
        }
        return g;
    }

    public static String getFileName(String link) {
        return link.substring(link.lastIndexOf("/") + 1);
    }

    public static String download(String fileDir, String link) throws IOException, CommonException {
        String fileName = getFileName(link);
        File file = new File(fileDir, fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            throw new CommonException("文件已存在");
        }else{
            file.createNewFile();
        }
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        InputStream in = connection.getInputStream();
        BufferedInputStream bfi = new BufferedInputStream(in);
        FileOutputStream out = new FileOutputStream(file);
        BufferedOutputStream bfo = new BufferedOutputStream(out);
        byte[] b = new byte[1024];
        int c;
        while ((c = bfi.read(b)) > 0) {
            bfo.write(b, 0, c);
        }
        out.flush();
        in.close();
        bfo.close();
        out.close();
        bfi.close();
        connection.disconnect();
        return fileName;
    }
}
