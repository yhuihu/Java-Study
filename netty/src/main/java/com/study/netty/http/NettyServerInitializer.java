package com.study.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author Tiger
 * @date 2020-06-03
 * @see com.study.netty.http
 **/
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //http加解码类
        pipeline.addLast(new HttpServerCodec());
        //请求处理
        pipeline.addLast(new NettyServerHandler());
    }
}
