package com.fendo.consumer.queue;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/18 16:16
 * @Description:
 */
@Component
public class QueueReceiver1 implements MessageListener{


    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage)message;
        try {
            if("hello2".equals(tm.getText())){
                throw new RuntimeException("故意抛出异常");
            }
            tm.acknowledge();
            System.out.println("QueueReceiver1接收到消息:"+tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
