package com.study.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Tiger
 * @date 2020-11-12
 * @see com.study.demo
 **/
public class Main1 {

    public static void main(String[] args) throws IOException {
        String target = "public class Main {\n" +
                "    static class Solution {\n" +
                "        public void sayHello(String args){\n" +
                "            System.out.println(args);\n" +
                "        }\n" +
                "    }\n" +
                "    public static void main(String[] args) {\n" +
                "        Solution solution = new Solution();\n" +
                "        solution.sayHello(args[0]);\n" +
                "    }\n" +
                "}\n";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append((char) ((Math.random() * 26) + 97));
        }
        String replace = stringBuilder.toString();
        target = target.replace("public class Main {", "public class " + replace);
        System.out.println(target);
        File file = new File("D:\\" + replace + ".java");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(target.getBytes());
        byteBuffer.flip();
        channel.write(byteBuffer);
        channel.close();
        fileOutputStream.close();
        Runtime run = Runtime.getRuntime();
        Process exec = run.exec("javac " + replace + ".java", null, new File("D:\\"));
        InputStream stderr = exec.getInputStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        String command = "java " + replace + " hello";
        System.out.println(command);
        Runtime run1 = Runtime.getRuntime();
        Process exec1 = run1.exec(command, null, new File("D:\\"));
        InputStream stderr1 = exec1.getErrorStream();
        InputStream inputStream = exec1.getInputStream();
        InputStreamReader isr1 = new InputStreamReader(inputStream);
        BufferedReader br1 = new BufferedReader(isr1);
        String answer = null;
        while ((answer = br1.readLine()) != null) {
            System.out.println(answer);
        }
    }
}
