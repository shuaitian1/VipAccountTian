package com.qst.vipaccount.entity.vo;

public enum ResponseResultCode {

    SUCCESS("0", "接口请求成功"),

    ACCOUNT_OR_PASSWORD_ERROR("00100001", "账号或密码错误"),
    TOKEN_INVALID_ERROR("00100002", "token无效或已过期"),
    OPERATION_NO_POWER("00100003", "当前账户没有操作权限"),

    ACCOUNT_NAME_ILLEGAL("00110001", "账户名不合法，请输入8-20位字母、数字或下划线"),
    ACCOUNT_PASSWORD_EMPTY("00110002", "账户、密码不可为空"),
    ACCOUNT_EXIST("00110003", "当前账户名已存在"),
    ACCOUNT_NO_EXIST("00110004", "当前用户不存在"),

    DATA_HISTORY_QUERY_NO_DEVICE_ID("00200001", "设备ID不能为空"),
    DATA_HISTORY_QUERY_ILLEGAL_QUERY_TIME("00200002", "查询时间格式错误"),

    ORGANIZATION_NO_EXIST("00300000", "组织机构不存在"),
    ORGANIZATION_EXIST("00300001", "组织机构已存在"),

    DEVICE_NO_EXIST("00400000", "此设备不存在"),
    DEVICE_EXIST("00400001", "此设备已存在"),
    DEVICE_NO_EXIST_OR_NO_POWER("00400002", "设备不存在或没有此设备权限"),

    FILE_UPLOAD_FAILURE("01000001", "文件上传失败"),
    FILE_UPLOAD_FAILURE_NO_FILE("01000002", "上传失败，请选择文件"),
    FILE_UPLOAD_FAILURE_ILLEGAL_FILE("01000003", "上传失败，文件格式或大小不符合要求"),
    FILE_UPLOAD_FAILURE_ILLEGAL_CONTENT("01000003", "上传失败，文件内容不符合要求"),

    SYSTEM_ERROR("10000001", "系统错误"),
    SYSTEM_PARAM_ERROR("10000002", "参数无效"),
    SYSTEM_REQUEST_ERROR("10000003", "服务器异常，请稍后重试");

    private String code;
    private String message;

    ResponseResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
