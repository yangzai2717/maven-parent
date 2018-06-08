package com.pyy.IO;

import com.pyy.domain.Bean;
import org.junit.Test;

import java.io.*;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/8 17:00
 * @Description:
 */
public class Serialization {

    @Test
    public void writerObject() throws Exception{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("save.txt"));
        Bean bean = new Bean(true, 3.14, "py");
        output.writeObject(bean);

        ObjectInputStream input = new ObjectInputStream(new FileInputStream("save.txt"));
        Bean bean1 = (Bean) input.readObject();
        System.out.println(bean1);
    }
}
