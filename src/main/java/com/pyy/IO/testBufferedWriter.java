package com.pyy.IO;

import org.junit.Test;

import java.io.*;
import java.nio.Buffer;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/8 15:39
 * @Description:
 */
public class testBufferedWriter {

    @Test
    public void testStringReaderWriter() throws  Exception{
        /*BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"));
            writer.write("昨夜星辰昨夜风，");
            writer.write("画楼西畔桂堂东。");
            writer.write("身无彩凤双飞翼，");
            writer.write("心有灵犀一点通。");
            writer.write("********");
            writer.flush();*/

        BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
        int count;
        char[] buffer = new char[20];
        while ((count = reader.read(buffer)) != -1){
            System.out.println(new String(buffer));
        }

    }
}
