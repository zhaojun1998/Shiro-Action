package im.zhaojun.validate.constraintvalidators;

import im.zhaojun.service.UserService;
import im.zhaojun.validate.constraints.UserNameNotDuplicate;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameNotDuplicateValidator implements ConstraintValidator<UserNameNotDuplicate, String> {

    @Resource
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userService.checkUserNameExist(value);
    }
}
