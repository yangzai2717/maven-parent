package com.pyy.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/12 16:05
 * @Description:
 */
public class ThreadPool {


    public static void main(String[] args){
        ExecutorService pool = getThreadPool();
        pool.submit(new ConcreteRunnable());
        pool.submit(new ConcreteRunnable());
        pool.shutdown();
    }

    private static ExecutorService getThreadPool(){

        return Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });
    }

    private static class ConcreteRunnable implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 10; ++i) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
