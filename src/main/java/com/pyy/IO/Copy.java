package com.pyy.IO;

import java.io.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/8 14:47
 * @Description:
 */
public class Copy {
    private static final int BUFFER_LENGTH = 1024;

    public static void main(String[] args) throws IOException{
        if(args == null || args.length != 2){
            throw new RuntimeException("Use like: copy <src-file> <dest-file>");
        }

        Reader reader = new FileReader(args[0]);
        Writer writer = new FileWriter(args[1]);
        char[] buffer = new char[BUFFER_LENGTH];
        int count;
        while ((count = reader.read(buffer)) != -1){
            writer.write(buffer,0,count);
        }
        reader.close();
        writer.close();
    }
}
