package top.cocoawork.chat.server.exception;

import top.cocoawork.chat.common.exception.MyChatException;

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
