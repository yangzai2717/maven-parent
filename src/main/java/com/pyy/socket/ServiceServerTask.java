package com.pyy.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/22 14:32
 * @Description:
 */
public class ServiceServerTask implements Runnable{

    private Socket socket;
    InputStream in = null;
    OutputStream out = null;

    public ServiceServerTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        //业务逻辑
        try {
            //从socket 连接中获取到与client之间的网络通信输入输出流
             in = socket.getInputStream();

             out = socket.getOutputStream();

            //将输入流包装一下
            BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(in));
            //从网络通信输入流中读取客户端发送过来的数据
            //注意：socketinputstream 的读数据的方法都是阻塞的
            String params = bufferedReader.readLine();

            GetDataServiceImpl getDataService = new GetDataServiceImpl();
            String result = getDataService.getData(params);

            //将调用结果写到socket的输出流中，以返回给客户端
            PrintWriter pw = new PrintWriter(out);
            pw.println(result);
            pw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
