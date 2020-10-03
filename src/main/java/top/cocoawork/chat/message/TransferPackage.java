package top.cocoawork.chat.message;

import java.time.LocalDateTime;

public abstract class TransferPackage implements JsonSerializable {

    public static final byte PACKAGE_TYPE_DATA = 1 << 1;       //正常业务数据包
    public static final byte PACKAGE_TYPE_TIMEOUT = 1 << 2;    //超时数据包
    public static final byte PACKAGE_TYPE_MIND = 1 << 3;       //心跳包


    //消息类型  0xAF 表示心跳包    0xBF 表示超时包  0xCF 业务信息包
    private byte type;

    //数据包版本
    private int ver = 1;

    private long time;


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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public TransferPackage() {
    }




}
