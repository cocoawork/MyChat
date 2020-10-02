package top.cocoawork.chat.message;

import java.time.LocalDateTime;

public class ChatFileMessage extends ChatMessage {

    private byte[] file;

    public ChatFileMessage(String from, LocalDateTime time, byte[] file) {
        super(from, time);
        this.file = file;
    }

    public ChatFileMessage(byte[] file) {
        this.file = file;
    }

}
