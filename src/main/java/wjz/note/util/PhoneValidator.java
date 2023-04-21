package wjz.note.util;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/20 8:31
 *
 */
public class PhoneValidator {
    private static final String PHONE_PATTERN =
            "^(1[3-9]\\d{9}|(\\d{3}-)?\\d{8})$";

    /**
     * 验证手机号是否合法
     *
     * @param phone 手机号码
     * @return true-合法，false-不合法
     */
    public static boolean validate(String phone) {
        return phone != null && phone.matches(PHONE_PATTERN);
    }
}
