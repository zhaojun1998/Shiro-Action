package im.zhaojun.common.shiro.exception;

public class AuthcTypeNotSupportException extends RuntimeException {

    private static final long serialVersionUID = -8964524099437750622L;

    public AuthcTypeNotSupportException() {
    }

    public AuthcTypeNotSupportException(String message) {
        super(message);
    }

    public AuthcTypeNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthcTypeNotSupportException(Throwable cause) {
        super(cause);
    }

    public AuthcTypeNotSupportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
