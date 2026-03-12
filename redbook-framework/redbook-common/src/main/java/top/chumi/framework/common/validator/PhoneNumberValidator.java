package top.chumi.framework.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author MingHu
 * @Date 2025/9/30 14:48
 * @Description: TODO
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        //初始化操作
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        //校验逻辑
        return phoneNumber != null && phoneNumber.matches("\\d{11}");
    }
}
