package com.pyy.activemq;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/24 09:33
 * @Description:
 */
public class ProducerTool {

    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = "failover://tcp://192.168.46.201:51511";
    private String subject = "mytopic";
    private Destination destination = null;
    private Connection connection = null;
    private Session session = null;
    private MessageProducer producer = null;

    //初始化
    private void initialize() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //创建一个会话
        destination = session.createTopic(subject);
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    public void produceMessage(String message) throws JMSException {
        initialize();
        TextMessage msg = session.createTextMessage(message);
        connection.start();
        System.out.println("Producer:-> Sending message: " + message);
        producer.send(msg);
        System.out.println("Producer:-> message sent complete ");

    }

    public void close() throws JMSException {
        System.out.println("Producer:-> Closing connection");
        if (producer != null)
            producer.close();
        if (session != null)
            session.close();
        if (connection != null)
            connection.close();
    }
}
