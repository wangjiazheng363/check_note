package wjz.utilproject.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/***
 *
 * Author:WangJiazheng
 * Data:2023/4/21 9:09
 *
 */
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    // 将Java对象转换为JSON字符串
    public static String toJson(Object value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }

    // 将JSON字符串转换为Java对象
    public static <T> T fromJson(String json, Class<T> valueType) throws JsonProcessingException {
        return mapper.readValue(json, valueType);
    }
}
