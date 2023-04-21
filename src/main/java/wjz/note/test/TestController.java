package wjz.note.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import wjz.note.test.vo.User;
import wjz.note.util.RedisUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/19 14:01
 *
 */
@RestController("/test")
@Slf4j
public class TestController {
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/test")
    public Object test(@Validated @RequestBody User user) {

        try {
            // 创建RedisUtil实例并连接到Redis服务器
            redisUtil.connect();

            // 设置一个字符串类型的键值对，并设置过期时间为60秒
            String key = "demo-key";
            String value = "demo-value";
            redisUtil.set(key, value, 60);

            // 获取该键的值并打印
            String result = redisUtil.get(key, String.class);
            System.out.println("Value of '" + key + "' is: " + result);

            // 关闭Redis连接
            redisUtil.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "注解校验通过";
    }
}
