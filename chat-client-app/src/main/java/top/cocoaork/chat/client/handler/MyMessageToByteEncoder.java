package top.cocoaork.chat.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.cocoaork.chat.client.protocol.LengthTransfer;

public class MyMessageToByteEncoder extends MessageToByteEncoder<LengthTransfer> {

    @Override
    protected void encode(ChannelHandlerContext ctx, LengthTransfer msg, ByteBuf out) throws Exception {

        //发送数据包长度
        out.writeInt(msg.getLength());
        //发送数据包
        out.writeBytes(msg.getBytes());


    }


}
