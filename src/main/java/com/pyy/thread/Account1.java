package com.pyy.thread;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/13 09:16
 * @Description:
 */
public class Account1 {

    private double balance = 0.0;

    /*haveBalance标识当前账户是否还有余额*/
    private boolean haveBalance = false;

    public double getBalance() {
        return balance;
    }

    /**
     * 取钱
     * @param amount
     * @throws Exception
     */
    public synchronized  void draw(double amount) throws Exception{
        // 如果没有存款, 则释放锁定, 持续等待
        while (!haveBalance){
            wait();
        }

        System.out.println(Thread.currentThread().getName() + "  执行取钱操作");
        balance -= amount;
        System.out.println(", 当前余额" + balance);

        haveBalance = false;

        //唤醒其他线程
        notifyAll();
    }

    /**
     * 存钱
     * @param amount
     * @throws Exception
     */
    public synchronized void deposit(double amount) throws Exception{
        while (haveBalance){
            wait();
        }

        System.out.println(Thread.currentThread().getName() + "  执行存钱操作");
        balance += amount;
        System.out.println(", 当前余额" + balance);

        haveBalance = true;

        //唤醒其他线程
        notifyAll();
    }
}
