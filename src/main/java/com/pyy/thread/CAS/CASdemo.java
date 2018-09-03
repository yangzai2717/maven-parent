package com.pyy.thread.CAS;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/7 10:43
 * @Description:
 */
public class CASdemo {

    public static void main(String[] args){
        System.out.println("START -- ");
        calc();
        calcSynchro();
        calcAtomic();
        testThreadSync();
        testThreadsAtomic();
        testThreadsSync2();
        testThreadsAtomic2();
        System.out.println("-- FINISHED ");
    }

    private static void calc() {
        StopWatch sw = new StopWatch();
        sw.start();

        long val = 0;
        while (val <  10000000L){
            val++;
        }
        sw.stop();
        long milSecds = sw.getElapsedTime();

        System.out.println(" calc() elapsed (ms): " + milSecds);
    }

    private static void calcSynchro() {
        StopWatch sw = new StopWatch();
        sw.start();

        long val = 0;

        while (val < 10000000L) {
            synchronized (CASdemo.class) {
                val++;
            }
        }

        sw.stop();
        long milSecds = sw.getElapsedTime();

        System.out.println(" calcSynchro() elapsed (ms): " + milSecds);
    }

    private static void calcAtomic() {
        StopWatch sw = new StopWatch();
        sw.start();

        AtomicLong val = new AtomicLong(0);
        while (val.incrementAndGet() < 10000000L) {

        }
        sw.stop();
        long milSecds = sw.getElapsedTime();

        System.out.println(" calcAtomic() elapsed (ms): " + milSecds);

    }

    private static void testThreadSync(){
        StopWatch sw = new StopWatch();
        sw.start();

        Thread t1 = new Thread(new LoopSync());
        t1.start();
        Thread t2 = new Thread(new LoopSync());
        t2.start();

        while (t1.isAlive() || t2.isAlive()){

        }

        sw.stop();
        long milSecds = sw.getElapsedTime();

        System.out.println(" testThreadsSync() 1 thread elapsed (ms): " + milSecds);
    }

    private static void testThreadsAtomic(){

        StopWatch sw = new StopWatch();
        sw.start();

        Thread t1 = new Thread(new LoopAtomic());
        t1.start();

        Thread t2 = new Thread(new LoopAtomic());
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {

        }

        sw.stop();
        long milSecds = sw.getElapsedTime();

        System.out.println(" testThreadsAtomic() 1 thread elapsed (ms): " + milSecds);

    }

    private static void testThreadsSync2(){

        StopWatch sw = new StopWatch();
        sw.start();

        Thread t1 = new Thread(new LoopSync());
        t1.start();

        Thread t2 = new Thread(new LoopSync());
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {

        }

        sw.stop();
        long milSecds = sw.getElapsedTime();

        System.out.println(" testThreadsSync() 2 threads elapsed (ms): " + milSecds);

    }

    private static void testThreadsAtomic2(){

        StopWatch sw = new StopWatch();
        sw.start();

        Thread t1 = new Thread(new LoopAtomic());
        t1.start();

        Thread t2 = new Thread(new LoopAtomic());
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {

        }

        sw.stop();
        long milSecds = sw.getElapsedTime();

        System.out.println(" testThreadsAtomic() 2 threads elapsed (ms): " + milSecds);

    }

    public static class LoopAtomic implements Runnable{

        @Override
        public void run() {
            AtomicLong val = new AtomicLong(0);
            while (val.incrementAndGet() < 10000000L){

            }
        }
    }

    private static class LoopSync implements Runnable {
        public void run() {
            long val = 0;

            while (val < 10000000L) {
                synchronized (CASdemo.class) {
                    val++;
                }
            }
        }
    }

}
