package com.pyy.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/20 15:55
 * @Description:
 */
public class DistributeServer {

    private static  final  String connectString = "192.168.46.201:2181,192.168.46.202:2181,192.168.46.203:2181";
    private static  final  int sessionTimeout = 2000;
    private static Charset charset = Charset.forName("utf-8");
    private static final String parentnode = "/servers";

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
                    zk.getChildren("/", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 向zk集群注册服务信息
     * @param hostname
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void registerServer(String hostname) throws KeeperException, InterruptedException {
        if(this.isExist(parentnode)){
            String createParent =  zk.create(parentnode, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }else{
            String create =  zk.create(parentnode +"/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(hostname + "is online.." + create );
        }

    }

    /**
     * 判断 节点是否存在
     * @param parentnode
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public boolean isExist(String parentnode) throws KeeperException, InterruptedException {
       Stat stat =  zk.exists(parentnode, false);
        return stat == null ? true :false;
    }

    /**
     * 业务功能
     */
    public void handleBusiness(String hostname) throws InterruptedException {
        System.out.println(hostname + " start working....");
        Thread.sleep(Long.MAX_VALUE);//可以搞一个socket服务器 一直监听
    }

    public static void main(String[] args) throws Exception {
        //获取zk连接
        DistributeServer server = new DistributeServer();
        server.getConnect();
        //利用zk连接注册服务信息
        server.registerServer(args[0]);
        //启动业务功能
        server.handleBusiness(args[0]);
    }
}
