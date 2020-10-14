package top.cocoawork.chat.server.message;

import top.cocoawork.chat.server.protocol.DataPackage;

public abstract class ChatMessage extends DataPackage {

    private String fromUid;
    private String toUid;

    /**
     * 消息类型
     * 系统消息： 0b00
     * 文本消息： 0b01
     */
    private byte msgType;

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }

    public ChatMessage(byte msgType) {
        this.msgType = msgType;
        this.setVer(1);
        this.setTime(System.currentTimeMillis());
        this.setType(DataPackage.PACKAGE_TYPE_DATA);
    }


}
