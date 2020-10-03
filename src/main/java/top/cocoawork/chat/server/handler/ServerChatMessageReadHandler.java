package top.cocoawork.chat.server.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.concurrent.EventExecutor;

import java.util.List;

public class ServerChatMessageReadHandler extends ChannelInboundHandlerAdapter {

    private ChannelGroup channels;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Json2ChatMessageHandler 接收到消息" + msg);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        if ( null == channels) {
            channels = new DefaultChannelGroup(ctx.executor());
        }
        channels.add(ctx.channel());
    }
}
