package com.pyy.singleton;

/**
 * 利用voletile ,解决java中的指令重排序
 */
public class Singleton5 {

    private static volatile Singleton5 singleton5 = null;
    private Singleton5(){}

    public static Singleton5 getInstences(){
        if(singleton5 == null){
            synchronized (Singleton5.class){
                if (singleton5 == null){
                    singleton5 = new Singleton5();
                }
            }
        }
        return singleton5;
    }
}
