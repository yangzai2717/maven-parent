package com.pyy.thread.stopThread;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/6 11:31
 * @Description:
 */
public class StopThread  implements Runnable{

    public static volatile boolean isStop  = true;

    public static void main(String[] args){
        StopThread st = new StopThread();
        new Thread(st,"ss").start();
        try {
            System.out.println(Thread.currentThread().getName() + "============");
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }
        isStop = false;
    }

    @Override
    public void run() {
        int i = 0;
        while (isStop){
            i++;
        }
    }
}
