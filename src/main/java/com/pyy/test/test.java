package com.pyy.test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/7/5 11:45
 * @Description:
 */
public class test {

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        /*final  CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i=0;i<3;i++){
            final int a = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("i :" + a);
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("结束");
        /*for(int i=0;i<5;i++){
            for(int j=5;j>i;j--){
                System.out.println("*");
            }
            System.out.println();
        }*/
        //System.out.println(8+8+"88" + (8+8));
        /*short s1 = 1;
        s1 += 1; //short = short + short
        s1 = (short)(s1 + 1); //没有强转 short = short + int 会报错
        for(int i=0;i<5;i++){
            if(i>2){
                System.out.println(i);
            }
            System.out.println("=========");
        }*/
        int[] nums = {3,5,6,2,1};
        innserSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void innserSort(int[] nums){
        int size = nums.length;
        int j = 0;
        int temp = 0;
        for (int i = 0; i < size; i++) {
            temp = nums[i];
            for(j = i;j > 0 && temp < nums[j-1];j--){
                nums[j] = nums[j-1];
            }
            nums[j] = temp;
        }
    }


}
