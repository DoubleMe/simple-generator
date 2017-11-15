package com.chm.generator.generate.xmlfile.dataobject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/29 11:25.
 */
public class MapperElement {


    private String elementName;

    private String id;

    private Map<String, String> parameter;

    private String body;

    private String remark;

    public MapperElement() {
        this.parameter = new LinkedHashMap<>();
    }

    public String getElementName() {

        return elementName;
    }

    public void setElementName(String elementName) {

        this.elementName = elementName;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public Map<String, String> getParameter() {

        return parameter;
    }

    public void addParameter(String key, String value) {

        this.parameter.put(key, value);
    }

    public String getBody() {

        return body;
    }

    public void setBody(String body) {

        this.body = body;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
    }
}
