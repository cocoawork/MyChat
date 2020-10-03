package top.cocoawork.chat.message;

import java.time.LocalDateTime;

public class ChatTextMessage extends ChatMessage {

    private String message;

    public String getMessage() {
        return message;
    }

    public ChatTextMessage(String message) {
        super();
        this.message = message;
    }

}
