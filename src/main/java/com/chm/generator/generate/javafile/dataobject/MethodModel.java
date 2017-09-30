package com.chm.generator.generate.javafile.dataobject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/27 14:18.
 */
public class MethodModel {


    /** The body lines. */
    private List<String> bodyLines;

    /** The constructor. */
    private boolean constructor;

    /** The return type. */
    private ReturnType returnType;

    /** The name. */
    private String methodName;

    /** Visibility */
    private String visibility;

    /** The parameters. */
    private List<Parameter> parameters;

    /**
     * default
     */
    private boolean isInterface;

    /**
     * 备注
     */
    private String remark;

    public MethodModel(){
        bodyLines = new ArrayList<>();
        parameters = new ArrayList<>();
    }

    public List<String> getBodyLines() {

        return bodyLines;
    }

    public void setBodyLines(List<String> bodyLines) {

        this.bodyLines = bodyLines;
    }

    public boolean isConstructor() {

        return constructor;
    }

    public void setConstructor(boolean constructor) {

        this.constructor = constructor;
    }

    public ReturnType getReturnType() {

        return returnType;
    }

    public void setReturnType(ReturnType returnType) {

        this.returnType = returnType;
    }

    public String getMethodName() {

        return methodName;
    }

    public void setMethodName(String methodName) {

        this.methodName = methodName;
    }

    public List<Parameter> getParameters() {

        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {

        this.parameters = parameters;
    }

    public boolean isInterface() {

        return isInterface;
    }

    public void setInterface(boolean isInterface) {

        this.isInterface = isInterface;
    }

    public void addBodyLine(String bodyLine){

        bodyLines.add(bodyLine);
    }

    public void addParameter(Parameter parameter){
        parameters.add(parameter);
    }

    public String getVisibility() {

        return visibility;
    }

    public void setVisibility(String visibility) {

        this.visibility = visibility;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
    }
}
