package com.study.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Tiger
 * @date 2020-05-24
 * @see com.study.netty.simple
 **/
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建BossGroup和workerGroup
        //1.创建两个线程组bossGroup和workerGroup
        //2bossGroup只是处理连接请求，真正和客户端进行业务处理的，是workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //创建服务器端的启动对象，配置参数
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //使用链式编程来进行设置
            serverBootstrap.group(bossGroup, workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel作为服务器的通道实现
                    .childHandler(new NettyServerInitializer());
            System.out.println("..服务器 is ready");
            //绑定一个端口并且同步，生成了一个ChannelFuture对象
            //启动服务器
            ChannelFuture sync = serverBootstrap.bind(8080).sync();
            //对关闭通道进行监听 当有关闭通道消息事件时才会关闭
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
