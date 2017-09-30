package com.chm.generator.generate.javafile.dataobject;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/27 15:06.
 */
public class Parameter {

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
     * 是否泛型
     */
    private boolean isGeneric;

    /**
     * 参数名称
     */
    private String name;

    public Parameter(String javaType,String simpleType, String name) {

        this.javaType = javaType;
        this.simpleType = simpleType;
        this.name = name;
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

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
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
}
