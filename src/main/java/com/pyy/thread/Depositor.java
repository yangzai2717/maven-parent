package com.pyy.thread;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/13 09:53
 * @Description:
 */
public class Depositor {

    public static void main(String[] args){
       Account1 account1 = new Account1();
       new Thread(new DrawMethod(account1,100),"-取钱者").start();
       new Thread(new DepositMethod(account1,50),"-存钱者").start();
    }

    private static class DrawMethod implements Runnable{

        private Account1 account1;
        private double amount;

        public DrawMethod(Account1 account1, double amount) {
            this.account1 = account1;
            this.amount = amount;
        }

        @Override
        public void run() {
            while (true){
                try {
                   account1.draw(amount);
                   Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static class DepositMethod implements Runnable{

        private Account1 account1;
        private double amount;

        public DepositMethod(Account1 account1, double amount) {
            this.account1 = account1;
            this.amount = amount;
        }

        @Override
        public void run() {
            while (true){
                try {
                    account1.deposit(amount);
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
