package im.zhaojun.common.exception;

public class UnknownRedirectUrlException extends RuntimeException{

    private static final long serialVersionUID = -4511193905202048700L;

    public UnknownRedirectUrlException(String message) {
        super(message);
    }
}
