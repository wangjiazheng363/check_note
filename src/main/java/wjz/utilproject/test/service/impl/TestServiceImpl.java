package wjz.utilproject.test.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import wjz.utilproject.enums.UniqueTypeEnums;
import wjz.utilproject.ninterface.Unique;
import wjz.utilproject.test.service.TestService;
import wjz.utilproject.test.vo.User;
import wjz.utilproject.util.RedisUtil;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/21 13:34
 *
 */
@Service
@Slf4j
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class TestServiceImpl implements TestService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void redisTest() {
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
    }

    @Override
    @Unique(entityClass = User.class, fieldNames = {"idCode"})
    public void addUser(User user) {
        log.info("校验完成");
    }

    @Override
    @Unique(entityClass = User.class, fieldNames = {"idCode","phone"} ,uniqueType = UniqueTypeEnums.EDIT   )
    public void editUser(User user) {
        log.info("校验完成");
    }

}
