package com.chm.generator.generate.enums;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/29 15:22.
 */
public enum SQLEnums {
    SELECT("select"), UPDATE("update"), INSERT("insert"), DELETE("delete");


    private String value;


    SQLEnums(String value) {

        this.value = value;
    }

    public String getValue() {

        return value;
    }


}
