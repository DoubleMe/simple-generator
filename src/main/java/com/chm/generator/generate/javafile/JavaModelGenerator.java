package com.chm.generator.generate.javafile;

import com.chm.generator.configuration.JavaModelGeneratorConfiguration;
import com.chm.generator.constants.LevelConstants;
import com.chm.generator.dataobject.Column;
import com.chm.generator.dataobject.IntrospectedTable;
import com.chm.generator.generate.GeneratorConfigHolder;
import com.chm.generator.generate.javafile.dataobject.*;
import com.chm.generator.generate.enums.ClassType;
import com.chm.generator.generate.enums.JavaVisibility;
import com.chm.generator.types.JavaTypeResolver;
import com.chm.generator.utils.JavaBeansUtil;

import java.io.File;
import java.util.List;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/27 15:25.
 */
public class JavaModelGenerator extends AbstractJavaGenerator {


    public JavaModelGenerator(GeneratorConfigHolder configHolder) {

        super(configHolder);
    }


    @Override
    public void generatedFile() {

        JavaModelGeneratorConfiguration configuration = super.getJavaModelGeneratorConfiguration();
        List<IntrospectedTable> introspectedTables = super.getIntrospectedTable();
        if (!introspectedTables.isEmpty()) {
            for (IntrospectedTable introspectedTable : introspectedTables) {

                System.out.println("mybatis-generator : the table name of " + introspectedTable.getTable().getTableName() + " build successful");
                ClassModel classModel = new ClassModel();

                String targetPackage = configuration.getTargetPackage();
                String targetProject = configuration.getTargetProject();

                classModel.setPackageName(targetPackage);
                classModel.setClassName(getDomainName(introspectedTable));
                classModel.setClassType(ClassType.CLASS);
                classModel.setRemarks(introspectedTable.getTable().getRemark());
                List<Column> columns = introspectedTable.getTable().getColumns();

                if (columns != null && !columns.isEmpty()) {
                    for (Column column : columns) {
                        classModel.addFiled(newFiled(column));
                        classModel.addMethod(getGetMethod(column));
                        classModel.addMethod(getSetMethod(column));
                    }
                }

                String source = classModelTOString(classModel);
                File directory = getDirectory(targetProject, targetPackage);
                writeFile(directory, source, getDomainName(introspectedTable));
            }
        }

    }


    /**
     * 创建一个私有字段
     *
     * @param column
     * @return
     */
    private FieldModel newFiled(Column column) {

        String simpleType = JavaTypeResolver.getSimpleTypeName(column.getJdbcType());
        String jdbcType = JavaTypeResolver.getJdbcTypeName(column.getJdbcType());
        String columnName = JavaBeansUtil.getCamelCaseString(column.getColumnName(), false);
        FieldModel fieldModel = new FieldModel();
        fieldModel.setSimpleType(simpleType);
        fieldModel.setJavaType(jdbcType);
        fieldModel.setFieldName(columnName);
        fieldModel.setRemark(column.getRemark());

        return fieldModel;
    }

    /**
     * 字段get方法
     *
     * @param column
     * @return
     */
    private MethodModel getGetMethod(Column column) {

        String simpleType = JavaTypeResolver.getSimpleTypeName(column.getJdbcType());
        String jdbcType = JavaTypeResolver.getJdbcTypeName(column.getJdbcType());
        String columnName = JavaBeansUtil.getCamelCaseString(column.getColumnName(), false);
        StringBuilder sb = new StringBuilder();
        MethodModel methodModel = new MethodModel();

        methodModel.setMethodName(JavaBeansUtil.getGetterMethodName(columnName, jdbcType));
        methodModel.setReturnType(new ReturnType(jdbcType, simpleType));
        methodModel.setVisibility(JavaVisibility.PUBLIC.getValue());

        newTab(sb, LevelConstants.LEVEL_BODY);
        sb.append("return this.").append(columnName).append(";");
        newLine(sb);
        methodModel.addBodyLine(sb.toString());
        methodModel.setFiledMethod(true);

        return methodModel;
    }

    /**
     * 字段set方法
     *
     * @param column
     * @return
     */
    private MethodModel getSetMethod(Column column) {

        String simpleType = JavaTypeResolver.getSimpleTypeName(column.getJdbcType());
        String jdbcType = JavaTypeResolver.getJdbcTypeName(column.getJdbcType());
        String columnName = JavaBeansUtil.getCamelCaseString(column.getColumnName(), false);

        MethodModel methodModel = new MethodModel();
        methodModel.setMethodName(JavaBeansUtil.getSetterMethodName(columnName));
        methodModel.setVisibility(JavaVisibility.PUBLIC.getValue());

        StringBuilder sb = new StringBuilder();
        newTab(sb, LevelConstants.LEVEL_BODY);
        sb.append("this.").append(columnName).append(" = ").append(columnName).append(";");
        newLine(sb);
        methodModel.addBodyLine(sb.toString());

        Parameter parameter = new Parameter(jdbcType, simpleType, columnName);
        methodModel.addParameter(parameter);
        methodModel.setFiledMethod(true);
        return methodModel;
    }


}
