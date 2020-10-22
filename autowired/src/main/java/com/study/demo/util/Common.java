package com.study.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;

/**
 * @author Tiger
 * @date 2020-10-18
 * @see com.study.demo.util
 **/
@Order(1)
public class Common implements ApplicationRunner {
    private static Logger log = LoggerFactory.getLogger(Common.class);
    private static Map<String, String> switchMap;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        File file = new File("D://switch.json");
        if (!file.exists()) {
            boolean mkdir = file.mkdir();
            if (!mkdir) {
                log.error("创建文件错误");
                System.exit(0);
            }
        } else {
            FileInputStream fileInputStream=new FileInputStream(file);
            FileChannel channel = fileInputStream.getChannel();
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            channel.read(byteBuffer);
        }
    }
}
