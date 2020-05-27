package com.study.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author Tiger
 * @date 2020-05-24
 * @see com.study.netty.simple
 * 说明
 * 1.我们定义一个handler需要继承netty 规定好的某个HandlerAdapter
 * 2.这时我们自定义一个Handler，才能称为一个handler
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 读取实际数据（这里我们可以读取客户端发送的消息）
     * 1.ChannelHandlerContext cts:上下文对象，含有与管道pipeline，通道channel，地址
     * 2.Object msg:就是客户端发送的数据 默认Object
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器读取线程："+Thread.currentThread().getName());
        System.out.println("server ctx=" + ctx);
        //将msg转成ByteBuf，这是netty提供的，也nio的ByteBuffer不同
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送的消息是：" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址：" + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完毕
     *
     * @param ctx 上下文对象
     * @throws Exception e
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //write方法+flush方法，将数据写入缓存并刷新，一般讲，我们对这个发送的数据进行编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端", CharsetUtil.UTF_8));
    }

    /**
     * 发生异常，关闭通道
     *
     * @param ctx   上下文
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
