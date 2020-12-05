package com.study.demo.Socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * @author Tiger
 * @date 2020-05-26
 * @see com.study.demo.Socket
 **/
public class NetDownload {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://yhhu.oss-cn-shenzhen.aliyuncs.com/20170513133210763.jpg");
        HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
        httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");
        InputStream inputStream = httpcon.getInputStream();
        ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
        FileChannel outChannel = new FileOutputStream("D:\\资源\\test.jpg").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        while (readableByteChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
    }
}
