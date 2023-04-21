package wjz.note.test.vo;

import lombok.Data;
import wjz.note.enums.CheckTypeEnums;
import wjz.note.ninterface.Check;

import javax.validation.Valid;

/***
 *
 * Author:WangJiazheng
 * Data:2023/4/19 14:00
 *
 */
@Data
public class User {
    /**
     * 姓名
     * */

    private String name;

    /**
     * 性别 man or women
     * */
    @Valid
    @Check(checkType = CheckTypeEnums.CHECK_TYPE_PARAMS ,paramValues = {"man", "woman"} ,  message = "性别格式错误")
    private String sex;

    @Valid
    @Check( checkType = CheckTypeEnums.CHECK_ID_CODE , message = "身份证不正确")
    private String idCode;

    @Valid
    @Check( checkType = CheckTypeEnums.CHECK_TYPE_EMAIL , message = "邮箱错误")
    private String email;

    @Valid
    @Check( checkType = CheckTypeEnums.CHECK_TYPE_PHONE , message = "手机号错误")
    private String phone;

}
