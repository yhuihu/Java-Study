package com.study.demo.Socket.GroupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Tiger
 * @date 2020-05-04
 * @see com.study.demo.Socket.GroupChat
 **/
public class GroupChatClient {
    private final String host = "127.0.0.1";
    private final int port = 9990;
    private Selector selector = null;
    private SocketChannel socketChannel = null;
    private String username;

    GroupChatClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + "：注册成功");
    }

    //发送消息
    public void sendMessage(String info) throws IOException {
        info = username + "说：" + info;
        socketChannel.write(ByteBuffer.wrap(info.getBytes()));
    }

    //收到的消息
    public void readMessage() throws IOException {
        int read = selector.select();
        //有事情触通道
        if (read > 0) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    //得到通道
                    SocketChannel s = (SocketChannel) key.channel();
                    //写到buffer
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    s.read(byteBuffer);
                    byteBuffer.flip();
                    String msg = new String(byteBuffer.array());
                    System.out.println(msg.trim());
                } else {
                    System.out.println("没有可用通道");
                }
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //启动
        GroupChatClient groupChatClient = new GroupChatClient();
        //启动线程接收
        new Thread(() -> {
            while (true) {
                try {
                    groupChatClient.readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            groupChatClient.sendMessage(msg);
        }
    }
}
