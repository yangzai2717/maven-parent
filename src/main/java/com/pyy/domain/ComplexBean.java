package com.pyy.domain;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/6 17:29
 * @Description:
 */
public class ComplexBean {

    private String name;

    private Bean refBean;

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
                "name='" + name + '\'' +
                ", refBean=" + refBean +
                '}';
    }
}
