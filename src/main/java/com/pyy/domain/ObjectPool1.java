package com.pyy.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/6 17:32
 * @Description:
 */
public class ObjectPool1 {

    private Map<String, Object> pool;

    private static ObjectPool1 OBJECTPOOL1;

    private ObjectPool1(Map<String, Object> pool){
        this.pool = pool;
    }

    private static JSONArray getObjects(String config) throws IOException {
        Reader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(config));
        return JSONObject.parseObject(CharStreams.toString(reader)).getJSONArray("objects");
    }

    private static Object getInstance(String className, JSONArray fields) throws ClassNotFoundException,IllegalAccessException,NoSuchMethodException,
            InstantiationException,InvocationTargetException{
        //配置的class
        Class<?> clazz = Class.forName(className);
        //目标class的实例对象
        Object targetObject = clazz.newInstance();
        if(fields != null && fields.size() != 0){
            for (int i = 0; i < fields.size(); i++) {
                JSONObject field = fields.getJSONObject(i);
                //需要设置的成员变量名
                String fieldName = field.getString("name");

                //需要设置的成员变量的值
                Object fieldValue;
                if(field.containsKey("value")){
                    fieldValue = field.get("value");
                }else if(field.containsKey("ref")){
                    String refBeanId = field.getString("ref");
                    fieldValue = OBJECTPOOL1.getObject(refBeanId);
                }else {
                    throw new RuntimeException("neither value nor ref");
                }

                String setterName = "set" +
                        fieldName.substring(0,1).toUpperCase() +
                        fieldName.substring(1);
                // 需要设置的成员变量的setter方法
                Method setterMethod = clazz.getMethod(setterName,fieldValue.getClass());
                //调用setter方法将值设置进去
                setterMethod.invoke(targetObject,fieldValue);
            }
        }
        return targetObject;
    }

    // 创建一个对象池的实例(保证是多线程安全的)
    private static void initSingletonPool(){
        if(OBJECTPOOL1 == null){
            synchronized (ObjectPool1.class){
                if(OBJECTPOOL1 == null){
                    OBJECTPOOL1 = new ObjectPool1(new ConcurrentHashMap<String, Object>());
                }
            }
        }
    }

    // 根据指定的JSON配置文件来初始化对象池
    public static ObjectPool1 init(String config){
        // 初始化pool
        initSingletonPool();

        try {
            JSONArray objects = getObjects(config);
            for (int i = 0; objects != null && i < objects.size(); i++) {
                JSONObject object = objects.getJSONObject(i);
                if(object == null || object.size() == 0){
                    continue;
                }
                String id = object.getString("id");
                String className = object.getString("class");

                // 初始化bean并放入池中
                OBJECTPOOL1.putObject(id,getInstance(className,object.getJSONArray("fields")));
            }
            return OBJECTPOOL1;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Object getObject(String id){
       return pool.get(id);
    }

    public void putObject(String id, Object object){
        pool.put(id, object);
    }

    public void clear(){
        pool.clear();
    }
}
