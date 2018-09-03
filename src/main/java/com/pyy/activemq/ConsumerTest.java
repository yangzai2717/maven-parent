package com.pyy.activemq;

import javax.jms.JMSException;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/27 09:05
 * @Description:
 */
public class ConsumerTest implements Runnable{
    static Thread t1 = null;

    public static void main(String[] args) {
        t1 = new Thread(new ConsumerTest());
        t1.setDaemon(false);
        t1.start();
    }

    @Override
    public void run() {
        ConsumerTool consumerTool = new ConsumerTool();
        try {
            consumerTool.consumerMessage();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
