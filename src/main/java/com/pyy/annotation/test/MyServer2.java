package com.pyy.annotation.test;

import com.pyy.annotation.annotation.RpcService;
import com.pyy.annotation.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
/*import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;*/

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/31 10:32
 * @Description:
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")*/
@Component
public class MyServer2 {

    @Autowired
    HelloService helloService;

    @Test
    public void helloTest(){
        System.out.println("开始");
        String hello = helloService.hello("aaaaaaaaa");
        System.out.println(hello);
    }
}
