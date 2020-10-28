package top.cocoaork.chat.client.message;

public class ChatMessageText extends ChatMessage<String> {

    @Override
    public Integer getMediaType() {
        return MESSAGE_MEDIA_TYPE_TEXT;
    }
}
