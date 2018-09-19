package com.fendo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/18 17:04
 * @Description:
 */
@Service
public class ConsumerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public TextMessage receive(Destination destination){
        TextMessage textMessage = (TextMessage) jmsTemplate.receive(destination);
        try {
            System.out.println("从队列" + destination.toString() + "收到了消息：" + textMessage.getText());
        } catch (Exception e){
            e.printStackTrace();
        }
        return textMessage;
    }

}
