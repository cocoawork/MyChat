package top.cocoawork.chat.message;

import com.alibaba.fastjson.JSON;

import java.time.LocalDateTime;

public abstract class ChatMessage extends TransferPackage {

    private String fromIp;
    private String toIp;
    private String fromUid;
    private String toUid;

    /**
     * 消息类型
     * 系统消息： 0x00
     * 文本消息： 0x01
     */
    private byte msgType;


    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public String getFromIp() {
        return fromIp;
    }

    public void setFromIp(String fromIp) {
        this.fromIp = fromIp;
    }

    public String getToIp() {
        return toIp;
    }

    public void setToIp(String toIp) {
        this.toIp = toIp;
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
        this.setType(TransferPackage.PACKAGE_TYPE_DATA);
    }


}
