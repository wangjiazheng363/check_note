package wjz.note.ninterface.impl;

import wjz.note.enums.CheckTypeEnums;
import wjz.note.ninterface.Check;
import wjz.note.util.EmailValidator;
import wjz.note.util.IdCardValidator;
import wjz.note.util.PhoneValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.*;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/19 13:58
 *
 */
public class ParamConstraintValidated implements ConstraintValidator<Check, Object> {
    /**
     * 合法的参数值，从注解中获取
     * */
    private List<String> paramValues;

    private CheckTypeEnums checkType;

    @Override
    public void initialize(Check constraintAnnotation) {
        //初始化时获取注解上的值
        if (constraintAnnotation.paramValues()!=null){
            paramValues = Arrays.asList(constraintAnnotation.paramValues());
        }
        checkType = constraintAnnotation.checkType();
    }

    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o==null){
            return false;
        }
        return this.check(o);
    }

    private boolean check(Object o) {
        if (CheckTypeEnums.CHECK_ID_CODE.equals(checkType)){
            boolean valid = IdCardValidator.validate(o.toString());
            return valid;
        }
        if (CheckTypeEnums.CHECK_TYPE_EMAIL.equals(checkType)){
            boolean valid = EmailValidator.validate(o.toString());
            return valid;
        }
        if (CheckTypeEnums.CHECK_TYPE_PHONE.equals(checkType)){
            boolean valid = PhoneValidator.validate(o.toString());
            return valid;
        }
        if (CheckTypeEnums.CHECK_TYPE_PARAMS.equals(checkType)){
            if (paramValues.contains(o)) {
                return true;
            }
            //不在指定的参数列表中
            return false;
        }
        return true;
    }


}

