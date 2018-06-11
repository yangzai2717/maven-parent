package com.pyy.domain;

import java.io.Serializable;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/6 17:29
 * @Description:
 */
public class ComplexBean implements Serializable{

    private transient int a;

    private transient String name;

    private transient Bean refBean;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bean getRefBean() {
        return refBean;
    }

    public void setRefBean(Bean refBean) {
        this.refBean = refBean;
    }

    public String toString() {
        return "ComplexBean{" +
                "a=" + a + "\'" +
                ", name='" + name + '\'' +
                ", refBean=" + refBean +
                '}';
    }
}
