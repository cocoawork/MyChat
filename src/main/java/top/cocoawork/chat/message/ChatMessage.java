package top.cocoawork.chat.message;

import com.alibaba.fastjson.JSON;

import java.time.LocalDateTime;

public abstract class ChatMessage implements JsonSerializable {

    private String clazz = this.getClass().getName();
    private String from;
    private LocalDateTime time;

    public String getClazz() {
        return clazz;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ChatMessage(String from, LocalDateTime time) {
        this.from = from;
        this.time = time;
    }

    public ChatMessage() {
        time = LocalDateTime.now();
    }

    @Override
    public String toJsonString() {
        return JSON.toJSONString(this);
    }

}
