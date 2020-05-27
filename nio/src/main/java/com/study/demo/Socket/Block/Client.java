package com.study.demo.Socket.Block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Tiger
 * @date 2020-05-04
 * @see com.study.demo.Socket.Block
 **/
public class Client {
    public static void main(java.lang.String[] args) throws IOException {
        // 1.建立连接
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        // 2.获取文件
        FileChannel inputChannel = FileChannel.open(Paths.get("D:\\资源\\资料\\个人\\杨辉虎1.jpg"), StandardOpenOption.READ);
        // 3.创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 4.读取数据
        while (inputChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        // 5.告诉服务端自己发送完成了，不然会一直阻塞，无法进入下一步
        socketChannel.shutdownOutput();

        int length = 0;
        while ((length = socketChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            System.out.println(new java.lang.String(byteBuffer.array(), 0, length));
            byteBuffer.clear();
        }
        inputChannel.close();
        socketChannel.close();
    }
}
