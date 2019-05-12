package im.zhaojun.common.exception;

public class DuplicateNameException extends RuntimeException {

    private static final long serialVersionUID = 1882153240006350935L;

    public DuplicateNameException() {
    }

    public DuplicateNameException(String message) {
        super(message);
    }

    public DuplicateNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateNameException(Throwable cause) {
        super(cause);
    }
}
