package top.cocoawork.wechat.server.message;

public class ChatTextMessage extends ChatMessage<String> {

    transient public static final byte MESSAGE_TEXT = 1;

    public ChatTextMessage(Long fromUid, Long toUid, String content) {
        super(fromUid, toUid, MESSAGE_TEXT, content);
    }

}
