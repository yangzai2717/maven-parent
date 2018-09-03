package com.pyy.activemq;

import javax.jms.JMSException;
import java.util.Random;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/27 08:59
 * @Description:
 */
public class ProducerTest {

    public static void main(String[] args) throws InterruptedException, JMSException {
        ProducerTool producer = new ProducerTool();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            Thread.sleep(random.nextInt(5)*1000);

            producer.produceMessage("hello , world!--" + i);
            producer.close();
        }
    }
}
