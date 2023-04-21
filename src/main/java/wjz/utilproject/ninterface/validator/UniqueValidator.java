package wjz.utilproject.ninterface.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import wjz.utilproject.enums.UniqueTypeEnums;
import wjz.utilproject.ninterface.Unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import wjz.utilproject.util.BeanUtil;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/21 14:08
 *
 */
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private Class<?> entityClass;
    private String fieldName;
    private UniqueTypeEnums uniqueType;
    private String mainPkName;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.entityClass = constraintAnnotation.entityClass();
        this.fieldName = constraintAnnotation.fieldName();
        this.mainPkName = constraintAnnotation.mainPkName();
        this.uniqueType = constraintAnnotation.uniqueType();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // 获取当前参数所对应的实体对象
        Object entity = BeanUtil.getBean(entityClass);

        // 获取实体对象的所有属性值
        Map<String, Object> props = BeanUtil.getProperties(entity);

        // 判断是否存在名为 "id" 的属性，如果存在则代表是修改操作
        if (UniqueTypeEnums.EDIT.equals(uniqueType)){
            // 获取 "id" 属性的值作为主键
            Object id = props.get(mainPkName);

            // 构建 SQL 查询语句，查询除主键所在的数据外是否存在相同的记录
            String sql = "SELECT COUNT(*) FROM " + entityClass.getSimpleName() +
                    " WHERE " + fieldName + " = ? AND mainPkName != ?";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, value, id);
            return count <= 0;
        } else {
            // 如果没有 "id" 属性，则代表是新增操作，直接查询是否存在相同的记录即可
            String sql = "SELECT COUNT(*) FROM " + entityClass.getSimpleName() + " WHERE " + fieldName + " = ?";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, value);
            return count <= 0;
        }
    }
}
