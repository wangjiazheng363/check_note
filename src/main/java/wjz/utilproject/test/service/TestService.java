package wjz.utilproject.test.service;

import wjz.utilproject.ninterface.Unique;
import wjz.utilproject.test.vo.User;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/21 13:34
 *
 */
public interface TestService {


    void redisTest();

    @Unique(entityClass = User.class, fieldName = "user_name")
    void addUser(User user);

}
