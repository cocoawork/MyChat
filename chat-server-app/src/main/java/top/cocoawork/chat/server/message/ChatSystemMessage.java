package top.cocoawork.chat.server.message;

public class ChatSystemMessage extends ChatMessage<Byte> {

    transient public static final byte MESSAGE_SYS = 1;

    public ChatSystemMessage(Long fromUid, Long toUid, Byte content) {
        super(fromUid, toUid, MESSAGE_SYS, content);
    }

}
