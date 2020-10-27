package top.cocoaork.chat.client.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import top.cocoaork.chat.client.message.ChatMessage;
import top.cocoaork.chat.client.message.ChatMessageSystem;
import top.cocoaork.chat.client.message.ChatMessageText;
import top.cocoaork.chat.client.protocol.DefaultLengthTransferPacket;


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
        Byte type = jsonObject.getByteValue("type");
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
            Integer msgType = jsonDataObject.getInteger("msgType");
            if (msgType == 1) {
                //文本消息
                ChatMessageText messageText = jsonDataObject.toJavaObject(ChatMessageText.class);

                message = messageText;
            }
            if (msgType == 0) {
                //系统消息
                ChatMessageSystem messageSystem = jsonDataObject.toJavaObject(ChatMessageSystem.class);
                message = messageSystem;
            }
        }


        DefaultLengthTransferPacket<ChatMessage> transferPacket = new DefaultLengthTransferPacket<>(type, message);

        out.add(transferPacket);

    }


}
