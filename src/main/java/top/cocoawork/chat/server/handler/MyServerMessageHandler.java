package top.cocoawork.chat.server.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.DefaultEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.cocoawork.chat.message.ChatSystemMessage;
import top.cocoawork.chat.message.ChatTextMessage;
import top.cocoawork.chat.message.TransferPackage;
import top.cocoawork.chat.message.TransferPackageWrap;

import java.net.SocketAddress;

public class MyServerMessageHandler extends SimpleChannelInboundHandler<TransferPackage> {

    private int all_idle_times;

    private final Logger logger = LoggerFactory.getLogger(MyServerMessageHandler.class);

    private static ChannelGroup channels;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TransferPackage msg) throws Exception {

        TransferPackageWrap<TransferPackage> packageWrap = new TransferPackageWrap(msg);
        channels.writeAndFlush(packageWrap);

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
        ChatSystemMessage message = new ChatSystemMessage(tip);
        TransferPackageWrap<ChatSystemMessage> packageWrap = new TransferPackageWrap<>(message);

        channels.writeAndFlush(packageWrap);
        channels.add(ctx.channel());
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
