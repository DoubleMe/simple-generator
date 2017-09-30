package com.chm.generator.generate;

import com.chm.generator.generate.javafile.JavaClientGenerator;
import com.chm.generator.generate.javafile.JavaModelGenerator;
import com.chm.generator.generate.xmlfile.SqlMapperGenerator;

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
}
