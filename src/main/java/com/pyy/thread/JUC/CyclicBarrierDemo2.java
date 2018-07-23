package com.pyy.thread.JUC;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/7/23 15:35
 * @Description:
 */
public class CyclicBarrierDemo2 {

    public static void main(String[] args) throws Exception{
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        for(int i = 0; i < cyclicBarrier.getParties(); i++){
                new Writer(cyclicBarrier).start();
        }

        Thread.sleep(25000);

        System.out.println("重用");
        for(int i = 0; i < cyclicBarrier.getParties(); i++){
            new Writer(cyclicBarrier).start();
        }
    }

    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }
        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据");
            try {
                Thread.sleep(5000);
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
}
