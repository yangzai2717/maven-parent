package com.pyy.thread.Exception;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/13 17:28
 * @Description:
 */
public class Main {
    private static Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + e.getMessage());

        }
    };

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(handler);
        int a = 5 / 0;
        System.out.println("线程正常结束, a=" + a);
    }
}
