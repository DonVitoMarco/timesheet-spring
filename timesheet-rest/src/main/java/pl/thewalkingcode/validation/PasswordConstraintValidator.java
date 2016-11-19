package pl.thewalkingcode.validation;

import pl.thewalkingcode.model.UserRegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, Object> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        UserRegisterDTO userRegisterDTO = (UserRegisterDTO) obj;
        return userRegisterDTO.getPassword().equals(userRegisterDTO.getMatchingPassword());
    }

}
