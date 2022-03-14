package top.cocoawork.wechat.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ={"top.cocoawork.wechat.*"})
public class ChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class,args);
    }

}
