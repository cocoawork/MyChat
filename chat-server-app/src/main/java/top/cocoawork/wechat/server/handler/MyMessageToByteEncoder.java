package top.cocoawork.wechat.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.cocoawork.wechat.server.protocol.DataPackageWrap;

public class MyMessageToByteEncoder extends MessageToByteEncoder<DataPackageWrap> {

    @Override
    protected void encode(ChannelHandlerContext ctx, DataPackageWrap msg, ByteBuf out) throws Exception {

        //发送数据包长度
        out.writeInt(msg.getLength());
        //发送数据包
        out.writeBytes(msg.getBytes());


    }


}
