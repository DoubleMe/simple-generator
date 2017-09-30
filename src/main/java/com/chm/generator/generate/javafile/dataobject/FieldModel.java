package com.chm.generator.generate.javafile.dataobject;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/28 14:10.
 */
public class FieldModel {

    //java 类型包含包名
    private String JavaType;
    //java 类型
    private String simpleType;
    //字段名
    private String fieldName;
    //备注
    private String remark;

    public String getJavaType() {

        return JavaType;
    }

    public void setJavaType(String javaType) {

        JavaType = javaType;
    }

    public String getSimpleType() {

        return simpleType;
    }

    public void setSimpleType(String simpleType) {

        this.simpleType = simpleType;
    }

    public String getFieldName() {

        return fieldName;
    }

    public void setFieldName(String fieldName) {

        this.fieldName = fieldName;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
    }
}
