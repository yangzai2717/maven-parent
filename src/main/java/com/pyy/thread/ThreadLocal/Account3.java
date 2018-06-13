package com.pyy.thread.ThreadLocal;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/13 15:20
 * @Description:
 */
public class Account3 {

    private ThreadLocal<Long> balance = new ThreadLocal<>();

    public Account3(Long balance) {
        this.balance.set(balance);
    }

    public Long getBalance() {
        return balance.get();
    }

    public void setBalance(Long balance) {
        this.balance.set(balance);
    }
}
