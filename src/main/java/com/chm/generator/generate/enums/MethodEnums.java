package com.chm.generator.generate.enums;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/29 10:17.
 */
public enum MethodEnums {
    INSERT("insert", "新增"), GETBYID("getById", "主键查询"), LIST("list", "列表查询"), UPDATE("update", "更新"), DELBYID("delById", "根据主键删除");

    private String methodName;

    private String remark;

    MethodEnums(String methodName, String remark) {

        this.methodName = methodName;
        this.remark = remark;
    }

    public String getMethodName() {

        return methodName;
    }


    public String getRemark() {

        return remark;
    }
}
