package com.pyy.domain;

import org.junit.Test;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/5 17:45
 * @Description:
 */
public class Client1 {

    @Test
    public void client() throws Exception{

        ObjectPool1 pool = ObjectPool1.init("config1.json");
        User user = (User) pool.getObject("id1");
        System.out.println(user.toString());

        Bean bean = (Bean) pool.getObject("id2");
        System.out.println(bean.toString());

        ComplexBean cBean = (ComplexBean) pool.getObject("id3");
        System.out.println(cBean.toString());
    }
}
