package im.zhaojun.common.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CaptchaIncorrectException extends AuthenticationException {

    private static final long serialVersionUID = 2682461331543282364L;

    public CaptchaIncorrectException() {
        super();
    }

    public CaptchaIncorrectException(String message) {
        super(message);
    }

    public CaptchaIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public CaptchaIncorrectException(Throwable cause) {
        super(cause);
    }
}
