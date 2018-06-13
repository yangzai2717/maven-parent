package com.pyy.thread.Condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/13 13:50
 * @Description:
 */
public class ProducerConsumer {


    public static void main(String[] args){
        ExecutorService pool = getExecutor();
        Repository repository = new Repository(0, 100);
        pool.submit(new Producer(repository,30));
        //pool.submit(new Producer(repository,30));
       // pool.submit(new Consumer(repository,10));
        pool.submit(new Consumer(repository,10));
    }

    private static ExecutorService getExecutor(){
        return new ThreadPoolExecutor(10, 20, 20, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    }

    private static class Producer implements Runnable{

        private Repository repository;
        private int produceCount;

        public Producer(Repository repository, int produceCount){
            this.repository = repository;
            this.produceCount = produceCount;
        }

        @Override
        public void run() {
            while (true){
                repository.add(produceCount);
            }
        }
    }

    private static class Consumer implements Runnable {

        private Repository repository;

        private int consumeCount;

        public Consumer(Repository repository, int consumeCount) {
            this.repository = repository;
            this.consumeCount = consumeCount;
        }

        @Override
        public void run() {
            while (true) {
                repository.reduce(consumeCount);
            }
        }
    }
}
