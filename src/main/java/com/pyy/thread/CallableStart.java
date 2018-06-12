package com.pyy.thread;

import java.util.concurrent.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/12 15:17
 * @Description:
 */
public class CallableStart {

    public static void main(String[] args) throws Exception{
        RunnableFuture<Integer> task = new FutureTask<Integer>(new ConcreteCallable());
        new Thread(task).start();

        while (true){
            System.out.println("主线程在干其他事情...");
            if(task.isDone()){
                System.out.println("子线程返回值: " + task.get());
                break;
            }
            Thread.sleep(5);
        }

    }

    private static class ConcreteCallable implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            int total = 0;
            for (int i = 0; i <10 ; i++) {
                Thread.sleep(100);
                total += 1;
            }
            return total;
        }
    }
}
