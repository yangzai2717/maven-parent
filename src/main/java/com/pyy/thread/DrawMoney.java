package com.pyy.thread;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/12 17:41
 * @Description:
 */
public class DrawMoney {

    public static void main(String[] args){
        Runnable r = new DrawRunnable(new Account(800), 300);
        new Thread(r, "甲").start();
        new Thread(r, "乙").start();
    }

    private static class DrawRunnable implements Runnable{

        private final Account account;
        private double money;

        public DrawRunnable(Account account, double money) {
            this.account = account;
            this.money = money;
        }

        @Override
        public void run() {
            while (account.draw(money)){

            }
           /* while (true) {
                synchronized (account){
                if (account.getBalance() > money) {
                    System.out.println(Thread.currentThread().getName() + "取钱" + money + "成功");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    account.reduceBalance(money);
                    System.out.println("\t" + Thread.currentThread().getName() + "成功后的余额: " + account.getBalance());
                } else {
                    System.out.println(Thread.currentThread().getName() + "取钱失败");
                    System.out.println("\t" + Thread.currentThread().getName() + "失败后的余额: " + account.getBalance());
                    break;
                }
            }
        }*/
        }
    }

}
