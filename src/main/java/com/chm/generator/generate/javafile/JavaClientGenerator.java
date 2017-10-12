package com.chm.generator.generate.javafile;

import com.chm.generator.configuration.JavaClientGeneratorConfiguration;
import com.chm.generator.configuration.JavaModelGeneratorConfiguration;
import com.chm.generator.configuration.config.TableConfiguration;
import com.chm.generator.dataobject.Column;
import com.chm.generator.dataobject.IntrospectedTable;
import com.chm.generator.dataobject.Table;
import com.chm.generator.generate.GeneratorConfigHolder;
import com.chm.generator.generate.enums.ClassType;
import com.chm.generator.generate.enums.MethodEnums;
import com.chm.generator.generate.javafile.dataobject.ClassModel;
import com.chm.generator.generate.javafile.dataobject.MethodModel;
import com.chm.generator.generate.javafile.dataobject.Parameter;
import com.chm.generator.generate.javafile.dataobject.ReturnType;
import com.chm.generator.types.JavaTypeResolver;
import com.chm.generator.utils.JavaBeansUtil;

import java.io.File;
import java.util.List;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/28 17:49.
 */
public class JavaClientGenerator extends AbstractJavaGenerator {

    public JavaClientGenerator(GeneratorConfigHolder configHolder) {
        super(configHolder);
    }

    @Override
    public void generatedFile() {


        JavaClientGeneratorConfiguration configuration = super.getJavaClientGeneratorConfiguration();

        List<IntrospectedTable> introspectedTables = super.getIntrospectedTable();
        if (!introspectedTables.isEmpty()) {
            for (IntrospectedTable introspectedTable : introspectedTables) {
                ClassModel classModel = new ClassModel();

                String targetPackage = configuration.getTargetPackage();
                String targetProject = configuration.getTargetProject();

                classModel.setPackageName(targetPackage);
                classModel.setClassName(getMapperName(introspectedTable, configuration.getDomainObjectRenamingRule()));
                classModel.setClassType(ClassType.INTERFACE);
                classModel.setRemarks(introspectedTable.getTable().getRemark());
                addMethod(classModel, introspectedTable);
                String source = classModelTOString(classModel);
                File directory = getDirectory(targetProject, targetPackage);
                writeFile(directory, source, getMapperName(introspectedTable, configuration.getDomainObjectRenamingRule()));
            }
        }
    }

    private void addMethod(ClassModel classModel, IntrospectedTable it) {

        TableConfiguration tc = it.getConfiguration();
        Table table = it.getTable();
        JavaModelGeneratorConfiguration javaModel = super.getJavaModelGeneratorConfiguration();
        String dsName = getDomainName(it);
        String domainType = javaModel.getTargetPackage() + "." + dsName;
        //新增
        if (tc.isInsertEnabled()) {
            MethodModel methodModel = new MethodModel();
            methodModel.setInterface(true);
            methodModel.setMethodName(MethodEnums.INSERT.getMethodName());
            methodModel.setReturnType(new ReturnType(Integer.class.getName(), "int"));
            methodModel.setRemark(MethodEnums.INSERT.getRemark());
            Parameter parameter = new Parameter(domainType, dsName, JavaBeansUtil.firstToLowerCase(dsName));
            methodModel.addParameter(parameter);
            classModel.addMethod(methodModel);
        }

        //修改
        if (tc.isUpdateEnabled()) {
            MethodModel methodModel = new MethodModel();
            methodModel.setInterface(true);
            methodModel.setMethodName(MethodEnums.UPDATE.getMethodName());
            methodModel.setReturnType(new ReturnType(Integer.class.getName(), "int"));
            methodModel.setRemark(MethodEnums.UPDATE.getRemark());
            Parameter parameter = new Parameter(domainType, dsName, JavaBeansUtil.firstToLowerCase(dsName));
            methodModel.addParameter(parameter);
            classModel.addMethod(methodModel);
        }
        //主键查询
        if (tc.isGetByIdEnabled() && !table.getKeys().isEmpty()) {
            MethodModel methodModel = new MethodModel();
            methodModel.setInterface(true);
            methodModel.setMethodName(MethodEnums.GETBYID.getMethodName());
            methodModel.setReturnType(new ReturnType(domainType, dsName));
            methodModel.setRemark(MethodEnums.GETBYID.getRemark());
            for (Column column : table.getKeys()) {

                methodModel.addParameter(keyParameter(column));
            }
            classModel.addMethod(methodModel);
        }

        //主键删除
        if (tc.isDelByIdEnabled() && !table.getKeys().isEmpty()) {
            MethodModel methodModel = new MethodModel();
            methodModel.setInterface(true);
            methodModel.setMethodName(MethodEnums.DELBYID.getMethodName());
            methodModel.setReturnType(new ReturnType(Integer.class.getName(), "int"));
            methodModel.setRemark(MethodEnums.DELBYID.getRemark());
            for (Column column : table.getKeys()) {
                methodModel.addParameter(keyParameter(column));
            }
            classModel.addMethod(methodModel);
        }

        //列表查询
        if (tc.isListEnabled()) {
            MethodModel methodModel = new MethodModel();
            methodModel.setInterface(true);
            methodModel.setMethodName(MethodEnums.LIST.getMethodName());
            ReturnType returnType = new ReturnType(List.class.getName(), List.class.getSimpleName());
            returnType.setGeneric(true);
            returnType.setGenericType(domainType);
            returnType.setGenericSimpleType(dsName);
            methodModel.setReturnType(returnType);
            methodModel.setRemark(MethodEnums.LIST.getRemark());
//            Parameter parameter = new Parameter(domainType, dsName, JavaBeansUtil.firstToLowerCase(dsName));
//            methodModel.addParameter(parameter);
            classModel.addMethod(methodModel);
        }
    }


    /**
     * 主键参数
     *
     * @param column
     * @return
     */
    private Parameter keyParameter(Column column) {
        String javaType = JavaTypeResolver.getJdbcTypeName(column.getJdbcType());
        String simpleType = JavaTypeResolver.getSimpleTypeName(column.getJdbcType());
        Parameter parameter = new Parameter(javaType, simpleType, column.getColumnName());
        return parameter;
    }
}
