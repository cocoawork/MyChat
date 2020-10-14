package top.cocoawork.chat.server.message;

public class ChatTextMessage extends ChatMessage {

    private String message;

    public String getMessage() {
        return message;
    }

    public ChatTextMessage(String message) {
        super((byte) 0b01);
        this.message = message;
    }

}
