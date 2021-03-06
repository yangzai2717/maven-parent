package com.pyy.suanfa;

import java.util.Stack;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/7/24 17:47
 * @Description:
 */
public class InvertInt {

    public static int reverse(int x) {
        if(x < 0){
            x = -x;
            return -get(x);
        }else{
            return get(x);
        }
    }

    public static int get(int x){
        int sum = 0;
        while (x > 0){
            int temp = x%10;
            if(sum > Integer.MAX_VALUE/10){
                return 0;
            }
            sum = sum * 10 + temp;
            x=x/10;
        }
        return sum;
    }

    public static void main(String[] args){
        System.out.println(reverse(-123450));
    }
}
