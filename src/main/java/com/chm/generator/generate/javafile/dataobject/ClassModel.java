package com.chm.generator.generate.javafile.dataobject;

import com.chm.generator.generate.enums.ClassType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/28 14:10.
 */
public class ClassModel {

    //包名
    private String packageName;

    //类名
    private String className;

    //类注释
    private String remarks;

    //作者
    private String author;

    //class 类型
    private ClassType classType;

    //import 集合
    private Map<String, Object> importMap;

    //字段集合
    private List<FieldModel> fieldModels;

    //方法集合
    private List<MethodModel> methodModels;

    public ClassModel(){
        importMap = new HashMap<>();
        fieldModels = new ArrayList<>();
        methodModels = new ArrayList<>();
    }

    public String getPackageName() {

        return packageName;
    }

    public void setPackageName(String packageName) {

        this.packageName = packageName;
    }

    public String getClassName() {

        return className;
    }

    public void setClassName(String className) {

        this.className = className;
    }

    public String getRemarks() {

        return remarks;
    }

    public void setRemarks(String remarks) {

        this.remarks = remarks;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(String author) {

        this.author = author;
    }

    public Map<String, Object> getImportMap() {

        return importMap;
    }

    public void setImportMap(Map<String, Object> importMap) {

        this.importMap = importMap;
    }

    public List<FieldModel> getFieldModels() {

        return fieldModels;
    }


    public List<MethodModel> getMethodModels() {

        return methodModels;
    }


    public ClassType getClassType() {

        return classType;
    }

    public void setClassType(ClassType classType) {

        this.classType = classType;
    }

    /**
     * 添加一个 字段
     *
     * @param fieldModel
     */
    public void addFiled(FieldModel fieldModel) {

        fieldModels.add(fieldModel);
        String javaType = fieldModel.getJavaType();
        if (!importMap.containsKey(javaType)) {
            importMap.put(javaType, null);
        }
    }

    /**
     * 添加 一个方法
     * @param methodModel
     */
    public void addMethod(MethodModel methodModel) {

        methodModels.add(methodModel);

        List<Parameter> parameters = methodModel.getParameters();
        if (!parameters.isEmpty()) {
            for (Parameter parameter : parameters) {
                String javaType = parameter.getJavaType();
                if (!importMap.containsKey(javaType)) {
                    importMap.put(javaType, null);
                }
            }
        }
        if (methodModel.getReturnType() != null){
            if (!importMap.containsKey(methodModel.getReturnType().getJavaType())) {
                importMap.put(methodModel.getReturnType().getJavaType(), null);
            }
        }
    }
}
