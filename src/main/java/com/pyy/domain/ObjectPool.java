package com.pyy.domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class ObjectPool {
    private Map<String,Object> pool;

    public ObjectPool(Map<String,Object> pool){
        this.pool = pool;
    }

    private static Object getInstence(String className) throws ClassNotFoundException,IllegalAccessException,InstantiationException{
        return Class.forName(className).getInterfaces();
    }

    private static JSONArray getObjects(String config) throws IOException{
        Reader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(config));
        return JSONObject.parseObject(CharStreams.toString(reader)).getJSONArray("objects");
    }

    // 根据指定的JSON配置文件来初始化对象池
    public static ObjectPool init(String config){
        return null;
    }
}
