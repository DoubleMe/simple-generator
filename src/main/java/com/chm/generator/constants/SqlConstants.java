package com.chm.generator.constants;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/20 12:17.
 */
public class SqlConstants {

    public static final String QUERY_TABLE_INFO = "select * from information_schema.TABLES where table_schema = '{0}' and table_name like '{1}'";

    public static final String QUERY_COLUMN_INFO = "select * from information_schema.COLUMNS where table_schema = '{0}' and table_name = '{1}'";
}
