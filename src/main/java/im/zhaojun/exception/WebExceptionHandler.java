package im.zhaojun.exception;

import im.zhaojun.util.ResultBean;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@ResponseBody
public class WebExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler
    public ResultBean unknownAccount(UnknownAccountException e) {
        log.error("账号不存在");
        return ResultBean.error(ResultBean.FAIL, "账号不存在");
    }

    @ExceptionHandler
    public ResultBean incorrectCredentials(IncorrectCredentialsException e) {
        log.error("密码错误");
        return ResultBean.error(ResultBean.FAIL, "密码错误");
    }

    @ExceptionHandler
    public ResultBean lockedAccount(LockedAccountException e) {
        log.error("账号已锁定");
        return ResultBean.error(ResultBean.FAIL, "账号已锁定");
    }

    @ExceptionHandler
    public ResultBean lockedAccount(CaptchaIncorrectException e) {
        log.error("验证码错误");
        return ResultBean.error(ResultBean.FAIL, "验证码错误");
    }

    @ExceptionHandler
    public ResultBean lockedAccount(DuplicateNameException e) {
        log.error("用户名已存在");
        return ResultBean.error(ResultBean.FAIL, "用户名已存在");
    }

    @ExceptionHandler
    public ResultBean missingRequestParameter(MissingServletRequestParameterException e) {
        log.error("请求参数无效");
        return ResultBean.error(ResultBean.FAIL, "请求参数缺失");
    }

    @ExceptionHandler
    public ResultBean methodArgumentNotValid(BindException e) {
        log.error("参数校验失败", e);
        List<ObjectError> allErrors = ((BeanPropertyBindingResult) e.getBindingResult()).getAllErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (int i = 0; i < allErrors.size(); i++) {
            ObjectError error = allErrors.get(i);
            errorMessage.append(error.getDefaultMessage());
            if (i != allErrors.size() - 1) {
               errorMessage.append(",");
            }
        }
        return ResultBean.error(ResultBean.FAIL, errorMessage.toString());
    }

    @ExceptionHandler
    public ResultBean all(Exception e) {
        log.error("出现其他异常:", e);
        return ResultBean.error(ResultBean.FAIL, "系统出现错误, 请重试");
    }

}
