package com.chm.generator.utils;

import com.chm.generator.dataobject.Column;
import com.chm.generator.types.JavaTypeResolver;

/**
 * @author chen-hongmin
 * @date 2018/9/20 14:18
 * @since V1.0
 */
public class ColumnUtil {

    /**
     * 获取拼接后的字符串
     *
     * @param column
     * @return
     */
    public static String getSpliceString(Column column) {

        int jdbcType = column.getJdbcType();

        String sqlJdbcTypeName = JavaTypeResolver.getSqlJdbcTypeName(jdbcType);
        String columnName = JavaBeansUtil.getCamelCaseString(column.getColumnName(), false);

        StringBuilder sb = new StringBuilder();
        sb.append("#{").append(columnName).append(",jdbcType=").append(sqlJdbcTypeName).append("}");

        return sb.toString();

    }
}
