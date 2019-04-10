/**
 * Copyright 2006-2017 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.chm.generator.configuration;

import com.chm.generator.configuration.config.Context;
import com.chm.generator.configuration.config.RenamingRule;
import com.chm.generator.configuration.config.TableConfiguration;
import com.chm.generator.configuration.config.properties.PropertyHolder;
import com.chm.generator.constants.ElementConstants;
import com.chm.generator.dataobject.Column;
import com.chm.generator.exception.XMLParserException;
import com.chm.generator.utils.ClassLoaderHolder;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import static com.chm.generator.utils.StringUtility.isTrue;
import static com.chm.generator.utils.StringUtility.isFalse;
import static com.chm.generator.utils.StringUtility.stringHasValue;

/**
 * 配置文件解析器
 *
 * @author chen-hongmin
 * @since 2017-09-16
 */
public class ConfigurationParser {


    /**
     * 解析配置文件
     *
     * @param stream 配置文件输入流
     * @return Configuration
     * @throws IOException
     * @throws XMLParserException
     */
    public Configuration parseConfiguration(InputStream stream) throws IOException, XMLParserException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();


            Document document = null;
            try {
                document = builder.parse(stream);
            } catch (SAXParseException e) {
                throw new XMLParserException(new ArrayList<>());
            } catch (SAXException e) {
                e.printStackTrace();
            }
            Element rootNode = document.getDocumentElement();
            return parseMyBatisGeneratorConfiguration(rootNode);
        } catch (ParserConfigurationException e) {
            throw new XMLParserException(new ArrayList<>());
        }

    }

    /**
     * 解析配置文件
     *
     * @param rootNode
     * @return
     * @throws XMLParserException
     */
    private Configuration parseMyBatisGeneratorConfiguration(Element rootNode) throws XMLParserException {

        Configuration configuration = new Configuration();

        NodeList nodeList = rootNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);

            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            switch (childNode.getNodeName()) {
                case ElementConstants.ELEMENT_PROPERTIES:
                    parseProperties(configuration, childNode);
                    break;
                case ElementConstants.ELEMENT_CLASS_PATHENTRY:
                    parseClassPathEntry(configuration, childNode);
                    break;
                case ElementConstants.ELEMENT_CONTEXT:
                    parseContext(configuration, childNode);
                    break;
            }

        }

        return configuration;
    }

    /**
     * 解析 properties
     *
     * @param configuration
     * @param node
     * @throws XMLParserException
     */
    protected void parseProperties(Configuration configuration, Node node) throws XMLParserException {

        Properties attributes = parseAttributes(node);
        String resource = attributes.getProperty("resource"); //$NON-NLS-1$
        String url = attributes.getProperty("url"); //$NON-NLS-1$
    }

    /**
     * 解析context
     *
     * @param configuration
     * @param node
     */
    private void parseContext(Configuration configuration, Node node) {

        Properties attributes = parseAttributes(node);
        String id = attributes.getProperty("id"); //$NON-NLS-1$

        Context context = new Context();
        context.setId(id);
        configuration.addContext(context);

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);

            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseProperty(context, childNode);
            } else if ("jdbcConnection".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseJdbcConnection(context, childNode);
            } else if ("javaModelGenerator".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseJavaModelGenerator(context, childNode);
            } else if ("sqlMapGenerator".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseSqlMapGenerator(context, childNode);
            } else if ("javaClientGenerator".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseJavaClientGenerator(context, childNode);
            } else if ("table".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseTable(context, childNode);
            }
        }
    }

    /**
     * 解析 sqlMap 信息
     *
     * @param context
     * @param node
     */
    protected void parseSqlMapGenerator(Context context, Node node) {

        SqlMapGeneratorConfiguration smc = new SqlMapGeneratorConfiguration();

        context.setSqlMapGeneratorConfiguration(smc);

        Properties attributes = parseAttributes(node);
        String targetPackage = attributes.getProperty("targetPackage"); //$NON-NLS-1$
        String targetProject = attributes.getProperty("targetProject"); //$NON-NLS-1$

        smc.setTargetPackage(targetPackage);
        smc.setTargetProject(targetProject);

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseProperty(smc, childNode);
            } else if ("domainObjectRenamingRule".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseDomainObjectRenamingRule(smc, childNode);
            }

        }
    }

    /**
     * 解析数据库table 信息
     *
     * @param context
     * @param node
     */
    protected void parseTable(Context context, Node node) {

        TableConfiguration tc = new TableConfiguration();
        context.addTableConfiguration(tc);

        Properties attributes = parseAttributes(node);

        String catalog = attributes.getProperty("catalog"); //$NON-NLS-1$
        if (stringHasValue(catalog)) {
            tc.setCatalog(catalog);
        }

        String schema = attributes.getProperty("schema"); //$NON-NLS-1$
        if (stringHasValue(schema)) {
            tc.setSchema(schema);
        }

        String tableName = attributes.getProperty("tableName"); //$NON-NLS-1$
        if (stringHasValue(tableName)) {
            tc.setTableName(tableName);
        }

        String domainObjectName = attributes.getProperty("domainObjectName"); //$NON-NLS-1$
        if (stringHasValue(domainObjectName)) {
            tc.setDomainObjectName(domainObjectName);
        }

        String alias = attributes.getProperty("alias"); //$NON-NLS-1$
        if (stringHasValue(alias)) {
            tc.setAlias(alias);
        }

        String enableInsert = attributes.getProperty("insert"); //$NON-NLS-1$
        if (stringHasValue(enableInsert)) {
            tc.setInsertEnabled(!isFalse(enableInsert));
        }

        String enableGetById = attributes.getProperty("getById"); //$NON-NLS-1$
        if (stringHasValue(enableGetById)) {
            tc.setGetByIdEnabled(!isFalse(enableGetById));
        }

        String enableList = attributes.getProperty("list"); //$NON-NLS-1$
        if (stringHasValue(enableList)) {
            tc.setListEnabled(!isFalse(enableList));
        }

        String enableUpdate = attributes.getProperty("update"); //$NON-NLS-1$
        if (stringHasValue(enableUpdate)) {
            tc.setUpdateEnabled(!isFalse(enableUpdate));
        }

        String enableDelById = attributes.getProperty("delById"); //$NON-NLS-1$
        if (stringHasValue(enableDelById)) {
            tc.setDelByIdEnabled(!isFalse(enableDelById));
        }

        String modelType = attributes.getProperty("modelType"); //$NON-NLS-1$
        if (stringHasValue(modelType)) {
            tc.setConfiguredModelType(modelType);
        }

        String escapeWildcards = attributes.getProperty("escapeWildcards"); //$NON-NLS-1$
        if (stringHasValue(escapeWildcards)) {
            tc.setWildcardEscapingEnabled(isTrue(escapeWildcards));
        }

        String delimitIdentifiers = attributes.getProperty("delimitIdentifiers"); //$NON-NLS-1$
        if (stringHasValue(delimitIdentifiers)) {
            tc.setDelimitIdentifiers(isTrue(delimitIdentifiers));
        }

        String delimitAllColumns = attributes.getProperty("delimitAllColumns"); //$NON-NLS-1$
        if (stringHasValue(delimitAllColumns)) {
            tc.setAllColumnDelimitingEnabled(isTrue(delimitAllColumns));
        }

        String mapperName = attributes.getProperty("mapperName"); //$NON-NLS-1$
        if (stringHasValue(mapperName)) {
            tc.setMapperName(mapperName);
        }

        String sqlProviderName = attributes.getProperty("sqlProviderName"); //$NON-NLS-1$
        if (stringHasValue(sqlProviderName)) {
            tc.setSqlProviderName(sqlProviderName);
        }

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseProperty(tc, childNode);
            } else if ("ignoreColumn".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseIgnoreColumn(tc, childNode);
            } else if ("domainObjectRenamingRule".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseDomainObjectRenamingRule(tc, childNode);
            } else if ("columnRenamingRule".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseColumnRenamingRule(tc, childNode);
            }
        }
    }

    /**
     * 解析java 模型
     *
     * @param context
     * @param node
     */
    protected void parseJavaModelGenerator(Context context, Node node) {

        JavaModelGeneratorConfiguration jmc = new JavaModelGeneratorConfiguration();

        context.setJavaModelGeneratorConfiguration(jmc);

        Properties attributes = parseAttributes(node);
        String targetPackage = attributes.getProperty("targetPackage"); //$NON-NLS-1$
        String targetProject = attributes.getProperty("targetProject"); //$NON-NLS-1$

        jmc.setTargetPackage(targetPackage);
        jmc.setTargetProject(targetProject);

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseProperty(jmc, childNode);
            } else if ("domainObjectRenamingRule".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseDomainObjectRenamingRule(jmc, childNode);
            }
        }
    }

    /**
     * 解析 javafile 客户端接口信息
     *
     * @param context
     * @param node
     */
    private void parseJavaClientGenerator(Context context, Node node) {

        JavaClientGeneratorConfiguration jcc = new JavaClientGeneratorConfiguration();

        context.setJavaClientGeneratorConfiguration(jcc);

        Properties attributes = parseAttributes(node);
        String targetPackage = attributes.getProperty("targetPackage"); //$NON-NLS-1$
        String targetProject = attributes.getProperty("targetProject"); //$NON-NLS-1$


        jcc.setTargetPackage(targetPackage);
        jcc.setTargetProject(targetProject);

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if ("property".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseProperty(jcc, childNode);
            } else if ("domainObjectRenamingRule".equals(childNode.getNodeName())) { //$NON-NLS-1$
                parseDomainObjectRenamingRule(jcc, childNode);
            }
        }
    }

    /**
     * 解析 数据库连接信息
     *
     * @param context
     * @param node
     */
    protected void parseJdbcConnection(Context context, Node node) {

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();

        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        Properties attributes = parseAttributes(node);
        String driverClass = attributes.getProperty("driverClass"); //$NON-NLS-1$
        String connectionURL = attributes.getProperty("connectionURL"); //$NON-NLS-1$

        jdbcConnectionConfiguration.setDriverClass(driverClass);
        jdbcConnectionConfiguration.setConnectionURL(connectionURL);

        String userId = attributes.getProperty("userId"); //$NON-NLS-1$
        if (stringHasValue(userId)) {
            jdbcConnectionConfiguration.setUserId(userId);
        }

        String password = attributes.getProperty("password"); //$NON-NLS-1$
        if (stringHasValue(password)) {
            jdbcConnectionConfiguration.setPassword(password);
        }

        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            parseProperty(jdbcConnectionConfiguration, childNode);
        }
    }

    /**
     * 解析classPathEntry
     *
     * @param configuration
     * @param node
     */
    protected void parseClassPathEntry(Configuration configuration, Node node) {

        Properties attributes = parseAttributes(node);
        String location = attributes.getProperty("location");
        ClassLoaderHolder.addExternalClassLoader(location);
        configuration.setClassPathEntries(attributes.getProperty("location")); //$NON-NLS-1$
    }

    /**
     * 解析Property
     *
     * @param propertyHolder
     * @param node
     */
    protected void parseProperty(PropertyHolder propertyHolder, Node node) {

        if (node.getNodeType() != Node.ELEMENT_NODE) {
            return;
        }
        if (!"property".equals(node.getNodeName())) { //$NON-NLS-1$
            return;
        }
        Properties attributes = parseAttributes(node);

        String name = attributes.getProperty("name"); //$NON-NLS-1$
        String value = attributes.getProperty("value"); //$NON-NLS-1$

        propertyHolder.addProperty(name, value);
    }

    /**
     * node to Properties
     *
     * @param node
     * @return
     */
    protected Properties parseAttributes(Node node) {

        Properties attributes = new Properties();
        NamedNodeMap nnm = node.getAttributes();
        for (int i = 0; i < nnm.getLength(); i++) {
            Node attribute = nnm.item(i);
            String value = attribute.getNodeValue();
            attributes.put(attribute.getNodeName(), value);
        }

        return attributes;
    }

    private void parseIgnoreColumn(TableConfiguration tc, Node node) {

        Properties attributes = parseAttributes(node);
        String column = attributes.getProperty("column"); //$NON-NLS-1$
        Column ic = new Column();

        ic.setColumnName(column);

        tc.addIgnoreColumns(ic);
    }

    private void parseDomainObjectRenamingRule(PropertyHolder tc, Node node) {

        Properties attributes = parseAttributes(node);
        String searchString = attributes.getProperty("searchString"); //$NON-NLS-1$
        String replaceString = attributes.getProperty("replaceString"); //$NON-NLS-1$
        String prefix = attributes.getProperty("prefix"); //$NON-NLS-1$
        String suffix = attributes.getProperty("suffix"); //$NON-NLS-1$

        RenamingRule dorr = new RenamingRule();

        dorr.setSearchString(searchString);

        if (stringHasValue(replaceString)) {
            dorr.setReplaceString(replaceString);
        }
        if (stringHasValue(prefix)) {
            dorr.setPrefix(prefix);
        }
        if (stringHasValue(suffix)) {
            dorr.setSuffix(suffix);
        }

        tc.setDomainObjectRenamingRule(dorr);
    }

    private void parseColumnRenamingRule(PropertyHolder holder, Node node) {

        Properties attributes = parseAttributes(node);
        String searchString = attributes.getProperty("searchString"); //$NON-NLS-1$
        String replaceString = attributes.getProperty("replaceString"); //$NON-NLS-1$

        RenamingRule dorr = new RenamingRule();

        dorr.setSearchString(searchString);

        if (stringHasValue(replaceString)) {
            dorr.setReplaceString(replaceString);
        }

        holder.setColumnRenamingRule(dorr);
    }


}
