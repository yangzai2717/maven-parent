package com.fendo.producer.topic;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/18 16:30
 * @Description:
 */
@Component("topicSender")
public class TopicSender {

    @Resource(name = "jmsTopicTemplate")
    private JmsTemplate jmsTemplate;

    public void send(String topicName, final String message){
        jmsTemplate.send(topicName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}
