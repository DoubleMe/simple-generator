package com.chm.generator.dataobject.table;

import com.chm.generator.dataobject.column.Column;

import java.util.List;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/20 9:28.
 */
public class TableInfo {


    /**
     * 表名
     */
    private String tableName;

    /**
     * 备注
     */
    private String remark;

    private List<Column> columns;

    public String getTableName() {

        return tableName;
    }

    public void setTableName(String tableName) {

        this.tableName = tableName;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
    }

    public List<Column> getColumns() {

        return columns;
    }

    public void setColumns(List<Column> columns) {

        this.columns = columns;
    }
}
