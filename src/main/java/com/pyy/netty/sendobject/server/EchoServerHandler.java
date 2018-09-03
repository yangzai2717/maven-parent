package com.pyy.netty.sendobject.server;

import com.pyy.netty.sendobject.bean.Person;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/30 10:27
 * @Description:
 */
public class EchoServerHandler extends SimpleChannelInboundHandler{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server 读取数据");
        Person person = (Person) msg;
        System.out.println(person.getName());
        System.out.println(person.getSex());
        System.out.println(person.getAge());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server 读取数据完毕");
        ctx.flush();  //刷新后才将数据发出到socketcahnnel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client exception....");
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
