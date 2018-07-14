package com.pyy.singleton;

/**
 * 双重校验模式  解决效率问题
 */
public class Singleton4 {

    private static Singleton4 singleton4 = null;
    private Singleton4(){}

    public static Singleton4 Singleton4(){
        if(singleton4 == null){
            synchronized (Singleton4.class){
                if(singleton4 == null){
                    singleton4 = new Singleton4();
                }
            }
        }
        return singleton4;
    }
}
