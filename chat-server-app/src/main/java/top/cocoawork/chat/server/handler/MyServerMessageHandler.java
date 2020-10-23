package top.cocoawork.chat.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.DefaultEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.cocoawork.chat.server.message.ChatSystemMessage;
import top.cocoawork.chat.server.protocol.Packet;

import java.net.SocketAddress;

public class MyServerMessageHandler extends SimpleChannelInboundHandler<Packet> {

    private int all_idle_times;

    private final Logger logger = LoggerFactory.getLogger(MyServerMessageHandler.class);

    private static ChannelGroup channels;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {

//        PacketWrap<Packet> packageWrap = new PacketWrap(msg);
        channels.writeAndFlush(msg);

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        if ( null == channels) {
            channels = new DefaultChannelGroup(new DefaultEventExecutor());
        }
        SocketAddress clientAddress = ctx.channel().remoteAddress();

        //提示群组中所有客户端 有新的成员上线了
        String tip =  "[" + clientAddress + "]上线了";
        System.out.println(tip);

//        ChatSystemMessage message = new ChatSystemMessage();
//
//        channels.writeAndFlush(packageWrap);
//        channels.add(ctx.channel());
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.ALL_IDLE) {
                all_idle_times++;
            }

//            //接收到超过三次，断开连接
//            if (all_idle_times >= 3) {
//                ctx.channel().close();
//            }
        }

    }
}
