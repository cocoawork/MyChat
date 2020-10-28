package top.cocoawork.chat.server.message;

public class ChatMessageText extends ChatMessage<String> {

    @Override
    public Integer getMediaType() {
        return MESSAGE_MEDIA_TYPE_TEXT;
    }
}
