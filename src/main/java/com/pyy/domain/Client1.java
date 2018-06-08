package com.pyy.domain;

import org.junit.Test;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/5 17:45
 * @Description:
 */
public class Client1 {

    @Test
    public void client() throws Exception{

        /*ObjectPool1 pool = ObjectPool1.init("config1.json");
        User user = (User) pool.getObject("id1");
        System.out.println(user.toString());

        Bean bean = (Bean) pool.getObject("id2");
        System.out.println(bean.toString());

        ComplexBean cBean = (ComplexBean) pool.getObject("id3");
        System.out.println(cBean.toString());*/

        User user = new User();
        Field idFiled = User.class.getDeclaredField("id");
        setAccessible(idFiled);
        idFiled.setInt(user, 46);

        Field nameFiled = User.class.getDeclaredField("name");
        setAccessible(nameFiled);
        nameFiled.set(user, "feiqing");

        Field passwordField = User.class.getDeclaredField("password");
        setAccessible(passwordField);
        passwordField.set(user, "ICy5YqxZB1uWSwcVLSNLcA==");

        System.out.println(user);
    }

    private void setAccessible(AccessibleObject object) {
        object.setAccessible(true);
    }
}
