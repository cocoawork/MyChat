package top.cocoawork.wechat.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"top.cocoawork.wechat.dao", "top.cocoawork.wechat.service.impl"})
@MapperScan(basePackages = "top.cocoawork.wechat.dao.mapper")
public class ChatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatServiceApplication.class,args);
    }

}
