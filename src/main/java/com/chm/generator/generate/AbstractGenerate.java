package com.chm.generator.generate;

import com.chm.generator.configuration.config.TableConfiguration;
import com.chm.generator.message.MessageSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 自动生成文件 基类
 *
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/21 9:45.
 */
public abstract class AbstractGenerate {


    protected TableConfiguration tableConfiguration;

    /**
     * 工程路径
     */
    protected String targetProject;

    /**
     * 包路径
     */
    protected String targetPackage;

    /**
     * 文件编码
     */
    protected String fileEncoding = "UTF-8";

    /**
     * 生成格式化好的文件内容
     *
     * @return
     */
    protected abstract String getFileContent();


    protected void writeFile() {

        File targetFile = getDirectory();
        String source = getFileContent();
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(targetFile, false);
            osw = new OutputStreamWriter(fos, fileEncoding);
            bw = new BufferedWriter(osw);
            bw.write(source);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fos.close();
                osw.close();
                bw.close();
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
    private File getDirectory() {

        File project = new File(targetProject);
        if (!project.isDirectory()) {
            throw new RuntimeException(MessageSource.getMessage("ValidationError.4", targetProject));
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

    public TableConfiguration getTableConfiguration() {

        return tableConfiguration;
    }

    public void setTableConfiguration(TableConfiguration tableConfiguration) {

        this.tableConfiguration = tableConfiguration;
    }

    public String getTargetProject() {

        return targetProject;
    }

    public void setTargetProject(String targetProject) {

        this.targetProject = targetProject;
    }

    public String getTargetPackage() {

        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {

        this.targetPackage = targetPackage;
    }

    public String getFileEncoding() {

        return fileEncoding;
    }

    public void setFileEncoding(String fileEncoding) {

        this.fileEncoding = fileEncoding;
    }
}
