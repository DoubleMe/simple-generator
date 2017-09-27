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

/**
 * The Class TableConfiguration.
 *
 * @author chen-hongmin
 */
public class TableConfiguration extends PropertyHolder {

    private boolean insertStatementEnabled;

    private boolean selectByPrimaryKeyStatementEnabled;

    private boolean selectByExampleStatementEnabled;

    private boolean updateByPrimaryKeyStatementEnabled;

    private boolean deleteByPrimaryKeyStatementEnabled;

    private boolean deleteByExampleStatementEnabled;

    private boolean countByExampleStatementEnabled;

    private boolean updateByExampleStatementEnabled;

    private String selectByPrimaryKeyQueryId;

    private String selectByExampleQueryId;

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


    public boolean isInsertStatementEnabled() {

        return insertStatementEnabled;
    }

    public void setInsertStatementEnabled(boolean insertStatementEnabled) {

        this.insertStatementEnabled = insertStatementEnabled;
    }

    public boolean isSelectByPrimaryKeyStatementEnabled() {

        return selectByPrimaryKeyStatementEnabled;
    }

    public void setSelectByPrimaryKeyStatementEnabled(boolean selectByPrimaryKeyStatementEnabled) {

        this.selectByPrimaryKeyStatementEnabled = selectByPrimaryKeyStatementEnabled;
    }

    public boolean isSelectByExampleStatementEnabled() {

        return selectByExampleStatementEnabled;
    }

    public void setSelectByExampleStatementEnabled(boolean selectByExampleStatementEnabled) {

        this.selectByExampleStatementEnabled = selectByExampleStatementEnabled;
    }

    public boolean isUpdateByPrimaryKeyStatementEnabled() {

        return updateByPrimaryKeyStatementEnabled;
    }

    public void setUpdateByPrimaryKeyStatementEnabled(boolean updateByPrimaryKeyStatementEnabled) {

        this.updateByPrimaryKeyStatementEnabled = updateByPrimaryKeyStatementEnabled;
    }

    public boolean isDeleteByPrimaryKeyStatementEnabled() {

        return deleteByPrimaryKeyStatementEnabled;
    }

    public void setDeleteByPrimaryKeyStatementEnabled(boolean deleteByPrimaryKeyStatementEnabled) {

        this.deleteByPrimaryKeyStatementEnabled = deleteByPrimaryKeyStatementEnabled;
    }

    public boolean isDeleteByExampleStatementEnabled() {

        return deleteByExampleStatementEnabled;
    }

    public void setDeleteByExampleStatementEnabled(boolean deleteByExampleStatementEnabled) {

        this.deleteByExampleStatementEnabled = deleteByExampleStatementEnabled;
    }

    public boolean isCountByExampleStatementEnabled() {

        return countByExampleStatementEnabled;
    }

    public void setCountByExampleStatementEnabled(boolean countByExampleStatementEnabled) {

        this.countByExampleStatementEnabled = countByExampleStatementEnabled;
    }

    public boolean isUpdateByExampleStatementEnabled() {

        return updateByExampleStatementEnabled;
    }

    public void setUpdateByExampleStatementEnabled(boolean updateByExampleStatementEnabled) {

        this.updateByExampleStatementEnabled = updateByExampleStatementEnabled;
    }

    public String getSelectByPrimaryKeyQueryId() {

        return selectByPrimaryKeyQueryId;
    }

    public void setSelectByPrimaryKeyQueryId(String selectByPrimaryKeyQueryId) {

        this.selectByPrimaryKeyQueryId = selectByPrimaryKeyQueryId;
    }

    public String getSelectByExampleQueryId() {

        return selectByExampleQueryId;
    }

    public void setSelectByExampleQueryId(String selectByExampleQueryId) {

        this.selectByExampleQueryId = selectByExampleQueryId;
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
}
