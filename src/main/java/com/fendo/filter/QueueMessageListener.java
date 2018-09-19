package com.fendo.filter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/19 16:50
 * @Description:
 */
public class QueueMessageListener implements MessageListener{
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("QueueMessageListener监听到了文本消息："
                    + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
