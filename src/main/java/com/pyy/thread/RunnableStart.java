package com.pyy.thread;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/12 14:59
 * @Description:
 */
public class RunnableStart {

    public static void main(String[] args){
        Runnable runnable = new ConcreteRunnable();
        new Thread(runnable,"first").start();
        new Thread(runnable).start();
    }



    private static class ConcreteRunnable implements Runnable{

        private int i = 0;

        @Override
        public void run() {
            for (; i < 10; ++i) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
