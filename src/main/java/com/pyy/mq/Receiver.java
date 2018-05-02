package com.pyy.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {
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
                    Session.CLIENT_ACKNOWLEDGE);
            destination = session.createQueue("first-queue");
            consumer = session.createConsumer(destination);
            /*consumer.setMessageListener(new MessageListener() {
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
            });*/
            int a = 0;
            while (a < 3){
                //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
                TextMessage message = (TextMessage) consumer.receive(10000);
                if(null != message){
                    System.out.println("收到消息" + message.getText());
                    message.acknowledge();
                }else {
                    System.out.println("kong");
                    break;
                }
                a++;
            }
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
