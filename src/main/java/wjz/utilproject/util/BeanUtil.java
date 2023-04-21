package wjz.utilproject.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
/***
 *
 * Author:WangJiazheng
 * Data:2023/4/21 14:51
 *
 */
public class BeanUtil {

    /**
     * 获取对象的所有属性及其值，返回一个 Map 对象
     */
    /**
     * 获取对象的所有属性及其值，返回一个 Map 对象
     */
    public static Map<String, Object> getProperties(Object object) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Map<String, String> propertyMap = BeanUtils.describe(object);
            for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
                String propertyName = entry.getKey();
                String propertyValue = entry.getValue();
                Object convertedValue = convertPropertyValue(propertyValue);
                resultMap.put(propertyName, convertedValue);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    /**
     * 根据类名获取类的实例
     */
    public static <T> T getBean(Class<T> clazz) {
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }


    /**
     * 将属性值转换为适当的类型
     */
    private static Object convertPropertyValue(String value) {
        if (value == null) {
            return null;
        } else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(value);
        } else if (value.matches("^[-+]?\\d+$")) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                return Integer.parseInt(value);
            }
        } else if (value.matches("^[-+]?\\d+\\.\\d*$")) {
            return Double.parseDouble(value);
        } else {
            return value;
        }
    }
}
