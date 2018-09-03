package com.pyy.NIO.TestNIO;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/29 14:39
 * @Description:
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8081;
        if(args != null && args.length < 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (Exception e){
            }
        }
        new Thread(new TimeClientHandle("127.0.0.1", port), "timeclient-001").start();
    }
}
