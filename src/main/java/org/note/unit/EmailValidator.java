package org.note.unit;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/19 16:38
 *
 */
import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_PATTERN =
            "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * 验证邮箱地址格式是否正确
     *
     * @param email 邮箱地址
     * @return true-格式正确，false-格式错误
     */
    public static boolean validate(String email) {
        return pattern.matcher(email).matches();
    }
}