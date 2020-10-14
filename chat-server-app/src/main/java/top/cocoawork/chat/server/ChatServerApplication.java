package top.cocoawork.chat.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import top.cocoawork.chat.server.bootstrap.ChatServer;

@SpringBootApplication
public class ChatServerApplication implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${server.port}")
    private Integer port;

    public static void main(String[] args) {
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
