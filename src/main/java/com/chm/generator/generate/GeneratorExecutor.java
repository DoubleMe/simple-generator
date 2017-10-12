package com.chm.generator.generate;

import com.chm.generator.configuration.JavaClientGeneratorConfiguration;
import com.chm.generator.configuration.JavaModelGeneratorConfiguration;
import com.chm.generator.configuration.SqlMapGeneratorConfiguration;
import com.chm.generator.dataobject.Column;
import com.chm.generator.dataobject.IntrospectedTable;
import com.chm.generator.dataobject.Table;
import com.chm.generator.generate.javafile.JavaClientGenerator;
import com.chm.generator.generate.javafile.JavaModelGenerator;
import com.chm.generator.generate.xmlfile.SqlMapperGenerator;
import com.chm.generator.types.JavaKeyHolder;

import java.util.List;

import static com.chm.generator.utils.StringUtility.stringHasValue;

/**
 * 生成文件执行器
 *
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/28 18:55.
 */
public class GeneratorExecutor {


    /**
     * 生成文件
     */
    public static void execute(GeneratorConfigHolder configHolder) {

        GenerateMessageHolder.clear();
        validate(configHolder);
        if (GenerateMessageHolder.hasError()){
           throw new RuntimeException(GenerateMessageHolder.getError());
        }
        if (GenerateMessageHolder.hasWarning()){
            GenerateMessageHolder.outPutWarning();
        }
        if (configHolder.getJavaModelGeneratorConfiguration() != null) {
            JavaModelGenerator javaModelGenerator = new JavaModelGenerator(configHolder);
            javaModelGenerator.generatedFile();
        }

        if (configHolder.getJavaClientGeneratorConfiguration() != null) {
            JavaClientGenerator javaClientGenerator = new JavaClientGenerator(configHolder);
            javaClientGenerator.generatedFile();
        }

        if (configHolder.getSqlMapGeneratorConfiguration() != null){
            SqlMapperGenerator sqlMapperGenerator = new SqlMapperGenerator(configHolder);
            sqlMapperGenerator.generatedFile();
        }
    }

    /**
     * 生成文件之前校验配置文件 输出异常信息
     * @param configHolder
     */
    public static void validate(GeneratorConfigHolder configHolder){

        if (configHolder.getJavaModelGeneratorConfiguration() != null) {
            JavaModelGeneratorConfiguration configuration = configHolder.getJavaModelGeneratorConfiguration();
            if (!stringHasValue(configuration.getTargetPackage())){
                GenerateMessageHolder.addError("javaModel targetPackage is blank");
                return;
            }
            if (!stringHasValue(configuration.getTargetProject())){
                GenerateMessageHolder.addError("javaModel targetProject is blank");
                return;
            }
        }

        if (configHolder.getJavaClientGeneratorConfiguration() != null) {
            JavaClientGeneratorConfiguration configuration = configHolder.getJavaClientGeneratorConfiguration();
            if (!stringHasValue(configuration.getTargetPackage())){
                GenerateMessageHolder.addError("javaClient targetPackage is blank");
                return;
            }
            if (!stringHasValue(configuration.getTargetProject())){
                GenerateMessageHolder.addError("javaClient targetProject is blank");
                return;
            }
        }

        if (configHolder.getSqlMapGeneratorConfiguration() != null) {
            SqlMapGeneratorConfiguration configuration = configHolder.getSqlMapGeneratorConfiguration();
            if (!stringHasValue(configuration.getTargetPackage())){
                GenerateMessageHolder.addError("sqlMap targetPackage is blank");
                return;
            }
            if (!stringHasValue(configuration.getTargetProject())){
                GenerateMessageHolder.addError("sqlMap targetProject is blank");
                return;
            }
        }

        List<IntrospectedTable> introspectedTable = configHolder.getIntrospectedTable();

        if (introspectedTable == null || introspectedTable.isEmpty()){
            GenerateMessageHolder.addError("no table found!");
            return;
        }

        for (IntrospectedTable table : introspectedTable){
            Table t = table.getTable();
            List<Column> columns = table.getTable().getColumns();
            if (columns == null || columns.isEmpty()){
                GenerateMessageHolder.addWarning(t.getTableName() + "no column");
            }
            for (Column column : columns){
                if (JavaKeyHolder.containKey(column.getColumnName())) {
                     GenerateMessageHolder.addWarning("the table " + t.getTableName() + " column name of " + column.getColumnName() + " is a java reserved word");
                }
            }
        }
    }
}
