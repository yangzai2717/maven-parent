package com.pyy.thread.ThreadLocal;


/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/13 15:22
 * @Description:
 */
public class ThreadLocalClient {

    public static void main(String[] args){
        Account3 account3 = new Account3(888L);
        new Thread(new Writer(account3)).start();
        new Thread(new Reader(account3)).start();
    }

    private static class Writer implements Runnable{

        private Account3 account3;

        public Writer(Account3 account3){
            this.account3 = account3;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10 ; i++) {
                account3.setBalance((long)i);
                try {
                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "-balance: " + account3.getBalance());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Reader implements Runnable{

        private Account3 account3;

        public Reader(Account3 account3){
            this.account3 = account3;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10 ; i++) {
                try {
                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "-balance: " + account3.getBalance());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
