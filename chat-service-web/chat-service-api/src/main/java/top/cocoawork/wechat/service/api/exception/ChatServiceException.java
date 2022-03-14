package top.cocoawork.wechat.service.api.exception;

import top.cocoawork.wechat.common.exception.MyChatException;

public class ChatServiceException extends MyChatException {

    public ChatServiceException() {
    }

    public ChatServiceException(String message) {
        super(message);
    }

    public ChatServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChatServiceException(Throwable cause) {
        super(cause);
    }
}
