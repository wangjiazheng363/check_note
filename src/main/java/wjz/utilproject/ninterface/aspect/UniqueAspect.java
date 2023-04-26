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

//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    //这个是环绕增强 通过 @annotation(注解名称) 来定义这个哪个自定义注解的切面类
    //注意 不管在哪个方法上定义了这个注解 调对应的方法后会先进这个方法 然后通过 point.proceed() 调目标方法
//    @Before("@annotation(unique)")
//    public Object around(JoinPoint point, Unique unique) throws Throwable {
//        // 获取目前请求参数
//        Object[] args = point.getArgs();
//        // 获取自定义注解参数
//        Class<?> entityClass = unique.entityClass();
//        String[] fieldNames = unique.fieldNames();
//        UniqueTypeEnums uniqueType = unique.uniqueType();
//        String mainPkName = unique.mainPkName();
//        String message = unique.message();
//        // 获取实体对象的所有属性值
//        Map<String, Object> paramMap = BeanUtil.getProperties(args[0]);
//        Boolean isOk = true;
//        // 判断校验类型是新增还是修改
//        if (UniqueTypeEnums.EDIT.equals(uniqueType)) {
//        // 获取主键的值
//            Object id = paramMap.get(mainPkName);
//            if (id == null) {
//                throw new IllegalArgumentException("非法参数：主键内容为空");
//            }
//            StringBuilder whereClauseBuilder = new StringBuilder();
//            Object[] argsArray = new Object[fieldNames.length + 1];
//            if (fieldNames.length == 1) {
//                whereClauseBuilder.append(camelToUnderline(fieldNames[0])).append(" = ?");
//                argsArray[0] = paramMap.get(fieldNames[0]).toString();
//            } else {
//                whereClauseBuilder.append("(");
//                for (int i = 0; i < fieldNames.length; i++) {
//                    String fieldName = fieldNames[i];
//                    Object fieldValue = paramMap.get(fieldName);
//                    if (fieldValue == null) {
//                        throw new IllegalArgumentException("非法参数：校验参数内容为空");
//                    }
//                    whereClauseBuilder.append(camelToUnderline(fieldName)).append(" = ? OR ");
//                    argsArray[i] = fieldValue.toString();
//                }
//                whereClauseBuilder.delete(whereClauseBuilder.length() - 4, whereClauseBuilder.length());
//                whereClauseBuilder.append(")");
//            }
//
//            whereClauseBuilder.append(" AND ").append(camelToUnderline(mainPkName)).append(" != ?");
//            argsArray[argsArray.length - 1] = id;
//            String sql = "SELECT COUNT(1) FROM " + camelToUnderline( entityClass.getSimpleName()) +
//                    " WHERE " + whereClauseBuilder.toString();
//            System.out.println(sql);
//            int count = jdbcTemplate.queryForObject(sql, Integer.class, argsArray);
//            isOk = count <= 0;
//        } else {
//            StringBuilder whereClauseBuilder = new StringBuilder();
//            Object[] argsArray = new Object[fieldNames.length];
//            if (fieldNames.length == 1) {
//                whereClauseBuilder.append(camelToUnderline(fieldNames[0])).append(" = ?");
//                argsArray[0] = paramMap.get(fieldNames[0]).toString();
//            } else {
//                whereClauseBuilder.append("(");
//                for (int i = 0; i < fieldNames.length; i++) {
//                    String fieldName = fieldNames[i];
//                    Object fieldValue = paramMap.get(fieldName);
//                    if (fieldValue == null) {
//                        throw new IllegalArgumentException("非法参数：校验参数内容为空");
//                    }
//                    whereClauseBuilder.append(camelToUnderline(fieldName)).append(" = ? OR ");
//                    argsArray[i] = fieldValue.toString();
//                }
//                whereClauseBuilder.delete(whereClauseBuilder.length() - 4, whereClauseBuilder.length());
//                whereClauseBuilder.append(")");
//            }
//            String sql = "SELECT COUNT(1) FROM " + camelToUnderline( entityClass.getSimpleName()) +
//                    " WHERE " + whereClauseBuilder.toString();
//            System.out.println(sql);
//            int count = jdbcTemplate.queryForObject(sql, Integer.class, argsArray);
//            isOk = count <= 0;
//        }
//        if (!isOk) {
//            throw new IllegalArgumentException(message);
//        }
//        return ((ProceedingJoinPoint) point).proceed();
//    }
//
//    /***
//     * 驼峰转字符串方法
//     * @param str
//     * @return
//     */
//    public static String camelToUnderline(String str) {
//        if (str == null || str.isEmpty()) {
//            return "";
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < str.length(); i++) {
//            char c = str.charAt(i);
//            if (Character.isUpperCase(c) && i!=0) {
//                sb.append('_').append(Character.toLowerCase(c));
//            } else {
//                sb.append(c);
//            }
//        }
//        return sb.toString();
//    }

}
