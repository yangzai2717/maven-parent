package com.pyy.NIO;

import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/11 15:55
 * @Description: buffer
 */
public class BufferTest {


    public void client(){
        ByteBuffer buffer  = ByteBuffer.allocate(64);
        displayBufferInfo(buffer ,"init");

        buffer.put((byte)'a');
        buffer.put((byte)'b');
        buffer.put((byte)'c');
        displayBufferInfo(buffer, "after put");

        buffer.flip();
        displayBufferInfo(buffer, "after flip");
        System.out.println((char) buffer.get());
        displayBufferInfo(buffer, "after get");

        buffer.clear();
        displayBufferInfo(buffer, "after clear");
        System.out.println((char) buffer.get(2));
    }

    public static void displayBufferInfo(Buffer buffer, String msg){
        System.out.println("---------" + msg + "-----------");
        System.out.println("position" + buffer.position());
        System.out.println("limit" + buffer.limit());
        System.out.println("capacity" + buffer.capacity());
    }
}
