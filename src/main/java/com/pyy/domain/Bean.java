package com.pyy.domain;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/5 17:46
 * @Description:
 */
public class Bean implements Serializable{
    private Boolean usefull;
    private Double rate;
    private String name;

    public Bean(Boolean usefull, Double rate, String name){
        this.usefull = usefull;
        this.rate = rate;
        this.name = name;
    }
    public Bean(){
    }

    private Object writeReplace() throws ObjectStreamException {
        List<String> list = new ArrayList<String>();
        list.add("bean1");
        list.add("bean2");
        return list;
    }

    public Boolean getUsefull() {
        return usefull;
    }

    public void setUsefull(Boolean usefull) {
        this.usefull = usefull;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "usefull=" + usefull +
                ", rate=" + rate +
                ", name='" + name + '\'' +
                '}';
    }
}
