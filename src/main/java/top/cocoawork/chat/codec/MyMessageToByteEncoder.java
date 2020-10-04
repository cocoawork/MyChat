package top.cocoawork.chat.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.cocoawork.chat.message.TransferPackageWrap;

public class MyMessageToByteEncoder extends MessageToByteEncoder<TransferPackageWrap> {

    @Override
    protected void encode(ChannelHandlerContext ctx, TransferPackageWrap msg, ByteBuf out) throws Exception {

        //发送数据包长度
        out.writeInt(msg.getLength());
        //发送数据包
        out.writeBytes(msg.getBytes());
    }


}
