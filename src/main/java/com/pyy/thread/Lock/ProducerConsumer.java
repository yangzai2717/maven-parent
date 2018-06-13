package com.pyy.thread.Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/13 10:34
 * @Description:
 */
public class ProducerConsumer {

    public static void main(String[] args){
        ExecutorService pool = getExecutor();

        Repository repository = new Repository(0, 100);
        pool.submit(new Producer(repository, 30));
        pool.submit(new Producer(repository, 30));
        pool.submit(new Consumer(repository, 10));
        pool.submit(new Consumer(repository, 10));
    }



    private static ExecutorService getExecutor(){
        return new ThreadPoolExecutor(10, 20, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    private static class Producer implements Runnable{

        private Repository repository;

        private int produceCount;

        public Producer(Repository repository, int produceCount) {
            this.repository = repository;
            this.produceCount = produceCount;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(700);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(repository.add(produceCount)){
                    System.out.println("+ 生产者" + Thread.currentThread().getName() + " 生产<" + produceCount + ">个产品 -- 成功");
                }else{
                    System.out.println("+ 生产者" + Thread.currentThread().getName() + " 生产<" + produceCount + ">个产品 -- 失败");
                }

                System.out.println("当前仓库共有" + repository.getCount() + "个产品");
            }
        }
    }

    private static class Consumer implements Runnable{

        private Repository repository;
        private int consumerCount;

        public Consumer(Repository repository, int consumerCount){
            this.repository = repository;
            this.consumerCount = consumerCount;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(700);
                }catch (Exception e){
                    e.printStackTrace();
                }

                if(repository.reduce(consumerCount)){
                    System.out.println("+ 消费者" + Thread.currentThread().getName() + " 消费<" + consumerCount + ">个产品 -- 成功");
                }else {
                    System.out.println("+ 消费者" + Thread.currentThread().getName() + " 消费<" + consumerCount + ">个产品 -- 失败");
                }

                System.out.println("当前仓库共有" + repository.getCount() + "个产品");            }
        }
    }
}
