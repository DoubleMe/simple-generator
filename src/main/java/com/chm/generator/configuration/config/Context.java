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

import com.chm.generator.configuration.JDBCConnectionConfiguration;
import com.chm.generator.configuration.JavaClientGeneratorConfiguration;
import com.chm.generator.configuration.JavaModelGeneratorConfiguration;
import com.chm.generator.configuration.SqlMapGeneratorConfiguration;
import com.chm.generator.configuration.config.properties.PropertyHolder;

import java.util.ArrayList;

/**
 * The Class Context.
 *
 * @author chen-hongmin
 */
public class Context extends PropertyHolder {

    private String id;

    private JDBCConnectionConfiguration jdbcConnectionConfiguration;

    private SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration;

    private JavaModelGeneratorConfiguration javaModelGeneratorConfiguration;

    private JavaClientGeneratorConfiguration javaClientGeneratorConfiguration;

    private ArrayList<TableConfiguration> tableConfigurations;

    private String beginningDelimiter = "\""; //$NON-NLS-1$

    private String endingDelimiter = "\""; //$NON-NLS-1$


    public Context() {

        tableConfigurations = new ArrayList<>();
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public JDBCConnectionConfiguration getJdbcConnectionConfiguration() {

        return jdbcConnectionConfiguration;
    }

    public void setJdbcConnectionConfiguration(JDBCConnectionConfiguration jdbcConnectionConfiguration) {

        this.jdbcConnectionConfiguration = jdbcConnectionConfiguration;
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

    public ArrayList<TableConfiguration> getTableConfigurations() {

        return tableConfigurations;
    }

    public void setTableConfigurations(ArrayList<TableConfiguration> tableConfigurations) {

        this.tableConfigurations = tableConfigurations;
    }

    public String getBeginningDelimiter() {

        return beginningDelimiter;
    }

    public void setBeginningDelimiter(String beginningDelimiter) {

        this.beginningDelimiter = beginningDelimiter;
    }

    public String getEndingDelimiter() {

        return endingDelimiter;
    }

    public void setEndingDelimiter(String endingDelimiter) {

        this.endingDelimiter = endingDelimiter;
    }

    public void addTableConfiguration(TableConfiguration tableConfiguration) {

        tableConfigurations.add(tableConfiguration);
    }
}
