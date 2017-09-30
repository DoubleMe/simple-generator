package com.chm.generator.generate;

import com.chm.generator.configuration.JavaClientGeneratorConfiguration;
import com.chm.generator.configuration.JavaModelGeneratorConfiguration;
import com.chm.generator.configuration.SqlMapGeneratorConfiguration;
import com.chm.generator.dataobject.IntrospectedTable;
import com.chm.generator.generate.javafile.JavaClientGenerator;
import com.chm.generator.generate.javafile.JavaModelGenerator;

import java.util.List;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/28 18:44.
 */
public class GeneratorConfigHolder {

    /**
     * 表配置信息
     */
    protected List<IntrospectedTable> introspectedTable;
    /**
     * sqlMap 配置信息
     */
    private SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration;

    /**
     * java model 配置信息
     */
    private JavaModelGeneratorConfiguration javaModelGeneratorConfiguration;

    /**
     * java mapper 配置信息
     */
    private JavaClientGeneratorConfiguration javaClientGeneratorConfiguration;


    public SqlMapGeneratorConfiguration getSqlMapGeneratorConfiguration() {

        return sqlMapGeneratorConfiguration;
    }

    public void setSqlMapGeneratorConfiguration(SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration) {

        this.sqlMapGeneratorConfiguration = sqlMapGeneratorConfiguration;
    }

    public JavaModelGeneratorConfiguration getJavaModelGeneratorConfiguration() {

        return javaModelGeneratorConfiguration;
    }

    public void setJavaModelGeneratorConfiguration(JavaModelGeneratorConfiguration javaModelGeneratorConfiguration) {

        this.javaModelGeneratorConfiguration = javaModelGeneratorConfiguration;
    }

    public JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration() {

        return javaClientGeneratorConfiguration;
    }

    public void setJavaClientGeneratorConfiguration(JavaClientGeneratorConfiguration javaClientGeneratorConfiguration) {

        this.javaClientGeneratorConfiguration = javaClientGeneratorConfiguration;
    }

    public List<IntrospectedTable> getIntrospectedTable() {

        return introspectedTable;
    }

    public void setIntrospectedTable(List<IntrospectedTable> introspectedTable) {

        this.introspectedTable = introspectedTable;
    }

   
}
