package top.cocoaork.chat.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import top.cocoaork.chat.client.bootstrap.ChatClient;

import java.sql.SQLOutput;

@SpringBootApplication
public class ChatClientApplication implements ApplicationListener<ApplicationReadyEvent> {
    public static void main(String[] args) {
        SpringApplication.run(ChatClientApplication.class,args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        try {
            new ChatClient("127.0.0.1",8899).run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
