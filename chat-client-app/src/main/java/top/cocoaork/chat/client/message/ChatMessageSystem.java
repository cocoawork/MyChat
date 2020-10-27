package top.cocoaork.chat.client.message;

public class ChatMessageSystem extends ChatMessage {

    private static final Integer MESSAGE_TYPE_SYS = 0;

    @Override
    public Integer getMsgType() {
        return MESSAGE_TYPE_SYS;
    }
}
