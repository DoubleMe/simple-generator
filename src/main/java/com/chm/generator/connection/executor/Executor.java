package com.chm.generator.connection.executor;

import java.sql.ResultSet;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/20 9:27.
 */
public interface Executor {


    /**
     * 执行查询sql
     * @param sql
     * @return
     */
    ResultSet executeQuery(String sql , String... params);

    void closeAll();
}
