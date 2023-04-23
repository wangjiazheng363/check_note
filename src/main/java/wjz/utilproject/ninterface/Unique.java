package wjz.utilproject.ninterface;

import wjz.utilproject.enums.UniqueTypeEnums;

import javax.validation.Payload;
import java.lang.annotation.*;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/21 14:04
 *
 */
@Target(ElementType.METHOD) // 该注解只能用于方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Unique {

    /**
     * 需要校验的实体类
     */
    Class<?> entityClass();

    /**
     * 校验的字段名称
     */
    String fieldName();

    /***
     * 校验类型   新建会校验全表，修改则校验 不包含当前主键的值是否重复  修改时 可以通过 增加参数  主键字段名称
     * @return
     */
    UniqueTypeEnums uniqueType() default UniqueTypeEnums.ADD;

    /***
     * 主键字段
     */
    String mainPkName() default "pk";
    /**
     * 提示信息
     */
    String message() default "已存在相同的记录";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}