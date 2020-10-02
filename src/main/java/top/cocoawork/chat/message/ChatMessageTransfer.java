package top.cocoawork.chat.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.util.CharsetUtil;

import java.util.Arrays;

public class ChatMessageTransfer<E extends ChatMessage> {

    private int len;
    private String data;
    transient protected E obj;

    public ChatMessageTransfer(E obj) {
        this.obj = obj;
        this.data = obj.toJsonString();
        this.len = this.data.length();
    }

    public ChatMessageTransfer(String data) {
        this.data = data;
        this.len = this.data.length();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public E getObj() {
        return obj;
    }

    public void setObj(E obj) {
        this.obj = obj;
    }

    public int getLen() {
        return len;
    }
}
