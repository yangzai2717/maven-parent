package com.pyy.NIO;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.SortedMap;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/11 17:14
 * @Description:
 */
public class CharsetLearn {

    @Test
    public void testGetAllCharsets() {
        SortedMap<String, Charset> charsetMap = Charset.availableCharsets();
        int count = 0;
        for (Map.Entry<String, Charset> charset : charsetMap.entrySet()) {
            System.out.println(charset.getKey() + " aliases -> " + charset.getValue().aliases() + " chaset -> " + charset.getValue());
            count++;
        }
        System.out.println("一共有" + count);
    }
}
