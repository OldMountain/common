package com.nxd.ftt.common.util;

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
public abstract class URLUtil {

    /**
     * 下载之前
     * @param contentLength 文件大小
     * @param file 输出文件
     */
    public abstract void before(int contentLength,File file);

    public abstract void after(File file);

    /**
     * 下载进度
     */
    public abstract void progress(Long read);

    public String getDownloadUrl(String url, Pattern pattern) throws IOException {
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

    public String getFileName(String link) {
        return link.substring(link.lastIndexOf("/") + 1);
    }

    public File download(String fileDir, String link) throws IOException {
        String fileName = getFileName(link);
        return download(fileDir, link, fileName);
    }

    public File download(String fileDir, String link, String fileName) throws IOException {

        File file = new File(fileDir, fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        int contentLength = connection.getContentLength();
        before(contentLength,file);
        InputStream in = connection.getInputStream();
        BufferedInputStream bfi = new BufferedInputStream(in);
        FileOutputStream out = new FileOutputStream(file);
        BufferedOutputStream bfo = new BufferedOutputStream(out);
        byte[] b = new byte[1024];
        int c;
        Long read = 0L;
        while ((c = bfi.read(b)) > 0) {
            bfo.write(b, 0, c);
            read += c;
            progress(read);
        }
        out.flush();
        in.close();
        bfo.close();
        out.close();
        bfi.close();
        connection.disconnect();
        after(file);
        return file;
    }

}
