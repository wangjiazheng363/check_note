import org.junit.jupiter.api.Test;
import wjz.utilproject.NoteApplication;
import wjz.utilproject.util.RedisUtil;
import org.springframework.boot.test.context.SpringBootTest;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/21 9:42
 *
 */
@SpringBootTest(classes = NoteApplication.class)
public class RedisTest {

    @Test
    public void redisTest(){
        try {
            // 创建RedisUtil实例并连接到Redis服务器
            RedisUtil redisUtil = new RedisUtil();
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
    }
}
