package com.study.netty.task;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
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
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列得到连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道测试对象（匿名对象）
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //给我们的workerGroup的EventLoop对应的管道设置处理器
                            ch.pipeline().addLast(new UserScheduleTaskHandler());
                        }
                    });
            System.out.println("..服务器 is ready");
            //绑定一个端口并且同步，生成了一个ChannelFuture对象
            //启动服务器
            ChannelFuture sync = serverBootstrap.bind(6668).sync();
            sync.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("监听端口成功");
                    }
                }
            });
            //对关闭通道进行监听 当有关闭通道消息事件时才会关闭
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
