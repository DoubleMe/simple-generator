package com.chm.generator.connection.executor;

import com.chm.generator.configuration.config.Context;
import com.chm.generator.configuration.config.TableConfiguration;
import com.chm.generator.connection.ConnectionFactory;
import com.chm.generator.connection.executor.impl.SqlExecutor;
import com.chm.generator.constants.SqlConstants;
import com.chm.generator.dataobject.column.Column;
import com.chm.generator.dataobject.table.TableInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/20 12:22.
 */
public class DBConfigAdapter {


    private Connection connection;

    private Executor executor;

    public DBConfigAdapter(Context context) {

        this.connection = ConnectionFactory.getConnection(context.getJdbcConnectionConfiguration());
        this.executor = new SqlExecutor(connection);
    }

    /**
     * 获取用户配置表信息
     *
     * @param context
     * @return
     */
    public List<TableInfo> getTables(Context context) {


        ArrayList<TableConfiguration> tableConfigurations = context.getTableConfigurations();

        String connectionURL = context.getJdbcConnectionConfiguration().getConnectionURL();
        String schema = parseSchema(connectionURL);
        try {
            List<TableInfo> tables = new ArrayList<>();
            for (TableConfiguration tableConfiguration : tableConfigurations) {
                ResultSet resultSet = executor.executeQuery(SqlConstants.QUERY_TABLE_INFO, schema, tableConfiguration.getTableName());
                while (resultSet.next()) {
                    TableInfo table = new TableInfo();
                    String tableName = resultSet.getString("TABLE_NAME");
                    String remark = resultSet.getString("TABLE_COMMENT");
                    table.setTableName(tableName);
                    table.setRemark(remark);
                    table.setColumns(getTableColumns(tableName,schema));
                    tables.add(table);
                }
            }

            executor.closeAll();
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 获取表字段信息
     *
     * @param tableName
     * @param schema
     * @return
     */
    public List<Column> getTableColumns(String tableName, String schema) {


        List<Column> columns = new ArrayList<>();
        try {
            ResultSet resultSet = connection.getMetaData().getColumns(null, schema, tableName, null);
            while (resultSet.next()) {
                Column column = new Column();
                String columnName = resultSet.getString("COLUMN_NAME");
                String remark = resultSet.getString("REMARKS");
                int dataType = resultSet.getInt("DATA_TYPE");
                column.setRemark(remark);
                column.setColumnName(columnName);
                column.setJdbcType(dataType);
                columns.add(column);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }


        return columns;
    }


    /**
     * 从url 中解析出 schema
     *
     * @param connectionURL 连接信息
     * @return schema
     */
    private String parseSchema(String connectionURL) {

        int index = connectionURL.lastIndexOf("/");
        int endIndex = connectionURL.indexOf("?");
        int end = endIndex == -1 ? connectionURL.length() : endIndex;

        String schema = connectionURL.substring(index + 1, end);
        return schema;
    }


}
