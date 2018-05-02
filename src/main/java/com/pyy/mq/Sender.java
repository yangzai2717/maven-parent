package com.pyy.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Sender {
    private static final int SENT_NUMBER= 5;

    public static void main(String[] args){
        //ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        //MessageProducer：消息发送者
        MessageProducer producer;
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://192.168.46.129:61616"
        );
        try {
            //构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            //启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("first-queue");
            //得到消息生成者【发送者】
            producer = session.createProducer(destination);
            // 设置不持久化，此处学习，实际根据项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //构造消息，此处写死，项目就是参数，或者方法获取
            sendMessage(session,producer);
            /*MqBean bean = new MqBean();
            bean.setAge(13);
            for(int i=0;i<100;i++) {
                bean.setName("小黄" + i);
                producer.send(session.createObjectMessage(bean));
            }
            producer.close();
            System.out.println("消费者结束");*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sendMessage(Session session,MessageProducer producer)throws Exception{
        for(int i=0 ;i <= SENT_NUMBER ; i++){
            TextMessage message = session.createTextMessage("ActiveMq 发送的消息" + i);
            // 发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
    }
}
