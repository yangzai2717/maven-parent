package com.pyy.netty.sendobject.client;


import com.pyy.netty.sendobject.coder.PersonEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/29 15:48
 * @Description:
 */
public class EchoClient {

    private final String host;
    private final int  port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        EventLoopGroup nioEventLoopGroup = null;
        try {
            //创建客户端的引导类
            Bootstrap bootstrap = new Bootstrap();
            //EventLoopGroup 可以理解为一个线程池，这个线程池用来处理链接、接受数据、发送数据
            nioEventLoopGroup = new NioEventLoopGroup();
            bootstrap.group(nioEventLoopGroup) //多线程处理
                    .channel(NioSocketChannel.class) //指定通道类型为NioserverSocketChannel ，一种通道的类型
                    .remoteAddress(new InetSocketAddress(host,port)) //地址
                    .handler(new ChannelInitializer<SocketChannel>() { //业务处理类
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new PersonEncoder()); //注册hander
                            socketChannel.pipeline().addLast(new EchoClientHandler()); //注册hander
                        }
                    });
            //连接服务器
            ChannelFuture channelFuture = bootstrap.connect().sync(); //一直等到连接成功
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e){

        }finally {
            nioEventLoopGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient("localhost", 20000).start();
    }
}
