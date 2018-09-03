package com.pyy.annotation.test;

import com.pyy.annotation.annotation.RpcService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/31 10:32
 * @Description:
 */
//@Component
public class MyServer implements ApplicationContextAware{

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        Map<String, Object> serviceBeanMap = ctx
                .getBeansWithAnnotation(RpcService.class);
        for (Object serviceBean : serviceBeanMap.values()){
            try {
                //获取自定义注解上的value
                String value = serviceBean.getClass().getAnnotation(RpcService.class).value();
                System.out.println("注解上的value : " + value);

                //反射被注解的类，并调用指定方法
                Method method = serviceBean.getClass().getMethod("hello", new Class[] {String.class});
                Object invoke = method.invoke(serviceBean, "bbb");
                System.out.println(invoke);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
