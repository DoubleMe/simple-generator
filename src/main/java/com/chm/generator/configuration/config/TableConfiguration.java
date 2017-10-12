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
package com.chm.generator.configuration.config;

import com.chm.generator.configuration.config.properties.PropertyHolder;
import com.chm.generator.dataobject.Column;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TableConfiguration.
 *
 * @author chen-hongmin
 */
public class TableConfiguration extends PropertyHolder {

    private boolean insertEnabled = true;

    private boolean getByIdEnabled = true;

    private boolean listEnabled = true;

    private boolean updateEnabled = true;

    private boolean delByIdEnabled = true;

    private String catalog;

    private String schema;

    private String tableName;

    private String domainObjectName;

    private String alias;

    private boolean wildcardEscapingEnabled;

    private String configuredModelType;

    private boolean delimitIdentifiers;

    private boolean isAllColumnDelimitingEnabled;

    private String mapperName;

    private String sqlProviderName;

    private List<Column> ignoreColumns = new ArrayList<>();




    public boolean isInsertEnabled() {

        return insertEnabled;
    }

    public void setInsertEnabled(boolean insertEnabled) {

        this.insertEnabled = insertEnabled;
    }

    public boolean isGetByIdEnabled() {

        return getByIdEnabled;
    }

    public void setGetByIdEnabled(boolean getByIdEnabled) {

        this.getByIdEnabled = getByIdEnabled;
    }

    public boolean isListEnabled() {

        return listEnabled;
    }

    public void setListEnabled(boolean listEnabled) {

        this.listEnabled = listEnabled;
    }

    public boolean isUpdateEnabled() {

        return updateEnabled;
    }

    public void setUpdateEnabled(boolean updateEnabled) {

        this.updateEnabled = updateEnabled;
    }

    public boolean isDelByIdEnabled() {

        return delByIdEnabled;
    }

    public void setDelByIdEnabled(boolean delByIdEnabled) {

        this.delByIdEnabled = delByIdEnabled;
    }

    public String getCatalog() {

        return catalog;
    }

    public void setCatalog(String catalog) {

        this.catalog = catalog;
    }

    public String getSchema() {

        return schema;
    }

    public void setSchema(String schema) {

        this.schema = schema;
    }

    public String getTableName() {

        return tableName;
    }

    public void setTableName(String tableName) {

        this.tableName = tableName;
    }

    public String getDomainObjectName() {

        return domainObjectName;
    }

    public void setDomainObjectName(String domainObjectName) {

        this.domainObjectName = domainObjectName;
    }

    public String getAlias() {

        return alias;
    }

    public void setAlias(String alias) {

        this.alias = alias;
    }

    public boolean isWildcardEscapingEnabled() {

        return wildcardEscapingEnabled;
    }

    public void setWildcardEscapingEnabled(boolean wildcardEscapingEnabled) {

        this.wildcardEscapingEnabled = wildcardEscapingEnabled;
    }

    public String getConfiguredModelType() {

        return configuredModelType;
    }

    public void setConfiguredModelType(String configuredModelType) {

        this.configuredModelType = configuredModelType;
    }

    public boolean isDelimitIdentifiers() {

        return delimitIdentifiers;
    }

    public void setDelimitIdentifiers(boolean delimitIdentifiers) {

        this.delimitIdentifiers = delimitIdentifiers;
    }

    public boolean isAllColumnDelimitingEnabled() {

        return isAllColumnDelimitingEnabled;
    }

    public void setAllColumnDelimitingEnabled(boolean allColumnDelimitingEnabled) {

        isAllColumnDelimitingEnabled = allColumnDelimitingEnabled;
    }

    public String getMapperName() {

        return mapperName;
    }

    public void setMapperName(String mapperName) {

        this.mapperName = mapperName;
    }

    public String getSqlProviderName() {

        return sqlProviderName;
    }

    public void setSqlProviderName(String sqlProviderName) {

        this.sqlProviderName = sqlProviderName;
    }

    public List<Column> getIgnoreColumns() {

        return ignoreColumns;
    }

    public void addIgnoreColumns(Column ignoreColumn) {

        this.ignoreColumns.add(ignoreColumn);
    }

    public void setIgnoreColumns(List<Column> ignoreColumns) {

        this.ignoreColumns = ignoreColumns;
    }



}
