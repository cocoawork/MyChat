package top.cocoaork.chat.client.interfaces;

import com.alibaba.fastjson.JSON;

public interface JsonSerializable {

    default String toJsonString() {
        return JSON.toJSONString(this);
    }

}
