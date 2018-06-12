package com.nxd.ftt.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * DownloadUtil
 *
 * @author luhangqi
 * @date 2018/6/12
 */
public class DownloadUtil {


    public static void download(HttpServletResponse response, String rootPath, String savePath) {
        String localFileName = FTools.getFileName(savePath);
        try {
            download(response, rootPath, savePath, localFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 文件下载
     *
     * @param response
     * @param rootPath      文件存放根目录
     * @param savePath      文件保存路径
     * @param localFileName 文件下载重命名
     * @throws IOException
     */
    public static void download(HttpServletResponse response, String rootPath, String savePath, String localFileName) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(localFileName, "UTF-8") + "." + FTools.getEtx(savePath));
        File file = new File(rootPath, savePath);
        OutputStream out = null;
        try (
                InputStream in = new FileInputStream(file);
                BufferedInputStream bi = new BufferedInputStream(in);
        ) {
            out = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            if ((length = bi.read(b)) != -1) {
                out.write(b, 0, length);
            }
        } finally {
            out.close();
        }
    }
}
