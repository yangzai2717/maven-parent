package com.pyy.domain;

import org.junit.Test;
import org.omg.Dynamic.Parameter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/8 09:28
 * @Description:
 */
public class Client2 {

    private Map<String, Object> objectMap;

    public void test(Map<String, User> map, Integer string) {
    }

    public Map<User, Bean> test() {
        return null;
    }

    /**
     * 测试属性类型
     * @throws Exception
     */

    public void testFieldType() throws Exception{
        Field field = Client2.class.getDeclaredField("objectMap");
        Type gType = field.getGenericType();
        // 打印type与generic type的区别
        System.out.println(field.getType());
        System.out.println(gType);
        System.out.println("**************");
        if(gType instanceof ParameterizedType){
            ParameterizedType pType = (ParameterizedType) gType;
            Type[] types = pType.getActualTypeArguments();
            for (Type type : types){
                System.out.println(type.toString());
            }
        }
    }

    /**
     * 测试参数类型
     *
     * @throws NoSuchMethodException
     */

    public void testParamType() throws Exception{
        Method testMethod = Client2.class.getMethod("test",Map.class,Integer.class);
        Type[] parameterTypes  = testMethod.getGenericParameterTypes();
        for (Type type : parameterTypes ){
            System.out.println("type ->" + type);
            if(type instanceof ParameterizedType){
                Type[] actualTypes = ((ParameterizedType)type).getActualTypeArguments();
                for(Type actualType : actualTypes){
                    System.out.println("\r tactual type ->" + actualType);
                }
            }
        }
    }

    /**
     * 测试返回值类型
     *
     * @throws NoSuchMethodException
     */
    @Test
    public void testReturnType() throws Exception{
        Method testMethod = Client2.class.getMethod("test");
        Type returnType = testMethod.getGenericReturnType();
        System.out.println("return type -> " + returnType);

        if(returnType instanceof ParameterizedType){
            Type[] actualTypes = ((ParameterizedType)returnType).getActualTypeArguments();
            for(Type type : actualTypes){
                System.out.println("\r tactual type ->" + type);
            }
        }
    }
}
