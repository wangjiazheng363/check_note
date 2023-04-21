v1.0
简单的字段校验注解 @check
参数：
    CheckTypeEnums checkType 校验类型 
    String[] paramValues 合法化参数校验值  {"man", "woman"} 注解会根据 paramValues 中的数据进行判断 传入值不在 paramValues 内的会返回false
    String message  返回消息
CheckTypeEnums
    CHECK_ID_CODE 身份证校验
    CHECK_TYPE_EMAIL 邮箱校验
    CHECK_TYPE_PHONE 手机号格式校验
    CHECK_TYPE_PARAMS 使用paramValues 参数对比校验

v1.1
项目增加了redis操作工具类

v2.0
项目更改为utilproject
此项目记录本人工作中自己开发的一些小工具及收集的工具

