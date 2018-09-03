package com.pyy.NIO.TestNIO;

import java.io.InputStreamReader;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/29 10:33
 * @Description:
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8081;
        if(args != null && args.length < 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (Exception e){
            }
        }
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"NIO-server-001").start();
    }
}
