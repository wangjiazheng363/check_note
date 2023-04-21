package wjz.utilproject.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/19 16:08
 *
 */




public class IdCardValidator {

    /**
     * 身份证号码校验
     *
     * @param idCard 身份证号码
     * @return true-校验通过，false-校验失败
     */
    public static boolean validate(String idCard) {
        // 判断身份证号码是否为空
        if (idCard == null || idCard.trim().isEmpty()) {
            return false;
        }

        // 将身份证号码转换为大写字母
        idCard = idCard.toUpperCase();

        // 校验身份证号码长度
        if (idCard.length() != 18) {
            return false;
        }

        // 获取前17位数字部分
        String idCard17 = idCard.substring(0, 17);

        // 校验前17位数字是否全是数字
        if (!isNumeric(idCard17)) {
            return false;
        }

        // 校验地区码是否正确
        String provinceCode = idCard.substring(0, 2);
        if (!isValidProvinceCode(provinceCode)) {
            return false;
        }

        // 校验生日是否正确
        String birthday = idCard.substring(6, 14);
        if (!isValidDate(birthday)) {
            return false;
        }

        // 校验校验码是否正确
        int sum = 0;
        char[] chars = idCard17.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sum += Integer.parseInt(String.valueOf(chars[i])) * WEIGHTS[i];
        }
        int remainder = sum % 11;
        if (remainder == 2) {
            remainder = 'X';
        } else {
            remainder = VALID_CHARS[remainder];
        }
        return idCard.charAt(17) == remainder;
    }

    // 验证前17位是否全是数字
    private static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    // 最小年份
    private static final int MIN_YEAR = 1900;

    // 最大年份
    private static final int MAX_YEAR = Calendar.getInstance().get(Calendar.YEAR);

    // 校验码对应的字符数组
    private static final char[] VALID_CHARS = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };

    // 每位数字对应的权重数组
    private static final int[] WEIGHTS = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

    // 验证日期是否正确
    private static boolean isValidDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.setLenient(false);
        try {
            Date date = format.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            if (year < MIN_YEAR || year > MAX_YEAR) {
                return false;
            }
            if (month < 1 || month > 12) {
                return false;
            }
            if (day < 1 || day > 31) {
                return false;
            }
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                return false;
            }
            if (month == 2) {
                if (day > 29) {
                    return false;
                }
                if ((year % 4 != 0 || year % 100 == 0) && day > 28) {
                    return false;
                }
            }
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // 地区码列表
    private static final String[] PROVINCE_CODES = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33",
            "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
            "64", "65", "71", "81", "82" };


    /**
     * 判断身份证号码的第18位字符是否为有效的校验码。
     */
    private static boolean isValidCheckCode(String numPart, char checkCode) {
        // 计算校验码
        char expected = calculateCheckCode(numPart);

        // 比较
        return checkCode == expected;
    }

    /**
     * 根据前17位数字计算校验码。
     */
    private static char calculateCheckCode(String numPart) {
        int sum = 0;
        for (int i = 0; i < numPart.length(); i++) {
            int factor = (int) Math.pow(2, 17 - i) % 11;
            int digit = Integer.parseInt(numPart.substring(i, i + 1));
            sum += factor * digit;
        }
        int remainder = sum % 11;
        char[] checkCodes = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        return checkCodes[remainder];
    }

    /**
     * 判断地区码是否有效。
     */
    private static boolean isValidProvinceCode(String provinceCode) {
        for (String code : PROVINCE_CODES) {
            if (code.equals(provinceCode)) {
                return true;
            }
        }
        return false;
    }
}
