package com.pyy.suanfa;

import java.util.Arrays;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/7/14 18:16
 * @Description: 两个有序数组合并成一个有序数组
 */
public class DoubleSort {

    public static int[] sort(int[] one, int[] two){
        int onesize = one.length;
        int twosize = two.length;
        int threesize = onesize + twosize;
        int[] three = new int[threesize];
        int i = 0;
        int j = 0;
        for(int t = 0; t < threesize; t++){
            if(i >= onesize){
                three[t] = two[j++];
            }else if(j >= twosize){
                three[t] = one[i++];
            }else{
                if(one[i] <= two[j]){
                    three[t] = one[i++];
                }else {
                    three[t] = two[j++];
                }
            }
        }
        return three;
    }

    public static void main(String[] args){
        int[] o = {1,3,5,7,9,11,12};
        int[] t = {2,4,6,8,10};
        int[] th = sort(o,t);
        System.out.println(Arrays.toString(th));
    }
}
