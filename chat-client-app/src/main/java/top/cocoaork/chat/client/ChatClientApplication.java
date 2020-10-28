package top.cocoaork.chat.client;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import top.cocoaork.chat.client.bootstrap.ChatClient;
import top.cocoaork.chat.client.message.ChatMessageText;

import java.util.Scanner;


/**
 * 聊天客户端
 * 主要用作调试服务端
 */
@SpringBootApplication
public class ChatClientApplication implements ApplicationListener<ApplicationReadyEvent> {

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(ChatClientApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            ChatClient chatClient = new ChatClient("127.0.0.1", 8899);
            chatClient.runWithListener(new GenericFutureListener() {
                @Override
                public void operationComplete(Future future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("连接成功，开始准备信息输入");

                        new Thread(() -> {
                            while (scanner.hasNextLine()) {
                                String line = scanner.nextLine();
                                ChatMessageText messageText = new ChatMessageText();
                                messageText.setContent(line);
                                messageText.setMediaType(0);
                                messageText.setMsgType(1);
                                chatClient.sendMessage(messageText);
                            }
                        }).start();

                    }
                }
            });



        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
