package top.cocoawork.chat.codec;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import top.cocoawork.chat.message.ChatSystemMessage;
import top.cocoawork.chat.message.ChatTextMessage;
import top.cocoawork.chat.message.TransferPackage;
import top.cocoawork.chat.message.TransferPackageWrap;

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

        TransferPackage aPackage = null;

        if (type == TransferPackage.PACKAGE_TYPE_DATA) {
            //数据包
            Byte msgType = jsonObject.getByte("msgType");
            String fromIp = jsonObject.getString("fromIp");
            if (msgType == 0x01) {
                //文本消息
                ChatTextMessage message = jsonObject.toJavaObject(ChatTextMessage.class);
                message.setFromIp(fromIp);

                aPackage = message;
            }
            if (msgType == 0x00) {
                //文本消息
                ChatSystemMessage message = jsonObject.toJavaObject(ChatSystemMessage.class);
                aPackage = message;
            }


        } else if (type == TransferPackage.PACKAGE_TYPE_MIND) {
            //心跳包

        } else if (type == TransferPackage.PACKAGE_TYPE_TIMEOUT) {
            //超时数据包

        }

        aPackage.setTime(time);
        aPackage.setType(type);
        aPackage.setVer(version);
        out.add(aPackage);

        System.out.println("in引用计数=" + in.refCnt());


    }


}
