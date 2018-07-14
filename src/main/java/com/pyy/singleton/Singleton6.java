package com.pyy.singleton;

/**
 * 利用静态内部类来实现单利
 */
public class Singleton6 {

     private static class SingletonHandle{
         public static Singleton6 singleton6 = new Singleton6();
     }

     private Singleton6(){}
     public static Singleton6 getInstences(){
         return SingletonHandle.singleton6;
     }
}
