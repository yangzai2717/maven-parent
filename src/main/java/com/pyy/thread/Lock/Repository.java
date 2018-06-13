package com.pyy.thread.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/13 10:26
 * @Description:
 */
public class Repository {

    /*使用mutex保护count*/
    private final Lock mutex = new ReentrantLock();
    private int count;
    private int limit;

    public Repository(int count, int limit) {
        this.count = count;
        this.limit = limit;
    }

    private boolean canAdd(int count) {
        return this.count + count <= limit;
    }

    private boolean canReduce(int count) {
        return this.count - count >= 0;
    }

    public synchronized boolean add(int count) {
        try {
            // + 加锁
            //mutex.lock();
            if(canAdd(count)){
                Thread.sleep(80);
                this.count += count;
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            //mutex.unlock();
        }
        return  false;
    }

    public synchronized boolean reduce(int count){
        try {
            // - 加锁
            //mutex.lock();
            if(canReduce(count)){
                Thread.sleep(80);
                this.count -= count;
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
           // mutex.unlock();
        }
        return  false;
    }

    public int getCount(){
        return count;
    }
}
