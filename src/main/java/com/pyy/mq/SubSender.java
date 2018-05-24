package com.pyy.mq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class SubSender {

    public static void main(String[] args){
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        Destination destination;
        MessageProducer producer;
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://192.168.46.129:61616"
        );
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE,
                    Session.CLIENT_ACKNOWLEDGE);
            destination = session.createQueue("test-sub");
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            MqBean bean = new MqBean();
            bean.setAge(13);
            for(int i = 0 ;i < 10; i++){
                Thread.sleep(1000);
                bean.setName("小黄" + i);
                producer.send(session.createObjectMessage(bean));
            }
            producer.close();
            System.out.println("发送结束");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
