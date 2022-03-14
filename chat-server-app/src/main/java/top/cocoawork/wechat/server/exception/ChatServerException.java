package top.cocoawork.wechat.server.exception;

import top.cocoawork.wechat.common.exception.MyChatException;

public class ChatServerException extends MyChatException {
    public ChatServerException() {
    }

    public ChatServerException(String message) {
        super(message);
    }

    public ChatServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatServerException(Throwable cause) {
        super(cause);
    }
}
