package com.pyy.netty.sendorder.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/30 09:26
 * @Description:
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

    //客户端连接服务器后被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接服务器，开始发送数据。。。。。。");
        byte[] req = "query time order".getBytes(); //消息
        ByteBuf firstMessage = Unpooled.buffer(req.length); //发送类
        firstMessage.writeBytes(req); //发送
        ctx.writeAndFlush(firstMessage);
    }

    //从服务器接收到数据后调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client 读取server数据");
        //服务端返回消息
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("服务端数据为：" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exception....");
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

    }
}
