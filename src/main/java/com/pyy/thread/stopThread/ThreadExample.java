package com.pyy.thread.stopThread;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/6 11:39
 * @Description:
 */
public class ThreadExample extends Thread {
    //标识线程是否结束
    public static boolean isDestroy = false;

    public static void main(String[] args) {
        ThreadExample t = new ThreadExample();
        t.start();
        try {
            //先让线程跑起来
            System.out.println(Thread.currentThread().getName() + "============");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //结束线程
        isDestroy = false;
    }


    @Override
    public void run() {
        int i = 0;
        while (!isDestroy) {
            i++;
        }
    }
}
