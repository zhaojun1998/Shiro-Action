package im.zhaojun.exception;

import org.apache.shiro.authc.AuthenticationException;

public class CaptchaIncorrectException extends AuthenticationException {

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
