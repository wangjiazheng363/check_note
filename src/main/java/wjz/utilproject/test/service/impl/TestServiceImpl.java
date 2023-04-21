package wjz.utilproject.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

//    @Autowired
//    private UserDao userDao;

    @Override
    @Unique(entityClass = User.class, fieldName = "user_name")
    public void addUser(User user) {
//        userDao.addUser(user);
    }

}
