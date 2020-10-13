package top.cocoawork.chat.message;

import java.time.LocalDateTime;

public abstract class TransferPackage implements JsonSerializable {

    public static final byte PACKAGE_TYPE_DATA = 0b00;       //正常业务数据包
    public static final byte PACKAGE_TYPE_TIMEOUT = 0b01;    //超时数据包
    public static final byte PACKAGE_TYPE_MIND = 0b10;       //心跳包

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
