package top.cocoawork.chat.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import top.cocoawork.chat.server.bootstrap.ChatServer;
import top.cocoawork.chat.server.message.ChatMessage;
import top.cocoawork.chat.server.message.ChatMessageText;
import top.cocoawork.chat.server.protocol.DefaultLengthTransferPacket;

@SpringBootApplication
public class ChatServerApplication implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${server.port}")
    private Integer port;

    public static void main(String[] args) {
//
//        ChatMessage message = new ChatMessageText();
//        message.setContent("asashdksadjkasjdkas".getBytes());
//        message.setRecType(0);
//        message.setFromUid(1L);
//
//        DefaultLengthTransferPacket packet = new DefaultLengthTransferPacket(DefaultLengthTransferPacket.PACKET_TYPE_DATA, message);
//
//        String s = packet.toJsonString();
//        Integer length = packet.getLength();
//        System.out.println(length);
//        System.out.println(s);

        SpringApplication.run(ChatServerApplication.class,args);
    }

    //应用启动
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            new ChatServer(port).run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
