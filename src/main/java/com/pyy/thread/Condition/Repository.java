package com.pyy.thread.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/13 11:19
 * @Description:
 */
public class Repository {

    /*使用mutex保护condition*/
    private final Lock mutex = new ReentrantLock();
    private final Condition addCondition = mutex.newCondition();
    private final Condition reduceCondition = mutex.newCondition();

    private int count;
    private int limit;

    public Repository(int count, int limit){
        this.count = count;
        this.limit = limit;
    }

    private boolean canAdd(int count){
        return this.count + count <= limit;
    }

    private boolean canReduce(int count){
        return this.count - count >= 0;
    }

    public void add(int count){
        try {
            mutex.lock();

            while (!canAdd(count)){
                System.out.println("生产者 "+Thread.currentThread().getName()+"  is  waiting" );
                addCondition.await();
            }

            this.count += count;
            System.out.println( Thread.currentThread().getName()+"生产成功 ，当前产品数量："  + getCount());

            // 唤醒消费线程
            reduceCondition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mutex.unlock();
        }
    }

    public void reduce(int count){
        try {
            mutex.lock();

            while (!canReduce(count)){
                System.out.println("消费者 "+Thread.currentThread().getName()+"  is  waiting" );
                reduceCondition.await();
            }
            this.count -= count;
            System.out.println( Thread.currentThread().getName()+"消费成功 ，当前产品数量："  + getCount());

            addCondition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mutex.unlock();
        }
    }

    public int getCount(){
        return count;
    }
}
