package com.chm.generator.generate.javafile;

import com.chm.generator.constants.LevelConstants;
import com.chm.generator.generate.AbstractGenerator;
import com.chm.generator.generate.GeneratorConfigHolder;
import com.chm.generator.generate.javafile.dataobject.*;
import com.chm.generator.generate.enums.FileType;
import com.chm.generator.generate.enums.JavaVisibility;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/21 10:46.
 */
public abstract class AbstractJavaGenerator extends AbstractGenerator {

    public AbstractJavaGenerator(GeneratorConfigHolder configHolder) {

        super(configHolder);
        super.fileType = FileType.JAVA;
    }
    /**
     * classModel 对象转成 String
     * @param classModel
     * @return
     */
    public String classModelTOString(ClassModel classModel){

        StringBuilder sb = new StringBuilder();
        //package
        sb.append(packageStr(classModel.getPackageName()));
        newLine(sb);
        //import
        Map<String, Object> importMap = classModel.getImportMap();
        if (!importMap.isEmpty()){
            for (String key : importMap.keySet()){
                if (key.indexOf("java.lang") == -1 && key.indexOf(classModel.getPackageName()) == -1){
                    sb.append(IMPORT).append(key).append(";");
                    newLine(sb);
                }
            }
        }
        newLine(sb);
        //备注
        sb.append(remarks(classModel.getRemarks(),LevelConstants.LEVEL_CLASS));
        //class header
        sb.append(JavaVisibility.PUBLIC.getValue()).append(classModel.getClassType().getValue()).append(classModel.getClassName());
        sb.append(SPACE).append(LEFT_BRACKETS);
        newLine(sb);
        //字段
        List<FieldModel> fieldModels = classModel.getFieldModels();
        if (!fieldModels.isEmpty()){
            for (FieldModel fieldModel : fieldModels){
                sb.append(remarks(fieldModel.getRemark(),LevelConstants.LEVEL_FIELD));
                newTab(sb,LevelConstants.LEVEL_FIELD);
                sb.append(JavaVisibility.PRIVATE.getValue()).append(fieldModel.getSimpleType()).append(SPACE).append(fieldModel.getFieldName()).append(";");
                newLine(sb,LevelConstants.LEVEL_BODY);
            }
        }
//        newLine(sb);
        List<MethodModel> methodModels = classModel.getMethodModels();
        if (!methodModels.isEmpty()){
            for (MethodModel methodModel : methodModels){
                sb.append(methodTOString(methodModel));
            }
        }
        sb.append(RIGHT_BRACKETS);
        return sb.toString();
    }

    /**
     * 类 package
     *
     * @return
     */
    protected String packageStr(String packageName) {

        StringBuilder sb = new StringBuilder(PACKAGE);
        sb.append(packageName).append(";");
        return newLine(sb).toString();
    }

    /**
     * 备注
     *
     * @return
     */
    protected String remarks(String remark , int level) {

        StringBuilder sb = new StringBuilder();
        if (remark == null || "".equals(remark)){
            newLine(sb);
            return sb.toString();
        }
        newTab(sb,level);
        sb.append("/**");
        newLine(sb);
        newTab(sb,level);
        sb.append(SPACE).append("*").append(SPACE).append(remark);
        newLine(sb);
        newTab(sb,level);
        sb.append(SPACE).append("*/");
        newLine(sb);
        return sb.toString();
    }

    /**
     * methodModel 转成String
     * @param methodModel
     * @return
     */
    public String methodTOString(MethodModel methodModel){

        StringBuilder sb = new StringBuilder();
        sb.append(remarks(methodModel.getRemark(),LevelConstants.LEVEL_METHOD));
        newTab(sb, LevelConstants.LEVEL_METHOD);
        if (methodModel.getVisibility() != null){
            sb.append(methodModel.getVisibility());
        }
        ReturnType returnType = methodModel.getReturnType();
        if (returnType == null){
            sb.append("void");
        }else {
            //泛型
            sb.append(returnType.getSimpleType());
            if (returnType.isGeneric()){
                sb.append("<").append(returnType.getGenericSimpleType()).append(">");
            }
        }
        sb.append(SPACE).append(methodModel.getMethodName()).append("(");
        //拼接参数
        if (!methodModel.getParameters().isEmpty()){
            Iterator<Parameter> iterator = methodModel.getParameters().iterator();
            boolean isFirst = true;
            while (iterator.hasNext()){
                Parameter parameter = iterator.next();
                if (isFirst){
                    isFirst = false;
                }else {
                    sb.append(";");
                }
                sb.append(parameter.getSimpleType()).append(SPACE).append(parameter.getName());
            }

        }

        sb.append(")");
        //接口或者抽象类
        if (methodModel.isInterface()){
            sb.append(";");
            newLine(sb ,LevelConstants.LEVEL_BODY);
            return sb.toString();
        }

        sb.append(SPACE).append(LEFT_BRACKETS);
        newLine(sb);

        for(String bodyLine : methodModel.getBodyLines()){
            sb.append(bodyLine);
        }
        newTab(sb, LevelConstants.LEVEL_METHOD);
        sb.append(RIGHT_BRACKETS);
        newLine(sb);
        return sb.toString();
    }


}
