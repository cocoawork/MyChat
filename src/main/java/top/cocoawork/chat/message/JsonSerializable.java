package top.cocoawork.chat.message;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public interface JsonSerializable  {

    default String toJsonString() {
        return JSON.toJSONString(this);
    }

}
