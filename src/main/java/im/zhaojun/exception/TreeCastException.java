package im.zhaojun.exception;

public class TreeCastException extends RuntimeException {
    private static final long serialVersionUID = -7358633666514111106L;

    public TreeCastException() {
        super();
    }

    public TreeCastException(String message) {
        super(message);
    }

    public TreeCastException(String message, Throwable cause) {
        super(message, cause);
    }

    public TreeCastException(Throwable cause) {
        super(cause);
    }

    protected TreeCastException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
