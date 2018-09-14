package com.pyy.activemq.mqExmaple;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/13 16:17
 * @Description:
 */
public class JMSConsumer_Listener {

    private static final String username = ActiveMQConnection.DEFAULT_USER;
    private static final String psssword = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String url = "failover://tcp://192.168.46.201:51511";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session;
        Destination destination;
        MessageConsumer messageConsumer;

        connectionFactory = new ActiveMQConnectionFactory(username, psssword, url);

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("hello");
            messageConsumer = session.createConsumer(destination);
            JMSConsumer_Listener jmsConsumer_listener = new JMSConsumer_Listener();
            messageConsumer.setMessageListener(jmsConsumer_listener.new Listener());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            /*try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }*/
        }
    }

    public class Listener implements MessageListener{

        @Override
        public void onMessage(Message message) {

            try {
                System.out.println("接收到的消息:"+((TextMessage)message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }

    }
}
