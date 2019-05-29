package im.zhaojun.common.shiro.exception;

public class UnknownRedirectUrlException extends RuntimeException{

    private static final long serialVersionUID = -4511193905202048700L;

    public UnknownRedirectUrlException() {
    }

    public UnknownRedirectUrlException(String message) {
        super(message);
    }

    public UnknownRedirectUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownRedirectUrlException(Throwable cause) {
        super(cause);
    }

    public UnknownRedirectUrlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
