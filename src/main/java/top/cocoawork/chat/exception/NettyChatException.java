package top.cocoawork.chat.exception;

public class NettyChatException extends RuntimeException {

    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg == null ? this.getMessage() : msg;
    }

    public NettyChatException(String msg) {
        this.msg = msg;
    }
}
