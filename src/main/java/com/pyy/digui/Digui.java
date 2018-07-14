package com.pyy.digui;

import java.util.Arrays;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/7/12 14:20
 * @Description:
 */
public class Digui {

    public static int  f(int n){
        if(n ==1 || n==2){
            return 1;
        }else {
            return f(n-1) + f(n -2);
        }
    }
    public static void main(String[] args){
       /* System.out.println(f(6));
        System.out.println(j(5));
        String a = "11,12,13,14";
        String[] s = a.split(",");
        System.out.println(Arrays.deepToString(s));*/
       StringBuffer b = new StringBuffer();
       String s = "s";
       b.append(s);
    }

    public static int j(int n){
        if(n==2){
            return 2;
        }else{
            return n * j(n-1);
        }
    }
}
