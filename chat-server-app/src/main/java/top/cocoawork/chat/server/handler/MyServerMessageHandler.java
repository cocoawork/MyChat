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
import top.cocoawork.chat.server.message.ChatMessage;
import top.cocoawork.chat.server.message.ChatMessageText;
import top.cocoawork.chat.server.protocol.DefaultLengthTransferPacket;
import top.cocoawork.chat.server.protocol.LengthTransfer;

import java.net.SocketAddress;

public class MyServerMessageHandler extends SimpleChannelInboundHandler<LengthTransfer> {

    private int all_idle_times;

    private final Logger logger = LoggerFactory.getLogger(MyServerMessageHandler.class);

    private static ChannelGroup channels;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LengthTransfer msg) throws Exception {

        logger.debug("数据回写：" + msg.toJsonString());

        //数据回写、数据转发
        if (msg instanceof DefaultLengthTransferPacket) {
            DefaultLengthTransferPacket packet = (DefaultLengthTransferPacket) msg;
            Object data = packet.getData();
            if (data instanceof ChatMessage) {
                ChatMessage message = (ChatMessage) data;
                Integer msgType = message.getMsgType();

                //消息接收方为空，忽略当前消息
                if (null == message.getToUid()) {
                    return;
                }

                if (msgType == 0) {
                    //单聊
                    //TODO: 查询toUid对应的用于连接ip，根据ip转发消息


                }else if (msgType == 1) {
                    //群聊
                    //TODO: 根据toUid查询群组成员uid，根据成员uid查询成员登录ip，依次转发消息

                }

            }
        }
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

        logger.debug(tip);

        ChatMessageText message = new ChatMessageText();
        message.setContent(tip);
        message.setMediaType(0);
        message.setMsgType(-1);
        LengthTransfer transfer = new DefaultLengthTransferPacket(DefaultLengthTransferPacket.PACKET_TYPE_DATA, message);

        channels.writeAndFlush(transfer);
        channels.add(ctx.channel());

        int size = channels.size();
        logger.debug("新设备连接，channels中连接数量：" + size);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        int size = channels.size();
        logger.debug("设备断开，channels中连接数量：" + size);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("连接发生异常 \n 连接：{} \n原因：{}", ctx.channel(), cause);
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
