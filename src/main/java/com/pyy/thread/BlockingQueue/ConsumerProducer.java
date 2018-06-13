package com.pyy.thread.BlockingQueue;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/13 16:16
 * @Description:
 */
public class ConsumerProducer {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 20;
    private static final int KEEP_ALIVE_TIME = 20;

    public static void main(String[] args){
        BlockingQueue<String> queue = getQueue();
        Producer producer = new Producer(queue, 10);
        Consumer consumer = new Consumer(queue);

        ExecutorService pool = getThreadPool();
        pool.submit(producer);
        pool.submit(consumer);
        pool.submit(consumer);
    }

    private static BlockingQueue<String> getQueue() {
        return new ArrayBlockingQueue<>(10);
    }

    private static ExecutorService getThreadPool() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    }

    public static class Producer implements Runnable{


        private BlockingQueue<String> queue;
        private int count;

        public Producer(BlockingQueue<String> queue, int count) {
            this.queue = queue;
            this.count = count;
        }

        @Override
        public void run() {
            Random random = new Random();
            int i = 1;
            while (count-- != 0){
                try {
                    Thread.sleep(100);
                    queue.put("product" + i++);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer  implements Runnable{


        private BlockingQueue<String> queue;

        public Consumer (BlockingQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    String product = queue.take();
                    System.out.println(Thread.currentThread().getName() + " " + product);
                    Thread.sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
