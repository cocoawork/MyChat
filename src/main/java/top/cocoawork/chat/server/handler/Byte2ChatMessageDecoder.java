package top.cocoawork.chat.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.CharsetUtil;
import top.cocoawork.chat.message.ChatMessageTransfer;

import java.util.List;

public class Byte2ChatMessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in == null) return;
        if (in.readableBytes() <= 4) return;;
        in.markReaderIndex();
        int len = in.readInt();
        if (in.readableBytes() < len) {
            in.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[len];

        in.readBytes(bytes);

        String s = new String(bytes, CharsetUtil.UTF_8);

        in.markReaderIndex();

    }
}
