package top.cocoawork.chat.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import top.cocoawork.chat.server.bootstrap.ChatServer;
import top.cocoawork.chat.server.message.ChatMessage;
import top.cocoawork.chat.server.message.ChatTextMessage;
import top.cocoawork.chat.server.protocol.Packet;

@SpringBootApplication
public class ChatServerApplication implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${server.port}")
    private Integer port;

    public static void main(String[] args) {

        ChatMessage message = new ChatTextMessage(1L, 2L, "特色唐卡的");
        Packet packet = new Packet(Packet.PACKET_TYPE_DATA, message);

        String s = packet.toJsonString();
        System.out.println(s);

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
