package com.chm.generator.generate.xmlfile.dataobject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/29 11:18.
 */
public class Mapper {

    //mapper namespace
    private String namespace;

    //别名
    private String alias;

    //注释
    private String remark;

    private List<MapperElement> elements;


    public Mapper(String namespace) {

        this.namespace = namespace;
    }

    public String getNamespace() {

        return namespace;
    }

    public void setNamespace(String namespace) {

        this.namespace = namespace;
    }

    public String getAlias() {

        return alias;
    }

    public void setAlias(String alias) {

        this.alias = alias;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
    }

    public List<MapperElement> getElements() {

        return elements;
    }

    public void addElement(MapperElement element) {

        if (this.elements == null){
            this.elements = new ArrayList<>();
        }
        this.elements.add(element);
    }
}
