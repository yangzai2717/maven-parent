package com.pyy.suanfa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/7/24 09:18
 * @Description:
 */
public class TwoNumSum {

    public static int[] twoSum(int[] nums, int target) {
        int size = nums.length;
        Map<Object,Object> map = new HashMap();
        for(int i=0;i<size;i++){
            int j = target - nums[i];
            if(map.containsKey(j)){
                return new int[] {(int)map.get(j),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    public static void main(String[] args){
        int[] nums = {2,7,9,11};
        int[] a = twoSum(nums,9);
        System.out.println(Arrays.toString(a));
    }

}
