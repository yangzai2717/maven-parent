package com.pyy.activemq.rr;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

import javax.jms.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/17 15:15
 * @Description:
 */
public class Server implements MessageListener{

    private static int ackMode;
    private static String clientWQueueName;
    private static final String url = "failover://tcp://192.168.46.201:51511";

    private boolean transacted = false;
    private MessageProducer replyProducer ;
    private Session session;
    private MessageProtocol messageProtocol;

    static {
        clientWQueueName = "client.message";
        ackMode = Session.AUTO_ACKNOWLEDGE;
    }

    public Server() {
        try {
            //这个消息代理是嵌入的
            BrokerService broker = new BrokerService();
            broker.setPersistent(false);
            broker.setUseJmx(false);
            broker.addConnector(url);
            broker.start();
        } catch (Exception e){
            e.printStackTrace();
        }

        //将消息的处理委托给另一个类，在设置JMS之前实例化它，这样它就可以处理消息了
        this.messageProtocol  = new MessageProtocol();
        this.setupMessageQueueConsumer();
    }

    private void setupMessageQueueConsumer(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection;

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(this.transacted, ackMode);
            Destination adminQueue = session.createQueue(clientWQueueName);

            //设置一个消息生成器响应来自客户端的消息，我们将从一个消息发送到从jmsreplytoheader字段发送到的目的地
            this.replyProducer = this.session.createProducer(null);
            this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            //设置消费者从管理队列中消费消息
            MessageConsumer consumer = this.session.createConsumer(adminQueue);
            consumer.setMessageListener(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage response = this.session.createTextMessage();
            if(message instanceof TextMessage){
                TextMessage txtMsg = (TextMessage) message;
                String messageText = txtMsg.getText();
                response.setText(this.messageProtocol.handleProtocolMessage(messageText));
            }

            //从接收到的消息中设置相关ID为响应消息的相关ID
            //这可以让客户端识别该消息的响应
            //向服务器发送的一个未完成的消息
            response.setJMSCorrelationID(message.getJMSCorrelationID());

            //将响应发送到接收消息的JMSReplyTo字段指定的目的地，
            //这大概是客户创建的临时队列
            this.replyProducer.send(message.getJMSReplyTo(), response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
