package com.chm.generator.dataobject;

import com.chm.generator.configuration.config.TableConfiguration;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/28 16:04.
 */
public class IntrospectedTable {


    private Table table;

    private TableConfiguration configuration;



    public Table getTable() {

        return table;
    }

    public void setTable(Table table) {

        this.table = table;
    }

    public TableConfiguration getConfiguration() {

        return configuration;
    }

    public void setConfiguration(TableConfiguration configuration) {

        this.configuration = configuration;
    }


}
