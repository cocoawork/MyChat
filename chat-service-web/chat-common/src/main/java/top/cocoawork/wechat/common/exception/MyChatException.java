package top.cocoawork.wechat.common.exception;

public class MyChatException extends RuntimeException {

    public MyChatException() {
    }

    public MyChatException(String message) {
        super(message);
    }

    public MyChatException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyChatException(Throwable cause) {
        super(cause);
    }
}
