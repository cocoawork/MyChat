package top.cocoawork.chat.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.cocoawork.chat.server.protocol.Packet;

public class MyMessageToByteEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {

        //发送数据包长度
        out.writeInt(msg.getLength());
        //发送数据包
        out.writeBytes(msg.getBytes());


    }


}
