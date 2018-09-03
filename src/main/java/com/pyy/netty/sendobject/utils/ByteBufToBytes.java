package com.pyy.netty.sendobject.utils;

import io.netty.buffer.ByteBuf;

/**
 * @Auther: 庞洋洋
 * @Date: 2018/8/30 16:01
 * @Description:
 */
public class ByteBufToBytes {

    public byte[] read(ByteBuf byteBuf){
        byte[] req = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(req);
        return req;
    }
}
