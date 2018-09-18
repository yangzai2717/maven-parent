package com.pyy.activemq.subpub;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/9/17 10:43
 * @Description:
 */
public class Listener2 implements MessageListener{
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("订阅者二收到的消息："  + ((TextMessage)message).getText());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
