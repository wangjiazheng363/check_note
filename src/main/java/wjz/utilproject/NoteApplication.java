package wjz.utilproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/19 13:46
 *
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class NoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(NoteApplication.class,args);

    }
}
