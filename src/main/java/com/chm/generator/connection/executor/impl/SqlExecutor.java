package com.chm.generator.connection.executor.impl;

import com.chm.generator.connection.executor.Executor;
import com.chm.generator.message.MessageSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/20 9:30.
 */
public class SqlExecutor implements Executor {

    private Connection connection;

    private Statement statement;


    public SqlExecutor(Connection connection) {

        this.connection = connection;

    }

    @Override
    public ResultSet executeQuery(String sql, String... params) {

        try {
            String formatSql = formatSql(sql, params);

            System.out.println("execute sql : " + formatSql);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(formatSql);

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException("mysql sql error" + sql, e);

        }
    }


    /**
     * 格式化sql
     * @param sql
     * @param params
     * @return
     */
    private static String formatSql(String sql, String... params){

        if (params == null || params.length == 0){
            return sql;
        }
       for(int i = 0; i < params.length ; i++){
           sql = sql.replace("{" + i + "}", params[i]);
       }

       return sql;
    }
    /**
     * 关闭连接
     */
    @Override
    public void closeAll() {

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

