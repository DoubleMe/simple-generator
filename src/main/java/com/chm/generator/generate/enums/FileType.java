package com.chm.generator.generate.enums;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/27 16:02.
 */
public enum FileType {

    JAVA("java"),XML("xml");
    private String value;

    private FileType(String value){

        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
