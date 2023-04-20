v1.0
# check_note
简单的字段校验注解
可以校验身份证号合法性
电话格式
邮箱格式

使用demo 
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
