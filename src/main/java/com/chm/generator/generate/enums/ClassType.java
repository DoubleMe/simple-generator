package com.chm.generator.generate.enums;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/28 14:28.
 */
public enum ClassType {

    CLASS("class "),ENUM("enum "),INTERFACE("interface "),ABSTRACT_CLASS("abstract class ");

    private String value;

    private ClassType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
