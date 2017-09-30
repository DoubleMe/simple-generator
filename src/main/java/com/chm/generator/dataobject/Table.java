package com.chm.generator.dataobject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/20 9:28.
 */
public class Table {


    /**
     * 表名
     */
    private String tableName;

    /**
     * 备注
     */
    private String remark;

    private List<Column> columns;

    private List<Column> keys;

    public Table() {

        columns = new ArrayList<>();
        keys = new ArrayList<>();
    }

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

    public List<Column> getKeys() {

        return keys;
    }

    public void setKeys(List<Column> keys) {

        for (Column key : keys) {
            for (Column column : columns) {
                if (key.getColumnName().equals(column.getColumnName())) {
                    key.setJdbcType(column.getJdbcType());
                    key.setRemark(column.getRemark());
                    continue;
                }
            }
        }
        this.keys = keys;
    }

    public boolean isKey(Column column) {

        for (Column key : keys) {

            if (column.getColumnName().equals(key.getColumnName())) {
                return true;
            }
        }

        return false;
    }
}
