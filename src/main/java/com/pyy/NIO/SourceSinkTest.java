package com.pyy.NIO;

import com.google.common.io.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/12 11:04
 * @Description:
 */
public class SourceSinkTest {

    @Test
    public void fileSinkSource() throws IOException {
        File file = new File("save.txt");
        CharSink sink = Files.asCharSink(file, StandardCharsets.UTF_8);
        sink.write("- 你好吗?\n- 我很好.");

        CharSource source = Files.asCharSource(file, StandardCharsets.UTF_8);
        System.out.println(source.read());
    }

    @Test
    public void netSource() throws IOException {
        CharSource source = Resources.asCharSource(new URL("http://www.sun.com"), StandardCharsets.UTF_8);
        System.out.println(source.readLines());
    }

    @Test
    public void saveHtmlFileChar() throws IOException{
        CharSource source = Resources.asCharSource(new URL("http://www.baidu.com"), StandardCharsets.UTF_8);
        source.copyTo(Files.asCharSink(new File("save3.html"),StandardCharsets.UTF_8));
    }

    @Test
    public void saveHtmlFileByte() throws IOException {
        ByteSource source = Resources.asByteSource(new URL("http://www.baidu.com"));
        source.copyTo(Files.asByteSink(new File("save4.html")));
    }
}
