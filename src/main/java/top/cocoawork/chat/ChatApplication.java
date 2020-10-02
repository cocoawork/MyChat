package top.cocoawork.chat;

import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import top.cocoawork.chat.client.ChatClient;
import top.cocoawork.chat.message.ChatTextMessage;
import top.cocoawork.chat.server.ChatServer;

import java.time.LocalDateTime;

public class ChatApplication {

    public static void main(String[] args) {

        Integer port = 9999;

        ChatServer chatServer = new ChatServer(port);
        ChatClient chatClient = new ChatClient("127.0.0.1", port);
        try {
            chatServer.run();
            Thread.sleep(5000);
            chatClient.run();
        } catch (InterruptedException e) {
            chatServer.shutdown();
            chatClient.shutdown();
        }
    }

}
