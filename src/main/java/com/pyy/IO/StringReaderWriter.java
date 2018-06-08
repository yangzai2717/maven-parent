package com.pyy.IO;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/8 15:39
 * @Description:
 */
public class StringReaderWriter {

    @Test
    public void testStringReaderWriter() throws  Exception{
        String string = "锦瑟无端五十弦，一弦一柱思华年。" +
                "庄生晓梦迷蝴蝶，望帝春心托杜鹃。" +
                "沧海月明珠有泪，蓝田日暖玉生烟。" +
                "此情可待成追忆？只是当时已惘然。";

        StringReader reader = new StringReader(string);
        char[] buffer = new char[32];
        int count;
        while ((count = reader.read(buffer)) != -1){
            System.out.println(new String(buffer, 0,count));
        }

        System.out.println("************");
        StringWriter writer = new StringWriter();
        writer.write("昨夜星辰昨夜风，");
        writer.write("画楼西畔桂堂东。");
        writer.write("身无彩凤双飞翼，");
        writer.write("心有灵犀一点通。");
        System.out.println(writer.toString());
    }
}
