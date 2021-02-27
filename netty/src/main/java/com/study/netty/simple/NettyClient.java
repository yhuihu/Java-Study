package com.study.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.util.Scanner;

/**
 * @author Tiger
 * @date 2020-05-24
 * @see com.study.netty.simple
 **/
public class NettyClient {
    public static ChannelFuture sync = null;

    static {
        //客户端需要一个事件循环组
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        //创建客户端启动对象
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)//设置线程组
                    .channel(NioSocketChannel.class)//设置客户端通道的实现类（反射）
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            System.out.println("客户端 ok..");
            //启动客户端去连接服务端
            //关于channelFuture要分析，涉及到netty的异步模型
            try {
                sync = bootstrap.connect("127.0.0.1", 6668).sync();
                //给关闭通道进行监听
                sync.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } finally {
            eventExecutors.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                String next = scanner.next();
                System.out.println("输入：" + next);
                sync.channel().writeAndFlush(Unpooled.copiedBuffer(next, CharsetUtil.UTF_8));
            }
        }
    }
}
