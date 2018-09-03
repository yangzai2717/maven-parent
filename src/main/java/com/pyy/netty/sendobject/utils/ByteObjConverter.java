package com.pyy.netty.sendobject.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/30 14:33
 * @Description:
 */
public class ByteObjConverter {

    public static byte[] objectToByte(Object obj){
        byte[] bytes = null;
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bo.close();
                oo.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public static Object byteToObject(byte[] bytes){
        Object obj = null;
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = null;
        try {
            oi = new ObjectInputStream(bi);
            obj = oi.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bi.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                oi.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return obj;
    }
}
