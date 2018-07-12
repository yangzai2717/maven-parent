package com.pyy.singleton;

/**
 * 给 懒汉式 增加同步机制
 * 解决了多线程同步问题，但是影响了效率
 */

public class Singleton3 {

    private static Singleton3 singleton2 = null;
    private Singleton3(){}

    public synchronized static Singleton3 getInstences(){
        if(singleton2 == null) {
            singleton2 = new Singleton3();
        }
        return singleton2;
    }
}
