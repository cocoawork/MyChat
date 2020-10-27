package top.cocoawork.chat.server.message;

public class ChatMessageText extends ChatMessage {

    private static final Integer MESSAGE_TYPE_TEXT = 1;

    @Override
    public Integer getMsgType() {
        return MESSAGE_TYPE_TEXT;
    }
}
