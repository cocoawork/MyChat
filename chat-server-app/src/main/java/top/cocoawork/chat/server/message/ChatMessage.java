package top.cocoawork.chat.server.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ChatMessage<E> {

    private Long fromUid;
    private Long toUid;
    private byte msgType;
    private E content;

    public ChatMessage(Long fromUid, Long toUid, byte msgType, E content) {
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.msgType = msgType;
        this.content = content;
    }
}
