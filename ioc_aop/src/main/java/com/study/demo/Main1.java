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
                "    public static void main(String[] args) {\n" +
                "        System.out.println(args[0]);\n" +
                "        System.out.println(args[1]);\n" +
                "        System.out.println(args[2]);\n" +
                "    }\n" +
                "}";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append((char) ((Math.random() * 26) + 97));
        }
        String replace = stringBuilder.toString();
        target = target.replace("public class Main", "public class " + replace);
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
        InputStream stderr = exec.getErrorStream();
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
//        Process process = run.exec("javac ");
    }
}
