package com.pyy.activemq.mqExmaple;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/13 16:17
 * @Description:
 */
public class JMSConsumer_Receive {

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
                    session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
                    destination = session.createQueue("helloworld");
                    messageConsumer = session.createConsumer(destination);
                    while (true){
                        TextMessage textMessage = (TextMessage) messageConsumer.receive(1000);//设置多少秒接收
                        if (textMessage != null){
                            System.out.println("收到的消息：" + textMessage.getText());
                        }else {
                            break;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        connection.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }


    }
}
