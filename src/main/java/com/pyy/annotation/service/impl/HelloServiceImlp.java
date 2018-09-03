package com.pyy.annotation.service.impl;

import com.pyy.annotation.annotation.RpcService;
import com.pyy.annotation.service.HelloService;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/31 10:31
 * @Description:
 */
@RpcService("helloservice")
public class HelloServiceImlp implements HelloService{
    @Override
    public String hello(String name) {
        return "hello " + name;
    }

    @Override
    public void test() {
        System.out.println("test");
    }
}
