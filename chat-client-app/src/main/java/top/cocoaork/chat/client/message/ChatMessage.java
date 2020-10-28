package top.cocoaork.chat.client.message;

import lombok.Data;
import top.cocoaork.chat.client.interfaces.JsonSerializable;

@Data
public abstract class ChatMessage<T> implements JsonSerializable {

    transient protected static final Integer MESSAGE_MEDIA_TYPE_TEXT = 0;

    private Long fromUid;

    private Long toUid;

    private T content;

    private Integer msgType;  //接收者类型 -1：系统消息， O：单聊，1：群聊

    private Integer mediaType; //消息内容类型： 0：文本， 1：图片


    /**
     * Gets msg type.
     *
     * @return the msg type
     * 0：系统消息
     * 1：文本消息
     * 2：图片消息
     */
    abstract public Integer getMediaType(); //消息类型（文本消息）


}
