package com.study.demo.Socket.GroupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author Tiger
 * @date 2020-05-04
 * @see com.study.demo.Socket.GroupChat
 **/
public class GroupChatServer {
    private Selector selector = null;
    private ServerSocketChannel serverSocketChannel = null;
    private static final int port = 9990;

    GroupChatServer() {
        try {
            //得到选择器
            selector = Selector.open();
            //创建管道
            serverSocketChannel = ServerSocketChannel.open();
            //绑定端口
            serverSocketChannel.bind(new InetSocketAddress(port));
            //设置非阻塞
            serverSocketChannel.configureBlocking(false);
            //注册到选择器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000);
                //有事件要处理
                if (count > 0) {
                    //遍历selectionKey
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        //取出key
                        SelectionKey key = iterator.next();
                        //观察是什么事件
                        if (key.isAcceptable()) {
                            //可接受状态，进行注册
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            //设置监听接收
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + ":上线");
                        } else if (key.isReadable()) {
                            //通道为可读状态
                            readData(key);
                        }
                        //删除当前key，防止重复操作
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待中");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读取消息
    public void readData(SelectionKey selectionKey) {
//        定义一个socketChannel
        SocketChannel channel = null;
        try {
            //取到关联的通道
            channel = (SocketChannel) selectionKey.channel();
            //创建Buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = channel.read(byteBuffer);
            if (count > 0) {
                byteBuffer.flip();
                String s = new String(byteBuffer.array());
                System.out.println("接收到客户端消息：" + s);
                //向其他客户端转发消息
                sendMessage(s, channel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送消息给其他用户（通道）
    private void sendMessage(String message, SocketChannel socketChannel) {
        System.out.println("服务器转发消息");
        //遍历
        for (SelectionKey key : selector.keys()) {
            //取出通道
            Channel channel = key.channel();
            //排除自己
            if (channel instanceof SocketChannel && (channel != socketChannel)) {
                ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
                SocketChannel sc = (SocketChannel) channel;
                //将数据写入通道
                try {
                    sc.write(byteBuffer);
                } catch (IOException e) {
                    try {
                        System.out.println(sc.getRemoteAddress() + ":下线了");
                        //取消注册
                        key.cancel();
                        //关闭通道
                        channel.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
