package com.pyy.netty.sendobject.coder;

import com.pyy.netty.sendobject.bean.Person;
import com.pyy.netty.sendobject.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/30 14:29
 * @Description: 序列化  将object装换位 Byte[]
 */
public class PersonEncoder extends MessageToByteEncoder<Person>{

    @Override
    protected void encode(ChannelHandlerContext ctx, Person person, ByteBuf out) throws Exception {
        //工具类 将object 转换为byte[]
        byte[] datas = ByteObjConverter.objectToByte(person);
        out.writeBytes(datas);
        ctx.flush();
    }
}
