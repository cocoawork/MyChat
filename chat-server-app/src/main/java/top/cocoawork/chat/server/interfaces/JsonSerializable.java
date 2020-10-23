package top.cocoawork.chat.server.interfaces;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public interface JsonSerializable  {

    default String toJsonString() {
        return JSON.toJSONString(this);
    }

}
