package com.study.demo;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author yhhu
 * @date 2021/2/4
 * @description
 */
public class TestApplication {
    public static void main(String[] args) throws JSchException, SftpException, IOException {
        JSch jSch = new JSch();
        Session sshSession = null;
        ChannelShell sftpChannel=null;
        sshSession = jSch.getSession("root", "115.xx.xx.xx",22);
        sshSession.setPassword("123456");
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.setTimeout(30000);
        sshSession.connect();
        Channel channel = sshSession.openChannel("shell");
        channel.connect();
        sftpChannel = ((ChannelShell) channel);
//        sftpChannel.setFilenameEncoding("UTF-8");
//        sftpChannel.put("D:\\bankcomm\\cq\\chromedriver_win32\\11.txt","/");
//        Vector ls = sftpChannel.ls("/");
//        for (Object l : ls) {
//            System.out.println(l);
//        }
//        System.out.println(sftpChannel.ls("/"));
        OutputStream outputStream = sftpChannel.getOutputStream();
        InputStream inputStream = sftpChannel.getInputStream();//从远端到达的数据  都能从这个流读取到
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.println("mkdir -p /codeup_test/");
        printWriter.println("exit");
        printWriter.flush();
        byte[] tmp = new byte[1024];
        while(true){
            while(inputStream.available() > 0){
                int i = inputStream.read(tmp, 0, 1024);
                if(i < 0) break;
                String s = new String(tmp, 0, i);
                if(s.indexOf("--More--") >= 0){
                    outputStream.write((" ").getBytes());
                    outputStream.flush();
                }
                System.out.println(s);
            }
            try{Thread.sleep(1000);}catch(Exception e){}
        }
//        outputStream.close();
//        inputStream.close();
//        sftpChannel.ls("/");
    }
}
