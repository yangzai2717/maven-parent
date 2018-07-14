package com.pyy;

import java.util.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/21 17:10
 * @Description:
 */
public class Test {

    public static void main(String[] args){
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

       */ List<String> ss = new ArrayList<>();
        ss.add("1");
        ss.add("2");
        ss.add("4");
        ss.add("5");
        if(ss.contains("5")){
            System.out.println("包含");
        }
        ///System.out.println(ss.toString());
        /*int n = 0;
        for(int i=0;i<3;i++){
            n = n++;
            System.out.println(n + "//");
        }
        System.out.println(n);*/
    }
}
