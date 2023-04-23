package wjz.utilproject.ninterface.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import wjz.utilproject.enums.UniqueTypeEnums;
import wjz.utilproject.ninterface.Unique;
import wjz.utilproject.util.BeanUtil;
import java.util.Map;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/21 16:23
 *
 */
@Aspect
@Component
public class UniqueAspect {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //这个是环绕增强 通过 @annotation(注解名称) 来定义这个哪个自定义注解的切面类
    //注意 不管在哪个方法上定义了这个注解 调对应的方法后会先进这个方法 然后通过 point.proceed() 调目标方法
    //所以无法通过点注解或者点方法来进这个类 可全局搜索查询 （目前没找到更好的方法，找到请告诉我 ）
    @Before("@annotation(unique)")
    public Object around(JoinPoint point, Unique unique) throws Throwable {
        //获取目前请求参数
        Object[] args = point.getArgs();
        //获取自定义注解参数
        Class<?> entityClass = unique.entityClass();
        String fieldName = unique.fieldName();
        UniqueTypeEnums uniqueType = unique.uniqueType();
        String mainPkName = unique.mainPkName();
        String message = unique.message();
        // 获取实体对象的所有属性值
        Map<String, Object> paramMap = BeanUtil.getProperties(args[0]);
        Boolean isOk = true;
        // 判断校验类型  是新增还是修改
        if (UniqueTypeEnums.EDIT.equals(uniqueType)){
            // 获取主键的值
            Object id = paramMap.get(mainPkName);
            // 构建 SQL 查询语句，查询除主键所在的数据外是否存在相同的记录
            String sql = "SELECT COUNT(*) FROM " + entityClass.getSimpleName() +
                    " WHERE " + camelToUnderline(fieldName) + " = ? AND " + camelToUnderline(mainPkName)  + "!= ?";
            System.out.println(sql);
            int count = jdbcTemplate.queryForObject(sql, Integer.class, paramMap.get(fieldName).toString(), id);
            isOk = count <= 0;
        } else {
            // 如果没有 "id" 属性，则代表是新增操作，直接查询是否存在相同的记录即可
            String sql = "SELECT COUNT(*) FROM " + entityClass.getSimpleName() + " WHERE " + camelToUnderline(fieldName)  + " = ?";
            System.out.println(sql);
            int count = jdbcTemplate.queryForObject(sql, Integer.class, paramMap.get(fieldName).toString());
            isOk = count <= 0;
        }
        if (!isOk){
            throw new IllegalArgumentException(message);
        }
        return ((ProceedingJoinPoint) point).proceed();

    }

    /***
     * 驼峰转字符串方法
     * @param str
     * @return
     */
    public static String camelToUnderline(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_').append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
