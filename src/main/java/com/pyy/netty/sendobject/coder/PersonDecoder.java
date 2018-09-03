package com.pyy.netty.sendobject.coder;

import com.pyy.netty.sendobject.utils.ByteBufToBytes;
import com.pyy.netty.sendobject.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/30 14:23
 * @Description: 将byte[] 转换为 object  反序列化
 */
public class PersonDecoder extends ByteToMessageDecoder{


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        //工具类，将ByteBuf 转换为 byte[]
        ByteBufToBytes read = new ByteBufToBytes();
        byte[] bytes = read.read(byteBuf);
        //工具类，将byte[] 转换为object
        Object obj = ByteObjConverter.byteToObject(bytes);
        out.add(obj);
    }
}
