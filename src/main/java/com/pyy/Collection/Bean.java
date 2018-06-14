package com.pyy.Collection;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/14 11:52
 * @Description:
 */
public class Bean implements Comparable<Bean>{
    private boolean isUsed;

    private double rate;

    private String name;

    public Bean(boolean isUsed, double rate, String name) {
        this.isUsed = isUsed;
        this.rate = rate;
        this.name = name;
    }

    @Override
    public int compareTo(Bean anotherBean) {
        double another = (anotherBean.isUsed ? 1 : 0) +
                anotherBean.rate + anotherBean.name.length();
        double self = (isUsed ? 1 : 0) + rate + name.length();
        return (int) (self - another);
    }

    @Override
    public String toString() {
        return "Bean{" +
                "isUsed=" + isUsed +
                ", rate=" + rate +
                ", name='" + name + '\'' +
                '}';
    }
}
