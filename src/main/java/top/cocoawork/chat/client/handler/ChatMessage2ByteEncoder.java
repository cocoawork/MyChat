package top.cocoawork.chat.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;
import top.cocoawork.chat.message.ChatMessageTransfer;
import top.cocoawork.chat.message.ChatTextMessage;

import java.net.SocketAddress;
import java.time.LocalDateTime;

public class ChatMessage2ByteEncoder extends MessageToByteEncoder<ChatMessageTransfer> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ChatMessageTransfer msg, ByteBuf out) throws Exception {

        int length = msg.getData().getBytes().length;
        out.writeInt(msg.getData().getBytes().length);
        out.writeBytes(msg.getData().getBytes());

        System.out.println("ChatMessage2ByteEncoder encode ... len=" + length);
    }


}
