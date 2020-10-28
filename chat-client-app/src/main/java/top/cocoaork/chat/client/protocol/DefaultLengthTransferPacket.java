package top.cocoaork.chat.client.protocol;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import top.cocoaork.chat.client.interfaces.JsonSerializable;

import java.nio.charset.StandardCharsets;

@Setter
@Getter
public class DefaultLengthTransferPacket<T> implements LengthTransfer {

    transient public static final byte PACKET_TYPE_DATA = 1;   //数据包
    transient public static final byte PACKET_TYPE_MIND = 2;   //心跳包

    transient public static final String PROTOCOL_VERSION = "1.0";

    //数据包类型
    private byte type;

    //时间戳
    private Long timestamp = System.currentTimeMillis() / 1000;

    //协议版本号
    private String version = PROTOCOL_VERSION;

    //数据内容
    private T data;

    private DefaultLengthTransferPacket() {
        throw new AssertionError("sorry, you can not call this private constructor!");
    }

    public DefaultLengthTransferPacket(byte type) {
        this.type = type;
    }

    public DefaultLengthTransferPacket(byte type, T data) {
        this.type = type;
        this.data = data;
    }

    @JSONField(serialize = false, deserialize = false)
    public Integer getLength() {
        return this.getBytes().length;
    }

    @JSONField(serialize = false, deserialize = false)
    public byte[] getBytes(){
        return this.toJsonString().getBytes(StandardCharsets.UTF_8);
    }
}
