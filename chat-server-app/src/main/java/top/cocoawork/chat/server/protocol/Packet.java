package top.cocoawork.chat.server.protocol;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import top.cocoawork.chat.server.interfaces.JsonSerializable;

import java.nio.charset.StandardCharsets;

@Getter
public class Packet<T> implements JsonSerializable {

    transient public static final byte PACKET_TYPE_DATA = 1;   //数据包
    transient public static final byte PACKET_TYPE_MIND = 2;   //心跳包

    transient public static final String PROTOCOL_VERSION = "1.0";

    //数据包类型
    private byte type;

    //时间戳
    private Long timestamp = System.currentTimeMillis() / 1000;

    //协议版本号
    private String version = PROTOCOL_VERSION;

    //数据长度
    @JSONField(serialize = false, deserialize = false)
    private transient Integer length;

    //数据包字节数组
    @JSONField(serialize = false, deserialize = false)
    private transient byte[] bytes;

    //数据内容
    private T data;

    private Packet() {
        throw new AssertionError("sorry, you can not call this private constructor!");
    }

    public Packet(byte type) {
        this.type = type;
    }

    public Packet(byte type, T data) {
        this.type = type;
        this.data = data;
    }

    public Integer getLength() {
        return this.getBytes().length;
    }

    public byte[] getBytes(){
        return this.toJsonString().getBytes(StandardCharsets.UTF_8);
    }
}
