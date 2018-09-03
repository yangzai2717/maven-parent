package com.pyy;

import java.util.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/21 17:10
 * @Description:
 */
public class Test {

    public static void main(String[] args){

        System.out.println("主 开始");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程开始");
                while (true){

                }
            }
        });
        thread.setDaemon(true);
        thread.start();


        /*Set<String> s = new HashSet<>();
        s.add("b");
        s.add("d");
        s.add("1");
        s.add("2");
        s.add("");
        s.add("");
        s.add(null);
        s.add(null);
        System.out.println(s.toString());

       */ /*List<String> ss = new ArrayList<>();
        ss.add("1");
        ss.add("2");
        ss.add("4");
        ss.add("5");
        if(ss.contains("5")){
            System.out.println("包含");
        }*/
        ///System.out.println(ss.toString());
        /*int n = 0;
        for(int i=0;i<3;i++){
            n = n++;
            System.out.println(n + "//");
        }
        System.out.println(n);*/
        //System.out.println(a());
    }

    public static  boolean a(){
        for (int i = 0; i < 5; i++) {
            if(i == 2){
                return false;
            }else{
                return true;
            }
        }
        return true;
    }

    public static void testDeamon(){
       Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程开始");
                while (true){

                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
