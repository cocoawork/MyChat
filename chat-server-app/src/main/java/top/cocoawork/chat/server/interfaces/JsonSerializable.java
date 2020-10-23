<<<<<<< HEAD:chat-server/src/main/java/top/cocoawork/wechat/server/protocol/JsonSerializable.java
package top.cocoawork.wechat.server.protocol;
=======
package top.cocoawork.chat.server.interfaces;
>>>>>>> no message:chat-server-app/src/main/java/top/cocoawork/chat/server/interfaces/JsonSerializable.java

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public interface JsonSerializable  {

    default String toJsonString() {
        return JSON.toJSONString(this);
    }

}
