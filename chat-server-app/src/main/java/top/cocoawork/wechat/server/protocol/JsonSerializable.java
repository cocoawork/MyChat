package top.cocoawork.wechat.server.protocol;

import com.alibaba.fastjson.JSON;

public interface JsonSerializable  {

    default String toJsonString() {
        return JSON.toJSONString(this);
    }

}
