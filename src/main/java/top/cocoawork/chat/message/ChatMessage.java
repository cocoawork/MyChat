package top.cocoawork.chat.message;

import com.alibaba.fastjson.JSON;

import java.time.LocalDateTime;

public abstract class ChatMessage extends TransferPackage {

    private String fromIp;
    private String toIp;
    private String fromUid;
    private String toUid;

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

    public ChatMessage() {
        this.setVer(1);
        this.setTime(System.currentTimeMillis());
        this.setType(TransferPackage.PACKAGE_TYPE_DATA);
    }


}
