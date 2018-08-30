package com.nxd.ftt.common.util;

import com.nxd.ftt.common.entity.Shell;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ProcessShell
 *
 * @author luhangqi
 * @date 2018/8/30
 */
public class ProcessShell {

    public static void main(String[] args) {
        try {
            String cmd = "reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\";
            Shell shell = execShell("cmd /c start E:/temp/regedit.bat");
            System.out.println(shell.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Shell execShell(String file) throws Exception {
        Shell shell = new Shell(1);
        StringBuffer buffer = new StringBuffer();
        Runtime runtime = Runtime.getRuntime();
        Process process;
        InputStream is;
        InputStreamReader isr;
        BufferedReader br;
        // 运行PING命令
        process = runtime.exec(file);
        process.waitFor();
        // 实例化输入流
        is = process.getInputStream();
        if (is.read() == -1) {
            is = process.getErrorStream();
            shell.setStatus(0);
        }
        // 把输入流转换成字节流
        isr = new InputStreamReader(is);
        // 从字节中读取文本
        br = new BufferedReader(isr);
        String line;
        int num = 0;
        while ((line = br.readLine()) != null) {
            if (num++ > 30) {
                //大于30行  退出
                break;
            }
            buffer.append(line);
        }
        is.close();
        isr.close();
        br.close();
        process.destroy();
        shell.setData(buffer.toString());
        return shell;
    }
}
