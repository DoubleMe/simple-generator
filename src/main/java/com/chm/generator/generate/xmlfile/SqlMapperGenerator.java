package com.chm.generator.generate.xmlfile;

import com.chm.generator.configuration.JavaClientGeneratorConfiguration;
import com.chm.generator.configuration.JavaModelGeneratorConfiguration;
import com.chm.generator.configuration.SqlMapGeneratorConfiguration;
import com.chm.generator.constants.ElementConstants;
import com.chm.generator.constants.LevelConstants;
import com.chm.generator.dataobject.Column;
import com.chm.generator.dataobject.IntrospectedTable;
import com.chm.generator.dataobject.Table;
import com.chm.generator.generate.AbstractGenerator;
import com.chm.generator.generate.GeneratorConfigHolder;
import com.chm.generator.generate.enums.FileType;
import com.chm.generator.generate.enums.MethodEnums;
import com.chm.generator.generate.xmlfile.dataobject.Mapper;
import com.chm.generator.generate.xmlfile.dataobject.MapperElement;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/29 11:12.
 */
public class SqlMapperGenerator extends AbstractGenerator {


    public SqlMapperGenerator(GeneratorConfigHolder configHolder) {

        super(configHolder);
        fileType = FileType.XML;
    }

    @Override
    public void generatedFile() {

        SqlMapGeneratorConfiguration sqlMap = super.getSqlMapGeneratorConfiguration();
        JavaClientGeneratorConfiguration javaClient = super.getJavaClientGeneratorConfiguration();
        List<IntrospectedTable> introspectedTables = super.getIntrospectedTable();
        if (!introspectedTables.isEmpty()) {
            for (IntrospectedTable introspectedTable : introspectedTables) {
                String namespace = javaClient.getTargetPackage() + "." + getMapperName(introspectedTable, javaClient.getDomainObjectRenamingRule());
                Mapper mapper = new Mapper(namespace);

                mapper.setRemark(introspectedTable.getTable().getRemark());
                mapper.addElement(createBaseResultMap(introspectedTable));
                mapper.addElement(createSqlList(introspectedTable));
                if (introspectedTable.getConfiguration().isInsertEnabled()) {
                    mapper.addElement(createInsert(introspectedTable));
                }
                if (introspectedTable.getConfiguration().isGetByIdEnabled()) {

                    mapper.addElement(createGetById(introspectedTable));
                }
                if (introspectedTable.getConfiguration().isListEnabled()) {
                    mapper.addElement(createList(introspectedTable));
                }
                if (introspectedTable.getConfiguration().isUpdateEnabled()) {
                    mapper.addElement(createUpdate(introspectedTable));
                }
                if (introspectedTable.getConfiguration().isDelByIdEnabled()) {
                    mapper.addElement(createDelById(introspectedTable));
                }

                String source = mapperTOString(mapper);
                File directory = getDirectory(sqlMap.getTargetProject(), sqlMap.getTargetPackage());
                writeFile(directory, source, getMapperName(introspectedTable, javaClient.getDomainObjectRenamingRule()));
            }
        }

    }

    /**
     * mapper 转换为 String
     *
     * @param mapper
     * @return
     */
    private String mapperTOString(Mapper mapper) {

        StringBuilder sb = new StringBuilder();

        sb.append(ElementConstants.XML_HEADER);
        newLine(sb);
        sb.append(ElementConstants.MAPPER_DTD);
        newLine(sb);
        sb.append("<!--").append(mapper.getRemark()).append("-->");
        newLine(sb);
        sb.append("<mapper ").append("namespace=").append("\"").append(mapper.getNamespace()).append("\"").append(">");
        newLine(sb);
        if (mapper.getElements() != null && !mapper.getElements().isEmpty()) {
            List<MapperElement> elements = mapper.getElements();
            for (MapperElement element : elements) {
                newLine(sb);
                if (element.getRemark() != null) {
                    newTab(sb, LevelConstants.LEVEL_XML_ELEMENT);
                    sb.append("<!--").append(element.getRemark()).append("-->");
                    newLine(sb);
                }
                newTab(sb, LevelConstants.LEVEL_XML_ELEMENT);
                sb.append("<").append(element.getElementName()).append(SPACE);
                if (!element.getParameter().isEmpty()) {
                    sb.append(mapTOParameterStr(element.getParameter()));
                }
                sb.append(">");
                newLine(sb);
                sb.append(element.getBody());
                newLine(sb);
                newTab(sb, LevelConstants.LEVEL_XML_ELEMENT);
                sb.append("</").append(element.getElementName()).append(">");
                newLine(sb);
            }
        }
        newLine(sb);
        sb.append("</mapper>");
        return sb.toString();
    }

    /**
     * 创建resultMapper
     *
     * @param it
     * @return
     */
    private MapperElement createBaseResultMap(IntrospectedTable it) {

        MapperElement element = new MapperElement();
        element.setElementName("resultMap");
        element.setRemark("the base resultMap");
        element.addParameter("id", "BaseResultMap");
        element.addParameter("type", javaModelGeneratorConfiguration.getTargetPackage() + "." + getDomainName(it));

        List<Column> columns = it.getTable().getColumns();

        StringBuilder sb = new StringBuilder();
        Map<String, String> map = new HashMap<>();
        boolean isFirst = true;
        for (Column col : columns) {
            if (isFirst) {
                isFirst = false;
            } else {
                newLine(sb);
            }
            newTab(sb, LevelConstants.LEVEL_XML_BODY);
            String childName = it.getTable().isKey(col) ? "id " : "result ";
            sb.append("<").append(childName);
            map.put("column", col.getColumnName());
            map.put("property", getColumnName(col.getColumnName()));
            sb.append(mapTOParameterStr(map));

            sb.append("/>");
        }

        element.setBody(sb.toString());
        return element;
    }

    /**
     * 创建sql_column_list
     *
     * @param it
     * @return
     */
    private MapperElement createSqlList(IntrospectedTable it) {

        MapperElement element = new MapperElement();
        element.setElementName("sql");
        element.setRemark("the Base_Column_List");
        element.addParameter("id", "Base_Column_List");
        StringBuilder sb = new StringBuilder();
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        List<Column> columns = it.getTable().getColumns();
        boolean isFirst = true;
        for (Column col : columns) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(",").append(SPACE);
            }
            sb.append(col.getColumnName());
        }
        element.setBody(sb.toString());
        return element;
    }

    /**
     * 创建sql_insert
     *
     * @param it
     * @return
     */
    private MapperElement createInsert(IntrospectedTable it) {

        MapperElement element = new MapperElement();
        element.setElementName("insert");
        element.setRemark(MethodEnums.INSERT.getRemark());
        element.addParameter("id", MethodEnums.INSERT.getMethodName());
        element.setBody(insert(it));
        element.addParameter("parameterType", javaModelGeneratorConfiguration.getTargetPackage() + "." + getDomainName(it));
        return element;
    }

    /**
     * 创建sql_insert
     *
     * @param it
     * @return
     */
    private MapperElement createGetById(IntrospectedTable it) {

        MapperElement element = new MapperElement();
        element.setElementName("select");
        element.setRemark(MethodEnums.GETBYID.getRemark());
        element.addParameter("id", MethodEnums.GETBYID.getMethodName());
        element.addParameter("resultMap", "BaseResultMap");
        element.setBody(getById(it));
        return element;
    }

    /**
     * 创建sql_list
     *
     * @param it
     * @return
     */
    private MapperElement createList(IntrospectedTable it) {

        MapperElement element = new MapperElement();
        element.setElementName("select");
        element.setRemark(MethodEnums.LIST.getRemark());
        element.addParameter("id", MethodEnums.LIST.getMethodName());
        element.addParameter("resultMap", "BaseResultMap");
        element.setBody(list(it));
        return element;
    }

    /**
     * 创建sql_update
     *
     * @param it
     * @return
     */
    private MapperElement createUpdate(IntrospectedTable it) {

        MapperElement element = new MapperElement();
        element.setElementName("update");
        element.setRemark(MethodEnums.UPDATE.getRemark());
        element.addParameter("id", MethodEnums.UPDATE.getMethodName());
        element.setBody(update(it));
        return element;
    }

    /**
     * 创建sql_delById
     *
     * @param it
     * @return
     */
    private MapperElement createDelById(IntrospectedTable it) {

        MapperElement element = new MapperElement();
        element.setElementName("delete");
        element.setRemark(MethodEnums.DELBYID.getRemark());
        element.addParameter("id", MethodEnums.DELBYID.getMethodName());
        element.setBody(delById(it));
        return element;
    }


    /**
     * insertSqlBody
     *
     * @param it
     * @return
     */
    public String insert(IntrospectedTable it) {

        StringBuilder sb = new StringBuilder();
        Table model = it.getTable();
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("INSERT INTO ").append(it.getTable().getTableName()).append(" (");
        for (int i = 0; i < model.getColumns().size(); i++) {
            Column col = model.getColumns().get(i);
            sb.append(col.getColumnName());
            if (i != model.getColumns().size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        newLine(sb);
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("VALUES (");
        for (int i = 0; i < model.getColumns().size(); i++) {
            Column col = model.getColumns().get(i);
            sb.append("#{").append(getColumnName(col.getColumnName())).append("}");
            if (i != model.getColumns().size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * getByIdSqlBody
     *
     * @param it
     * @return
     */
    public String getById(IntrospectedTable it) {

        StringBuilder sb = new StringBuilder();
        Table model = it.getTable();
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("SELECT ");
        newLine(sb);
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("<include refid=\"Base_Column_List\"/>");
        newLine(sb);
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("FROM ").append(model.getTableName());
        newLine(sb);
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("WHERE ");

        List<Column> keys = model.getKeys();
        sb.append(ksyString(keys));
        return sb.toString();
    }

    /**
     * listSqlBody
     *
     * @param it
     * @return
     */
    public String list(IntrospectedTable it) {

        StringBuilder sb = new StringBuilder();
        Table model = it.getTable();
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("SELECT ");
        newLine(sb);
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("<include refid=\"Base_Column_List\"/>");
        newLine(sb);
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("FROM ").append(model.getTableName());

        return sb.toString();
    }

    /**
     * updateSqlBody
     *
     * @param it
     * @return
     */
    public String update(IntrospectedTable it) {

        StringBuilder sb = new StringBuilder();
        Table model = it.getTable();
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("UPDATE ").append(model.getTableName());
        newLine(sb);
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("<set>");
        for (Column col : model.getColumns()) {
            newLine(sb);
            newTab(sb, LevelConstants.LEVEL_XML_BODY + 1);
            sb.append("<if test=").append("\"").append(getColumnName(col.getColumnName())).append(" != null").append("\"").append(">");
            newLine(sb);
            newTab(sb, LevelConstants.LEVEL_XML_BODY + 2);
            sb.append(col.getColumnName()).append(" = #{").append(getColumnName(col.getColumnName())).append("}").append(",");
            newLine(sb);
            newTab(sb, LevelConstants.LEVEL_XML_BODY + 1);
            sb.append("</if>");
        }
        newLine(sb);
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("</set>");
        newLine(sb);
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("WHERE ").append(ksyString(model.getKeys()));

        return sb.toString();
    }

    /**
     * delByIdSqlBody
     *
     * @param it
     * @return
     */
    public String delById(IntrospectedTable it) {

        StringBuilder sb = new StringBuilder();
        Table model = it.getTable();
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("DELETE FROM ").append(model.getTableName());
        newLine(sb);
        newTab(sb, LevelConstants.LEVEL_XML_BODY);
        sb.append("WHERE ").append(ksyString(model.getKeys()));

        return sb.toString();
    }

    /**
     * map 参数转为string
     *
     * @param map
     * @return
     */
    private String mapTOParameterStr(Map<String, String> map) {

        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append("\"").append(entry.getValue()).append("\"").append(SPACE);
        }

        return sb.toString();
    }

    /**
     * 主键String
     *
     * @param keys
     * @return
     */
    private String ksyString(List<Column> keys) {

        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Column col : keys) {
            if (isFirst) {
                isFirst = false;
            } else {
                newLine(sb);
                newTab(sb, LevelConstants.LEVEL_XML_BODY);
                sb.append("AND ");
            }
            sb.append(col.getColumnName()).append(" = #{").append(getColumnName(col.getColumnName())).append("}");
        }

        return sb.toString();
    }
}
