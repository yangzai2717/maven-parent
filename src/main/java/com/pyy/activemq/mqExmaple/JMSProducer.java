package com.pyy.activemq.mqExmaple;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/13 14:52
 * @Description: P2P 点对点通信
 */
public class JMSProducer {

    private static final String username = ActiveMQConnection.DEFAULT_USER;
    private static final String psssword = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String url = "failover://tcp://192.168.46.201:51511";
    private static final int sendnum = 10; //发送的消息数量

    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectionFactory connectionFactory; //连接工厂
                Connection connection = null; //连接
                Session session; //会话，接受或者发送消息的线程
                Destination destination; //消息的目的地
                MessageProducer messageProducer; //消息生产者

                //实例化连接工厂
                connectionFactory = new ActiveMQConnectionFactory(username, psssword, url);

                try {
                    connection = connectionFactory.createConnection(); //通过连接工厂获取连接
                    connection.start(); //启动
                    session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);//创建session
                    destination = session.createQueue("helloworld"); //创建一个helloworld的消息队列
                    messageProducer = session.createProducer(destination); //创建消息生产者
                    sendMessage(session, messageProducer);
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    /*if (connection != null){
                        try {
                            connection.close();
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }*/
                }
            }
        }).start();

    }

    /**
     * 发送消息
     * @param session
     * @param messageProducer
     * @throws JMSException
     */
    public static void sendMessage(Session session, MessageProducer messageProducer) throws JMSException, InterruptedException {
        for (int i = 0; i < sendnum; i++) {
            TextMessage message = session.createTextMessage("ActiveMq发送的消息"+i);
            System.out.println("发送的消息：" + "ActiveMq发送的消息"+i);
            messageProducer.send(message);
            Thread.sleep(1000);
        }
    }
}
