package top.cocoawork.chat.message;

public abstract class ProtocolDataPackage {

    //消息类型  0xAF 表示心跳包    0xBF 表示超时包  0xCF 业务信息包
    private byte type;

    //数据包版本
    private int ver = 1;

    //数据包长度
    private int len;


    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
