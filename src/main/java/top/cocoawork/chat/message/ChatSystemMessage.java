package top.cocoawork.chat.message;

public class ChatSystemMessage extends ChatMessage {

    private String message;

    public String getMessage() {
        return message;
    }

    public ChatSystemMessage(String message) {
        super((byte) 0x00);
        this.message = message;

    }

}
