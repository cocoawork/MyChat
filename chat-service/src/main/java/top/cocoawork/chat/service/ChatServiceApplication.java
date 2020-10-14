package top.cocoawork.chat.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"top.cocoawork.chat.dao", "top.cocoawork.chat.service.impl"})
@MapperScan(basePackages = "top.cocoawork.chat.dao.mapper")
public class ChatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatServiceApplication.class,args);
    }

}
