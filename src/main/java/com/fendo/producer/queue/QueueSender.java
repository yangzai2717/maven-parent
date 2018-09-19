package com.fendo.producer.queue;

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
@Component("queueSender")
public class QueueSender {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void send(String queueName, final String message){
        jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}
