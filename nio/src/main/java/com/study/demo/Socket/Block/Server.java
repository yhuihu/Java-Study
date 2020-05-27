package com.study.demo.Socket.Block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Tiger
 * @date 2020-05-04
 * @see com.study.demo.Socket.Block
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        // 1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2.文件通道
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\资源\\2.jpg"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        // 3.绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8888));
        // 4.获取客户端连接的通道
        SocketChannel accept = serverSocketChannel.accept();
        // 5.分配缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 6.接收数据
        while (accept.read(byteBuffer) != -1) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        byteBuffer.put("接收完成".getBytes());
        byteBuffer.flip();
        accept.write(byteBuffer);
        accept.close();
        outChannel.close();
        serverSocketChannel.close();
    }
}
