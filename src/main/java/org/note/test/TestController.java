package org.note.test;

import lombok.extern.slf4j.Slf4j;
import org.note.test.vo.User;
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

    @PostMapping("/test")
    public Object test(@Validated @RequestBody User user) {
        System.out.println(user);
        return "注解校验通过";
    }
}
