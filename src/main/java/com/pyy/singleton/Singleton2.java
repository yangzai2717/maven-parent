package com.pyy.singleton;

/**
 * 懒汉 实现单例模式
 * 实现了用的时候仔实例化该单例避免资源浪费，但是无法解决多线程的同步问题
 */
public class Singleton2 {

    private static Singleton2 singleton2 = null;
    private Singleton2(){}

    public static Singleton2 getInstences(){
        if(singleton2 == null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
}
