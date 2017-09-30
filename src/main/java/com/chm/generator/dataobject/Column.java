package com.chm.generator.dataobject;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/20 13:59.
 */
public class Column {


    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 字段类型
     */
    private int jdbcType;

    /**
     * 字段注释
     */
    private String remark;

    public String getColumnName() {

        return columnName;
    }

    public void setColumnName(String columnName) {

        this.columnName = columnName;
    }

    public int getJdbcType() {

        return jdbcType;
    }

    public void setJdbcType(int jdbcType) {

        this.jdbcType = jdbcType;
    }

    public String getRemark() {

        return remark;
    }

    public void setRemark(String remark) {

        this.remark = remark;
    }
}
