package top.cocoawork.wechat.server.protocol;

public abstract class DataPackage implements JsonSerializable {

    //数据包类型
    public static final byte PACKAGE_TYPE_DATA = 0b00;       //正常业务数据包

    //数据包类型
    private byte type;

    //数据包版本
    private int ver = 1;

    //时间戳
    private long time;

    private String fromIp;

    public String getFromIp() {
        return fromIp;
    }

    public void setFromIp(String fromIp) {
        this.fromIp = fromIp;
    }

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

    public DataPackage() {
    }




}
