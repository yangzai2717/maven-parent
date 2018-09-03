package com.pyy.suanfa;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/2 17:11
 * @Description:
 */
public class Huizushu {

    public static boolean isHuishu(int x){
        if(x < 0 || (x%10 == 0 && x != 0) ){
            return false;
        }
        int reverNum = 0;
        while (x > reverNum){
            reverNum =   x%10 + reverNum * 10;
            x /= 10;
        }
        return x == reverNum || x== reverNum/10;
    }

    public static void main(String[] args){
        System.out.println(isHuishu(121));
    }

}
