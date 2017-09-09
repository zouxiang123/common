package com.xtt.common.util.excel;

public class BadInputException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;
    public static final String KEY_REQUIRED = "“%s”的值不能为空";
    public static final String KEY_DATA_TOO_LONG = "“%s”超出最大长度";

    public static final String KEY_SHEET_NOT_FOUND = "找不到对应的sheet页“%s”";
    public static final String KEY_UNRECOGNIZED_HEADER = "列名“%s”无效";
    public static final String KEY_INVALID_SEX = "“%s”不是有效性别";
    public static final String KEY_INVALID_ID_NUMBER = "“%s”不是有效的身份证号码";
    public static final String KEY_INVALID_BRITHDAY = "“%s”不是有效的出生日期";
    public static final String KEY_INVALID_MOBILE = "“%s”不是有效的手机号";
    public static final String KEY_INVALID_POSITION = "“%s”不是有效的职称";
    public static final String KEY_INVALID_ROLE = "“%s”不是有效的角色名";
    public static final String KEY_INVALID_VALUE = "“%s”不是有效的值";
    public static final String KEY_INVALID_INTEGER = "“%s”不是有效的整数";
    public static final String KEY_REPEAT_NUMBER = "“%s”手机号不能重复";

    private String errorValue;

    public BadInputException(String errorKey, String sheetName) {
        this.errorValue = String.format(errorKey, sheetName);
    }

    public String getErrorValue() {
        return errorValue;
    }

}