package com.chm.generator.generate;

import com.chm.generator.configuration.JavaClientGeneratorConfiguration;
import com.chm.generator.configuration.JavaModelGeneratorConfiguration;
import com.chm.generator.configuration.SqlMapGeneratorConfiguration;
import com.chm.generator.configuration.config.RenamingRule;
import com.chm.generator.dataobject.IntrospectedTable;
import com.chm.generator.dataobject.Table;
import com.chm.generator.generate.enums.FileType;
import com.chm.generator.generate.javafile.JavaClientGenerator;
import com.chm.generator.generate.javafile.JavaModelGenerator;
import com.chm.generator.message.MessageSource;
import com.chm.generator.utils.JavaBeansUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 自动生成文件 基类
 *
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/21 9:45.
 */
public abstract class AbstractGenerator {


    //package str
    public static final String PACKAGE = "package ";

    public static final String IMPORT = "import ";
    //class
    public static final String CLASS = "class";

    //空格
    public static final String SPACE = " ";

    //tab
    public static final String TAB = "    ";

    //换行
    private static final String lineSeparator;

    //左括号
    public static final String LEFT_BRACKETS = "{";

    //右括号
    public static final String RIGHT_BRACKETS = "}";


    /**
     * 表配置信息
     */
    protected List<IntrospectedTable> introspectedTable;
    /**
     * sqlMap 配置信息
     */
    protected SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration;

    /**
     * java model 配置信息
     */
    protected JavaModelGeneratorConfiguration javaModelGeneratorConfiguration;

    /**
     * java mapper 配置信息
     */
    protected JavaClientGeneratorConfiguration javaClientGeneratorConfiguration;

    public  AbstractGenerator(GeneratorConfigHolder configHolder){

        this.introspectedTable = configHolder.getIntrospectedTable();
        this.sqlMapGeneratorConfiguration = configHolder.getSqlMapGeneratorConfiguration();
        this.javaModelGeneratorConfiguration = configHolder.getJavaModelGeneratorConfiguration();
        this.javaClientGeneratorConfiguration = configHolder.getJavaClientGeneratorConfiguration();
    }


    /**
     * 文件编码
     */
    protected String fileEncoding = "UTF-8";

    /**
     * 文件类型
     */
    protected FileType fileType;

    static {
        String ls = System.getProperty("line.separator"); //$NON-NLS-1$
        if (ls == null) {
            ls = "\n"; //$NON-NLS-1$
        }
        lineSeparator = ls;
    }

    public List<IntrospectedTable> getIntrospectedTable() {

        return introspectedTable;
    }

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

    public void setIntrospectedTable(List<IntrospectedTable> introspectedTable) {

        this.introspectedTable = introspectedTable;
    }

    public String getFileEncoding() {

        return fileEncoding;
    }

    public void setFileEncoding(String fileEncoding) {

        this.fileEncoding = fileEncoding;
    }

    public abstract void generatedFile();

    /**
     * 新增一行
     *
     * @param sb
     * @param level
     * @return
     */
    protected StringBuilder newTab(StringBuilder sb, int level) {

        for (int i = 0; i < level; i++) {
            sb.append(TAB);
        }
        return sb;
    }


    /**
     * 新增一行
     *
     * @param sb
     * @return
     */
    protected StringBuilder newLine(StringBuilder sb) {

        sb.append(lineSeparator);
        return sb;
    }

    /**
     * 新增一行
     *
     * @param sb
     * @return
     */
    protected StringBuilder newLine(StringBuilder sb, int total) {

        for (int i = 0; i < total; i++) {
            sb.append(lineSeparator);
        }
        return sb;
    }

    /**
     * 输出文件
     * 没有文件夹创建
     * 文件已存在则覆盖
     */
    public void writeFile(File targetFile, String source, String fileName) {


        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        String file = fileName + "." + fileType.getValue();
        try {
            File directory = new File(targetFile, file);
            System.out.println("file:" + file + "创建成功");
            fos = new FileOutputStream(directory, false);
            osw = new OutputStreamWriter(fos, fileEncoding);
            bw = new BufferedWriter(osw);
            bw.write(source);
            bw.flush();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fos.close();
                osw.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * 获取文件路径
     *
     * @return
     */
    protected File getDirectory(String targetProject, String targetPackage) {

        File project = new File(targetProject);
        if (!project.isDirectory()) {
            project.mkdir();
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(targetPackage, "."); //$NON-NLS-1$
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            sb.append(File.separatorChar);
        }

        File directory = new File(project, sb.toString());
        if (!directory.isDirectory()) {
            boolean rc = directory.mkdirs();
            if (!rc) {
                throw new RuntimeException(MessageSource.getMessage("ValidationError.5", directory.getAbsolutePath()));
            }
        }

        return directory;
    }

    /**
     * get domain name
     *
     * @return
     */
    protected String getDomainName(IntrospectedTable table) {

        String domainObjectName = table.getConfiguration().getDomainObjectName();

        if (domainObjectName == null || "".equals(domainObjectName)) {
            String tableName = table.getTable().getTableName();
            RenamingRule domainObjectRenamingRule = table.getConfiguration().getDomainObjectRenamingRule();
            if (domainObjectRenamingRule != null){
                tableName = domainObjectRenamingRule.rename(tableName);
            }
            domainObjectName = JavaBeansUtil.getCamelCaseString(tableName, true);
        }


        return domainObjectName;
    }

    /**
     * get domain name
     *
     * @return
     */
    protected String getMapperName(IntrospectedTable table) {

        String mapperName = table.getConfiguration().getMapperName();

        if (mapperName == null || "".equals(mapperName)) {
            String tableName = table.getTable().getTableName();
            mapperName = JavaBeansUtil.getCamelCaseString(tableName, true) + "Mapper";

        }
        return mapperName;
    }

    /**
     * get domain name
     *
     * @return
     */
    protected String getColumnName(String columnName) {


//        RenamingRule domainObjectRenamingRule = table.getConfiguration().getColumnRenamingRule();
//        if (domainObjectRenamingRule != null){
//            columnName = domainObjectRenamingRule.rename(columnName);
//        }

        return JavaBeansUtil.getCamelCaseString(columnName, false);
    }


}
