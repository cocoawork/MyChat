package top.cocoawork.wechat.server.message;

public class ChatSystemMessage extends ChatMessage {

    private String message;

    public String getMessage() {
        return message;
    }

    public ChatSystemMessage(String message) {
        super((byte) 0b00);
        this.message = message;
    }

}
