package com.pyy.thread.JUC;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/7/23 13:58
 * @Description:
 */
public class CountDownLatchDemo {

    private static CountDownLatch count = new CountDownLatch(3);
    private static ExecutorService service =  Executors.newFixedThreadPool(6);

    public static void main(String[] args) throws InterruptedException{

            for (int i = 0; i < count.getCount(); i++){
                final int  a  = i;
                service.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int tm = new Random().nextInt(5);
                            TimeUnit.SECONDS.sleep(tm);
                            System.out.println("第" + a + "个线程解析第" + a + "个sheel完成");
                            count.countDown();
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        count.await();
        System.out.println("全部执行完了，开始汇总");
    }
}
