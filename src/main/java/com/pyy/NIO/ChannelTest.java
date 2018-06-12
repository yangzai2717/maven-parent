package com.pyy.NIO;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/11 16:17
 * @Description:
 */
public class ChannelTest {

    private CharsetDecoder decoder = Charset.forName("utf-8").newDecoder();


    @Test
    public void client() throws IOException{
        FileChannel inChannel = new FileInputStream("save.txt").getChannel();
        FileChannel outChannel = new FileOutputStream("attach.txt").getChannel();

        MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY,0, new File("save.txt").length());
        BufferTest.displayBufferInfo(buffer, "init buffer");

        //将Buffer内容一次写入另一文件的Channel
        outChannel.write(buffer);
        buffer.flip();
        // 解码CharBuffer之后输出
        System.out.println(decoder.decode(buffer));
    }

    @Test
    public void testDecodeEncode() throws IOException {
        File inFile = new File("save.txt");
        FileChannel in = new FileInputStream(inFile).getChannel();

        MappedByteBuffer byteBuffer = in.map(FileChannel.MapMode.READ_ONLY, 0, inFile.length());
        // Charset utf8 = Charset.forName("UTF-8");
        Charset utf8 = StandardCharsets.UTF_8;

        // 解码
        // CharBuffer charBuffer = utf8.decode(byteBuffer);
        CharBuffer charBuffer = utf8.newDecoder().decode(byteBuffer);
        System.out.println(charBuffer);

        // 编码
        // ByteBuffer encoded = utf8.encode(charBuffer);
        ByteBuffer encoded = utf8.newEncoder().encode(charBuffer);
        byte[] bytes = new byte[(int) inFile.length()];
        encoded.get(bytes);
        for (int i = 0; i < bytes.length; ++i) {
            System.out.print(bytes[i]);
        }
        System.out.println();

    }

}
