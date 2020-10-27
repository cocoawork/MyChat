package top.cocoaork.chat.client.message;

import lombok.Data;
import top.cocoaork.chat.client.interfaces.JsonSerializable;

@Data
public abstract class ChatMessage implements JsonSerializable {

    private Long fromUid;

    private Long toUid;

    private Integer recType; //接收者类型 O：单聊，1：群聊

    private byte[] content;

    private Integer msgType;


    /**
     * Gets msg type.
     *
     * @return the msg type
     * 0：系统消息
     * 1：文本消息
     * 2：图片消息
     */
    abstract public Integer getMsgType(); //消息类型（文本消息）


}
