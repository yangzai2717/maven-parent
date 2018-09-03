package com.pyy.NIO.TestNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/29 10:35
 * @Description:
 */
public class MultiplexerTimeServer implements Runnable{

    private  Selector selector;

    private ServerSocketChannel servChannel;

    private volatile boolean stop = true;

    /**
     * 初始化多路复用器， 绑定监听端口
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open(); //向内核注册一个监听
            servChannel = ServerSocketChannel.open();
            servChannel.configureBlocking(false); //配置为非阻塞
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            servChannel.register(selector, SelectionKey.OP_ACCEPT); //注册一个接收请求的事件
            System.out.println("The time server is start in port: " + port);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!stop){
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if (key.channel() != null)
                                key.channel().close();
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private  void handleInput(SelectionKey key) throws IOException {
        //查看key是否还可用，可能你刚拿过来，客户端关闭了
        if(key.isValid()){
            //处理新接入的请求消息
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();  //建立一个连接
                sc.configureBlocking(false);  //非阻塞
                sc.register(selector, SelectionKey.OP_READ);  //再注册一个事件 read
            }
            if(key.isReadable()){
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024); //分配一个缓存，和操作系统进行交互
                int readBytes = sc.read(readBuffer);  //从channel read 到buffer里面来
                if(readBytes > 0){
                    readBuffer.flip(); //返回一个完整的包
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order : " + body);
                    String currenttime = "query time order".equalsIgnoreCase(body)
                            ? new java.util.Date().toString()
                            : "bad order";
                    dowrite(sc, currenttime);  //channel 是双向的 能写能发
                } else if(readBytes < 0){
                    //对端链路关闭
                    key.cancel();;
                    sc.close();
                }else {
                    ;
                }
            }
        }
    }

    private  void dowrite(SocketChannel sc, String response) throws IOException {
        if(response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes(); //序列化
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length); //和内核交互
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
        }
    }
}
