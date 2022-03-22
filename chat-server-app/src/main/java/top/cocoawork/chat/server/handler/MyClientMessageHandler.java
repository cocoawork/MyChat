package top.cocoawork.chat.server.handler;

import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.cocoawork.chat.server.message.ChatMessage;
import top.cocoawork.chat.server.protocol.DefaultLengthTransferPacket;
import top.cocoawork.chat.server.protocol.LengthTransfer;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MyClientMessageHandler extends SimpleChannelInboundHandler<LengthTransfer> {

    private final Logger logger = LoggerFactory.getLogger(MyClientMessageHandler.class);

    private Channel channel;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channel = ctx.channel();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    if (scanner.hasNextLine()){
//                        String line = scanner.nextLine();
//                        ChatMessageText message = new ChatMessageText();
//                        message.setContent(line.getBytes());
//                        sendMessage(message);
//                    }
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();



    }


    public void sendMessage(ChatMessage message) {
        if (channel != null && channel.isWritable()) {
            LengthTransfer transfer = new DefaultLengthTransferPacket<>(DefaultLengthTransferPacket.PACKET_TYPE_DATA, message);
            channel.writeAndFlush(transfer);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LengthTransfer msg) throws Exception {

        byte[] bytes = msg.getBytes();

        String content = new String(bytes);

        System.out.println("接收到回传消息：" + content);

//        if (msg.get() == TransferPackage.PACKAGE_TYPE_DATA) {
//            ChatMessage message = (ChatMessage)msg;
//            long time = msg.getTime();
//            LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
//            DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
//            //系统消息
//            if (message.getMsgType() == 0x00) {
//                System.out.println("["+dateTime.format(formatter)+"] " + ((ChatSystemMessage)message).getMessage());
//            }
//
//            //文本消息
//            if (message.getMsgType() == 0x01) {
//                ChatTextMessage textMessage = (ChatTextMessage)message;
//                System.out.println("["+textMessage.getFromIp()+"] : " + textMessage.getMessage());
//            }
//
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
    }

}
