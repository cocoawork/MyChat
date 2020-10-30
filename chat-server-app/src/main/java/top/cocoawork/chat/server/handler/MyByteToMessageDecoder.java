package top.cocoawork.chat.server.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import top.cocoawork.chat.server.message.ChatMessage;
import top.cocoawork.chat.server.message.ChatMessageText;
import top.cocoawork.chat.server.protocol.DefaultLengthTransferPacket;

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
        long time = jsonObject.getLong("timestamp");
        byte type = jsonObject.getByteValue("type");
        String version = jsonObject.getString("version");

        JSONObject jsonDataObject = jsonObject.getJSONObject("data");


        if (null == jsonDataObject) {
            return;
        }

        ChatMessage message = null;
        if (type == DefaultLengthTransferPacket.PACKET_TYPE_DATA) {
            //数据包
//            Long fromUid = jsonDataObject.getLong("fromUid");
//            Long toUid = jsonDataObject.getLong("toUid");
//            Integer recType = jsonDataObject.getInteger("recType");
//            Integer msgType = jsonDataObject.getInteger("msgType");
            Integer mediaType = jsonDataObject.getInteger("mediaType");

            if (mediaType == 0) {
                //文本消息
                ChatMessageText messageText = jsonDataObject.toJavaObject(ChatMessageText.class);
                message = messageText;
            }
        }

        DefaultLengthTransferPacket<ChatMessage> packet = new DefaultLengthTransferPacket<>(type, message);
        packet.setTimestamp(time);
        packet.setVersion(version);
        out.add(packet);


    }


}
