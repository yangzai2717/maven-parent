package com.pyy.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/27 09:06
 * @Description:
 */
public class ConsumerTool implements MessageListener, ExceptionListener {

    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = "failover://tcp://192.168.46.201:51511";
    private String subject = "mytopic";
    private Destination destination = null;
    private Connection connection = null;
    private Session session = null;
    private MessageConsumer consumer = null;
    public static Boolean isConnection = false;

    //初始化
    private void initialize() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //创建一个会话
        destination = session.createTopic(subject);
        consumer = session.createConsumer(destination);
    }

    public void consumerMessage() throws JMSException {
        initialize();
        connection.start();
        consumer.setMessageListener(this);
        connection.setExceptionListener(this);
        isConnection = true;
        System.out.println("Consumer: -> Begin listening...");
    }

    //注册监听 如果出现异常 调用该函数
    @Override
    public void onException(JMSException e) {
        isConnection = false;
    }

    //监听，当有消息的时候，调用该函数，消息处理函数
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage txmsg = (TextMessage) message;
            try {
                String msg = txmsg.getText();
                System.out.println("Consumer:-> Received: " + msg);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Consumer: -> Received:" + message);
        }
    }
}
