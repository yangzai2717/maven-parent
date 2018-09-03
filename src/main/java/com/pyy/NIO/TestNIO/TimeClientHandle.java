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
 * @Date: 2018/8/29 14:40
 * @Description:
 */
public class TimeClientHandle implements Runnable{

    private String host;
    private int port;
    private  Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop = true;

    public TimeClientHandle(String ip ,int port) {
        this.host = ip == null ? "127.0.0.1" : ip;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        }catch (IOException e){
            e.printStackTrace();
        }
        while (stop){
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

    private  void doConnect() throws IOException {
        //如果直接连接成功， 则注册到多路复用器上，发送请求消息 请应答
        if(socketChannel.connect(new InetSocketAddress(host, port))){
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        }else{
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private  void handleInput(SelectionKey key) throws IOException {
        //查看key是否还可用，可能你刚拿过来，客户端关闭了
        if(key.isValid()){
            //处理连接是否可用
            SocketChannel sc = (SocketChannel) key.channel();
            if(key.isConnectable()){
                if(sc.finishConnect()){
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                }else
                    System.exit(1);
            }
            if(key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024); //分配一个缓存，和操作系统进行交互
                int readBytes = sc.read(readBuffer);  //从channel read 到buffer里面来
                if(readBytes > 0){
                    readBuffer.flip(); //返回一个完整的包
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Now is: " + body);

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

    private  void doWrite(SocketChannel sc) throws IOException {
        byte[] bytes = "query time order ".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        sc.write(writeBuffer);
        if(!writeBuffer.hasRemaining())
            System.out.println("send order 2 server succeed");
    }
}
