package com.pyy.Collection.Iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/14 09:35
 * @Description:
 */
public class RemoveClient {

    Collection<Integer> collection = new ArrayList<>();

    @Before
    public void setUp(){
        Random random = new Random();
        for (int i = 0; i <10 ; i++) {
            collection.add(random.nextInt(i + 1));
        }
    }

    @Test
    public void client(){
        System.out.println("before:");
        for (Iterator<Integer> iterator = collection.iterator(); iterator.hasNext();){
            Integer integer = iterator.next();
            System.out.println(integer);
            if(integer == 0){
                iterator.remove();
            }
        }
        System.out.println("after:");
        for (Integer integer : collection){
            System.out.println(integer);
        }
    }
}
