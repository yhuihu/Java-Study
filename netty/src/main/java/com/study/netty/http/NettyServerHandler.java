package com.study.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * @author Tiger
 * @date 2020-06-03
 * @see com.study.netty.http
 **/
public class NettyServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        Channel channel = ctx.channel();
        if (msg instanceof HttpRequest) {
            System.out.println("msg类型：" + msg.getClass());
            System.out.println("客户端地址：" + channel.remoteAddress());
            if (((HttpRequest) msg).uri().contains("icon")) {
                System.out.println("无效请求");
                return;
            }
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello，这是服务器给你返回的消息", CharsetUtil.UTF_16);
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
            channel.writeAndFlush(fullHttpResponse);
        }
    }
}
