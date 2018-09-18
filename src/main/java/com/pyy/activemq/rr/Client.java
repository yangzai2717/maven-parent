package com.pyy.activemq.rr;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Random;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/17 14:00
 * @Description:
 */
public class Client implements MessageListener{

    private static int ackMode;
    private static String clientWQueueName;
    private static final String url = "failover://tcp://192.168.46.201:51511";

    private boolean transacted = false;
    private MessageProducer producer;

    static {
        clientWQueueName = "client.message";
        ackMode = Session.AUTO_ACKNOWLEDGE;
    }

    public Client(){
        ActiveMQConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory(url);
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(transacted, ackMode);
            Destination adminQueue  = session.createQueue(clientWQueueName);

            //设置消息生成器将消息发送到服务器正在消耗的队列
            this.producer = session.createProducer(adminQueue );
            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //创建一个临时队列，该客户端将侦听响应
            //然后创建一个从该临时对列消耗消息的消费者。。。对于真正的应用程序
            //客户端应该为服务器的每个消息重新使用相同的临时队列。。。一个临时队列 每个客户端
            Destination tempDest = session.createTemporaryQueue();
            MessageConsumer responseConsumer = session.createConsumer(tempDest);

            //此类也将处理到临时队列的消息
            responseConsumer.setMessageListener(this);

            //现在创建你要发送的实际消息
            TextMessage txtMessage = session.createTextMessage();
            //设置消息
            txtMessage.setText("MyProtocolMessage");

            //将回复字段设置为上面创建的临时队列，这是服务器应答的队列
            txtMessage.setJMSReplyTo(tempDest);

            //设置相关ID，以便当您收到响应时，您知道响应式哪个发送消息
            //如果没有多个未完成的消息给服务器，那么相同的相关ID可以用于多有的消息
            //如果有多个未完成的消息，
            String correlationId = this.createRandomString();
            txtMessage.setJMSCorrelationID(correlationId);
            this.producer.send(txtMessage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String createRandomString(){
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }

    @Override
    public void onMessage(Message message) {
        String messageText = null;
        try {
            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                messageText = textMessage.getText();
                System.out.println("响应内容 = " + messageText);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
