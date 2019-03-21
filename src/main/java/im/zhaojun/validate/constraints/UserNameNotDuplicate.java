package im.zhaojun.validate.constraints;

import im.zhaojun.validate.constraintvalidators.UserNameNotDuplicateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { METHOD, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = UserNameNotDuplicateValidator.class)
@Documented
@NotBlank
public @interface UserNameNotDuplicate {

    String message() default "用户名已存在";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link UserNameNotDuplicate} annotations on the same element.
     *
     * @see UserNameNotDuplicate
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        UserNameNotDuplicate[] value();
    }
}
