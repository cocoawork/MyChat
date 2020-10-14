package top.cocoawork.chat.server.protocol;

import com.alibaba.fastjson.JSON;

public interface JsonSerializable  {

    default String toJsonString() {
        return JSON.toJSONString(this);
    }

}
