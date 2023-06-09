package wjz.utilproject.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import wjz.utilproject.test.service.TestService;
import wjz.utilproject.test.vo.User;
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
    private TestService testService;

    @PostMapping("/test")
    public Object test(@Validated @RequestBody User user) {
        return "注解校验通过";
    }

    @PostMapping("/redisTest")
    public void redisTest(){
        testService.redisTest();
    }

    @PostMapping("/addTest")
    public String addTest(@Validated @RequestBody User user){
        try {
            testService.addUser(user);
            return "保存成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @PostMapping("/editTest")
    public String editTest(@Validated @RequestBody User user){
        try {
            testService.editUser(user);
            return "保存成功";
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    @PostMapping("/copyData")
    public void copyData() {
        testService.copyTableFromDatabaseAToDatabaseB();
    }
}
