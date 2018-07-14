package com.pyy.test;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/7/5 11:45
 * @Description:
 */
public class test {

    public static void main(String[] args){
        /*for(int i=0;i<5;i++){
            for(int j=5;j>i;j--){
                System.out.println("*");
            }
            System.out.println();
        }*/
        //System.out.println(8+8+"88" + (8+8));
        short s1 = 1;
        s1 += 1; //short = short + short
        s1 = (short)(s1 + 1); //没有强转 short = short + int 会报错
        for(int i=0;i<5;i++){
            if(i>2){
                System.out.println(i);
            }
            System.out.println("=========");
        }
    }
}
