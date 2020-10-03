package top.cocoawork.chat.client.handler;

import io.netty.channel.*;
import top.cocoawork.chat.message.ChatTextMessage;
import top.cocoawork.chat.message.TransferPackage;
import top.cocoawork.chat.message.TransferPackageWrap;

public class ClientChatMessageSendHandler extends ChannelInboundHandlerAdapter {

    private Channel channel;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channel = ctx.channel();


        ChatTextMessage message = new ChatTextMessage("今天没有去上班，来到了郑州东区的某个鸟不拉屎的工地，天气下雨了，有点微冷☔️！！！");
        message.setFromIp("67.5.5.4");
        message.setFromUid("7834875435");
        for (int i = 0; i < 100; i++) {
            sendMessage(message);
        }


    }


    public void sendMessage(TransferPackage transferPackage) {
        if (channel != null && channel.isWritable()) {
            TransferPackageWrap transfer = new TransferPackageWrap<>(transferPackage);
            channel.writeAndFlush(transfer);
        }
    }

}
