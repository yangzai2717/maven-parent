package com.pyy.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SubReceiver {

    public static void main(String[] args){
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        //Session： 一个发送或接收消息的线程
        Session session;
        //Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        //消费者，消息接收者
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://192.168.46.129:61616"
        );
        try {
            //构造从工厂得到的连接对象
            connection = connectionFactory.createConnection();
            //启动
            connection.start();
            //获取操作连接
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("test-sub");
            //topic 为一对多 因为正常情况下我们的topic消息不会再服务器持久化，所以要先打开消费者，再打开生产者，
            //Topic topic = session.createTopic("topic-queue");
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        MqBean bean = (MqBean)((ObjectMessage)message).getObject();
                        System.out.println(bean);
                        if(null != message){
                            System.out.println("收到消息"+bean.getName());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(null != connection){
                    connection.close();
                }
            }catch (Throwable ignore){

            }
        }
    }
}
