package top.cocoawork.chat.server.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import top.cocoawork.chat.server.message.ChatMessage;
import top.cocoawork.chat.server.message.ChatSystemMessage;
import top.cocoawork.chat.server.message.ChatTextMessage;
import top.cocoawork.chat.server.protocol.Packet;

import java.util.List;

public class MyByteToMessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        if (in == null) return;

        if (in.readableBytes() <= 4) return;;
        in.markReaderIndex();
        int len = in.readInt();
        if (in.readableBytes() < len) {
            in.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[len];

        in.readBytes(bytes);

        String json = new String(bytes, CharsetUtil.UTF_8);

        in.markReaderIndex();

        //json解析
        JSONObject jsonObject = JSONObject.parseObject(json);
        long time = jsonObject.getLong("time");
        Byte type = jsonObject.getByte("type");
        Integer version = jsonObject.getInteger("ver");

        ChatMessage aPackage = null;

        if (type == Packet.PACKET_TYPE_DATA) {
            //数据包
            Byte msgType = jsonObject.getByte("msgType");
            String fromIp = jsonObject.getString("fromIp");
            if (msgType == 0b01) {
                //文本消息
                ChatTextMessage message = jsonObject.toJavaObject(ChatTextMessage.class);

                aPackage = message;
            }
            if (msgType == 0b00) {
                //文本消息
                ChatSystemMessage message = jsonObject.toJavaObject(ChatSystemMessage.class);
                aPackage = message;
            }
        }

        assert aPackage != null;
//        aPackage.setTimestamp(time);
//        aPackage.setType(type);
//        aPackage.setVersion(version+"");
        out.add(aPackage);

    }


}
