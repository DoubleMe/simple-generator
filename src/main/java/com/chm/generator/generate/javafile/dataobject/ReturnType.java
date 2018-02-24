package com.chm.generator.generate.javafile.dataobject;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/29 10:52.
 */
public class ReturnType {

    /**
     * 参数类型
     */
    private String javaType;

    /**
     * 类型
     */
    private String simpleType;

    /**
     * 泛型
     */
    private String genericType;
    /**
     * 泛型
     */
    private String genericSimpleType;

    /**
     * 是否泛型
     */
    private boolean isGeneric;

    /**
     * 备注
     */
    private String remark;

    public ReturnType(String javaType, String simpleType) {

        this.javaType = javaType;
        this.simpleType = simpleType;
    }

    public String getJavaType() {

        return javaType;
    }

    public void setJavaType(String javaType) {

        this.javaType = javaType;
    }

    public String getSimpleType() {

        return simpleType;
    }

    public void setSimpleType(String simpleType) {

        this.simpleType = simpleType;
    }

    public String getGenericType() {

        return genericType;
    }

    public void setGenericType(String genericType) {

        this.genericType = genericType;
    }

    public boolean isGeneric() {

        return isGeneric;
    }

    public void setGeneric(boolean generic) {

        isGeneric = generic;
    }

    public String getGenericSimpleType() {

        return genericSimpleType;
    }

    public void setGenericSimpleType(String genericSimpleType) {

        this.genericSimpleType = genericSimpleType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
