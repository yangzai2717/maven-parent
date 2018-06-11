package com.pyy.IO;

import com.pyy.domain.Bean;
import com.pyy.domain.ComplexBean;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/6/8 17:00
 * @Description:
 */
public class Serialization {


    public void writerObject() throws Exception{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("save.txt"));
        Bean bean = new Bean(true, 3.14, "py");
        output.writeObject(bean);

        ObjectInputStream input = new ObjectInputStream(new FileInputStream("save.txt"));
        Bean bean1 = (Bean) input.readObject();
        System.out.println(bean1);
    }

    @Test
    public void testSerialization() throws Exception{
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("save.txt"));
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("save.txt"));
        Bean bean = new Bean(true, 3.14, "fq");
        output.writeObject(bean);
        bean.setName("pyy");
        output.writeObject(bean);

        // 可以看到两个对象是完全一样的
        Bean readBean1 = (Bean) input.readObject();
        Bean readBean2 = (Bean) input.readObject();
        System.out.println(readBean1 == readBean2);
        System.out.println(readBean1);
        System.out.println(readBean2);
    }

    @Test
    public void writeObject() throws IOException, ClassNotFoundException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("save.txt"));
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("save.txt"));
            Bean bean = new Bean(true, 3.14, "bean");
            ComplexBean complexBean = new ComplexBean();
            complexBean.setName("complex_bean");
            complexBean.setA(6);
            complexBean.setRefBean(bean);
            //output.writeObject(bean);

            // 在这里对complexBean中的refBean成员做了修改
            complexBean.getRefBean().setName("simple_bean");
            output.writeObject(complexBean);

            ComplexBean complexBean1 = (ComplexBean) input.readObject();
            System.out.println(complexBean1);
    }

    @Test
    public void readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("save.txt"));

            // 可以发现complex_bean内的refBean属性并未改变
            ComplexBean complexBean = (ComplexBean) input.readObject();
            System.out.println("\n" + "complexBean: " + complexBean);
            System.out.println("bean : "  +complexBean.getRefBean());

    }

    @Before
    public void writeObject1() throws IOException {
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("save.txt"));
            output.writeObject(new Bean());

    }

    @Test
    @SuppressWarnings(value = "unchecked")
    public void readObject1() throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("save.txt"));
            List<String> list = (List<String>) input.readObject();
            for (String bean : list){
                System.out.println(bean);
            }
    }

}
