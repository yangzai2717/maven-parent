package com.pyy.thread;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/12 17:39
 * @Description: 银行账户
 */
public class Account {

    private double balance;

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void reduceBalance(double count) {
        this.balance -= count;
    }

    public synchronized boolean draw(double money){
        if (getBalance() >= money) {
            System.out.println(Thread.currentThread().getName() + "取钱" + money + "成功");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            reduceBalance(money);
            System.out.println("\t" + Thread.currentThread().getName() + "成功后的余额: " + getBalance());
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + "取钱失败");
            System.out.println("\t" + Thread.currentThread().getName() + "失败后的余额: " +getBalance());
            return false;
        }
    }
}
