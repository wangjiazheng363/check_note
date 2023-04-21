package wjz.utilproject.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import wjz.utilproject.config.RedisConfig;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/21 8:56
 *
 */
@Component
public class RedisUtil {
    private static Jedis jedis;

    @Autowired
    private RedisConfig redisConfig;

    // 连接Redis服务器
    public void connect() {
        jedis = new Jedis(redisConfig.getHost(), redisConfig.getPort());
    }

    // 关闭Redis连接
    public static void close() {
        if (jedis != null) {
            jedis.close();
        }
    }

    // 获取Redis键值对，并转换为指定类型
    public static <T> T get(String key, Class<T> valueType) {
        String value = jedis.get(key);
        return value == null ? null : fromJson(value, valueType);
    }

    // 设置Redis键值对，并指定过期时间（单位：秒）
    public static void set(String key, Object value) {

        try {
            jedis.set(key, toJson(value));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // 设置Redis键值对，并指定过期时间（单位：秒）
    public static void set(String key, Object value, int expireSeconds) {
        try {
            SetParams params = new SetParams().ex(expireSeconds);
            jedis.set(key, toJson(value), params);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // 设置Redis键值对，并返回旧值
    public static <T> T getSet(String key, Object value, Class<T> valueType) {
        try {
            String oldValue = jedis.getSet(key, toJson(value));
            return oldValue == null ? null : fromJson(oldValue, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // 删除Redis键值对
    public static void delete(String key) {
        jedis.del(key);
    }

    // 获取Redis键的过期时间（单位：秒）
    public static long getExpire(String key) {
        return jedis.ttl(key);
    }

    // 设置Redis键的过期时间（单位：秒）
    public static void setExpire(String key, int expireSeconds) {
        jedis.expire(key, expireSeconds);
    }

    // 将Java对象转换为JSON字符串
    private static String toJson(Object value) throws JsonProcessingException {
        return JsonUtil.toJson(value);
    }

    // 将JSON字符串转换为Java对象
    private static <T> T fromJson(String json, Class<T> valueType) {
        try {
            return JsonUtil.fromJson(json, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}