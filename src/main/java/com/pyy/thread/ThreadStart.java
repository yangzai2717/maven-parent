package com.pyy.thread;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/12 14:30
 * @Description:
 */
public class ThreadStart {

    public static void main(String[] args){

        new ConcreteThread().start();
        new ConcreteThread("second").start();

        for (int i = 0; i <10 ; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }

    private static class ConcreteThread extends Thread{

        public ConcreteThread() {
        }

        public ConcreteThread(String name){
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName() + i);
            }
        }
    }
}
