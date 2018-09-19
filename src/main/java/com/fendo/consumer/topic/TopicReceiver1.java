package com.fendo.consumer.topic;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/18 16:17
 * @Description:
 */
@Component
public class TopicReceiver1 implements MessageListener{
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("TopicReceiver1接收到消息:"+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
