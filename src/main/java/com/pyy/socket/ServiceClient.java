package com.pyy.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/22 16:07
 * @Description:
 */
public class ServiceClient {

    public static void main(String[] args) throws Exception{

        //向服务器发出请求建立连接
        Socket socket = new Socket("localhost", 8899);
        //从socket 中获取输入输出流
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        PrintWriter pw = new PrintWriter(out);
        pw.println("hello");
        pw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String result = br.readLine();
        System.out.println(result);

        in.close();
        out.close();
        socket.close();
    }
}
