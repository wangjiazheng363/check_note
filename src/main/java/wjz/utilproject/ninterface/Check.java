package wjz.utilproject.ninterface;

import wjz.utilproject.enums.CheckTypeEnums;
import org.springframework.lang.Nullable;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/19 13:49
 *
 */
@Target({ ElementType.FIELD}) //只允许用在类的字段上
@Retention(RetentionPolicy.RUNTIME) //注解保留在程序运行期间，此时可以通过反射获得定义在某个类上的所有注解
@Constraint(validatedBy = ParamConstraintValidated.class)
public @interface Check {

    /***
     * 校验类型
     * @return
     */
    CheckTypeEnums checkType();

    /**
     * 合法的参数值
     * */
    @Nullable
    String[] paramValues() default "{}";

    /**
     * 提示信息
     * */
    String message() default "参数不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
