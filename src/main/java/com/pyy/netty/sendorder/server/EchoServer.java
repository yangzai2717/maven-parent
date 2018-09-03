package com.pyy.netty.sendorder.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/30 10:10
 * @Description:
 */
public class EchoServer {

    private final int prot;

    public EchoServer(int prot) {
        this.prot = prot;
    }

    public void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = null;
        try {
            //服务端引导类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //连接池处理数据
            eventLoopGroup = new NioEventLoopGroup();
            //装配Bootstrap
            serverBootstrap.group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class) //指定通道类型为NioServerSocketChannel
                    .localAddress("localhost", prot) //设置InetSocketAddress让服务器监听某个端口已等待客户端链接
                    .childHandler(new ChannelInitializer<Channel>() { //设置childHandler 执行所有的链接请求
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            //注册两个Inboundlhander，执行顺序为注册顺序，所有
                            //注册两个Outboundlhander，执行顺序为注册顺序的逆序，所有
                            channel.pipeline().addLast(new EchoInHandler1()); //注册handler
                            channel.pipeline().addLast(new EchoOutHandler1()); //注册handler
                            channel.pipeline().addLast(new EchoOutHandler2()); //注册handler
                            channel.pipeline().addLast(new EchoInHandler2()); //注册handler
                        }
                    });
            //最后绑定服务器等待直到绑定完成，调用sync 方法会阻塞直到服务器完成绑定，然后服务器等待通道关闭
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("开始监听，端口为：" + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoServer(20000).start();
    }
}
