package com.pyy.activemq.subpub;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/13 16:17
 * @Description:
 */
public class JMSConsumer1 {

    private static final String username = ActiveMQConnection.DEFAULT_USER;
    private static final String psssword = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String url = "failover://tcp://192.168.46.201:51511";

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectionFactory connectionFactory;
                Connection connection = null;
                Session session;
                Destination destination;
                MessageConsumer messageConsumer;

                connectionFactory = new ActiveMQConnectionFactory(username, psssword, url);

                try {
                    connection = connectionFactory.createConnection();
                    connection.start();
                    session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
                    destination = session.createTopic("FirstTopic1");
                    messageConsumer = session.createConsumer(destination);
                    messageConsumer.setMessageListener(new Listener1());  //注册消息监听
                }catch (Exception e){
                    e.printStackTrace();
                }/*finally {
                    try {
                        connection.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        }).start();


    }
}
