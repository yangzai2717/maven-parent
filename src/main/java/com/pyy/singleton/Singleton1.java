package com.pyy.singleton;

/**
 * 饿汉 实现单例模式
 * 类加载的时候对实例进行创建
 * 缺点是：即使该类不用被用到也会创建
 */
public class Singleton1 {

    private static Singleton1 singleton1 = new Singleton1();
    private Singleton1(){};

    public static Singleton1 getInstence(){
        return singleton1;
    }
}
