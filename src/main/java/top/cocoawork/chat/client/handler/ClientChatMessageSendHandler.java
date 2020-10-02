package top.cocoawork.chat.client.handler;

import io.netty.channel.*;
import io.netty.handler.codec.MessageToMessageEncoder;
import top.cocoawork.chat.message.ChatMessage;
import top.cocoawork.chat.message.ChatMessageTransfer;
import top.cocoawork.chat.message.ChatTextMessage;

import java.time.LocalDateTime;
import java.util.List;

public class ClientChatMessageSendHandler extends ChannelInboundHandlerAdapter {

    private Channel channel;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channel = ctx.channel();


        ChatTextMessage message = new ChatTextMessage();
        message.setFrom("sfafasf");
        message.setMessage("this is a test 艾斯百度金矿分为了可交付第三方年网络科技那你啥法是否能为了你问客服是的，放那看DNF螺蛳粉你网络废了你发那么\nasjnflkfnlwefsd");
        for (int i = 0 ; i < 10; i++) {
            sendMessage(message);
            System.out.println("ClientChatMessageSendHandler  sendMessage...");
        }
    }


    public void sendMessage(ChatMessage message) {
        if (channel != null && channel.isWritable()) {
            ChatMessageTransfer<ChatMessage> transfer = new ChatMessageTransfer<>(message);
            channel.writeAndFlush(transfer);
        }
    }

}
