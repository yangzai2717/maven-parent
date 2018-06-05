package com.pyy.domain;

import org.junit.Test;

import java.util.Properties;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/5 17:45
 * @Description:
 */
public class Client {

    @Test
    public void client(){
        ObjectPool pool = ObjectPool.init("config.json");
        User user = (User)pool.getObject("id1");
        System.out.println(user.toString());

        Bean bean = (Bean) pool.getObject("id2");
        System.out.println(bean.toString());
    }
}
