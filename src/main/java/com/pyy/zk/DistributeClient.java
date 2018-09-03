package com.pyy.zk;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/20 16:38
 * @Description:
 */
public class DistributeClient {

    private static  final  String connectString = "192.168.46.201:2181,192.168.46.202:2181,192.168.46.203:2181";
    private static  final  int sessionTimeout = 2000;
    private static Charset charset = Charset.forName("utf-8");
    private static final String parentnode = "/servers";

    //注意：加volatile的意义
    private volatile List<String> serverList;

    private ZooKeeper zk = null;

    /**
     * 创建到zk的客户端连接
     * @throws Exception
     */
    public void getConnect() throws Exception{
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {  //参数是一个事件，
                //收到事件通知后的回调函数(应该是我们自己的事件处理逻辑)
                System.out.println(watchedEvent.getType() + "---" + watchedEvent.getPath());
                try {
                    //重新跟新服务器列表，并且注册了监听
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     *  获取服务器信息列表
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void getServerList() throws KeeperException, InterruptedException {

        //获取服务器子节点信息，并且对父节点进行监听
        List<String> children = zk.getChildren(parentnode, true);
        List<String> servers = new ArrayList<>();
        for (String child: children) {
            byte[] data = zk.getData(parentnode+"/"+child, false, null);
            servers.add(new String(data));
        }
        serverList = servers;

        //打印一下服务器列表
        System.out.println(serverList);
    }

    /**
     * 业务功能
     */
    public void handleBusiness() throws InterruptedException {
        System.out.println(" client start working....");
        Thread.sleep(Long.MAX_VALUE);//可以搞一个socket服务器 一直监听
    }

    public static void main(String[] args) throws Exception{
        //获取zk连接，并添加监听
        DistributeClient client = new DistributeClient();
        client.getConnect();
        //获取servers子节点信息，从中获取服务器信息列表
        client.getServerList();
        //业务线程启动
        client.handleBusiness();
    }


}
