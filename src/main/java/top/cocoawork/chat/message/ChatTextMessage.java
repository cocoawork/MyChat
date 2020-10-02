package top.cocoawork.chat.message;

import java.time.LocalDateTime;

public class ChatTextMessage extends ChatMessage {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatTextMessage(String from, LocalDateTime time, String message) {
        super(from, time);
        this.message = message;
    }

    public ChatTextMessage(String message) {
        this.message = message;
    }

    public ChatTextMessage() {
    }
}
