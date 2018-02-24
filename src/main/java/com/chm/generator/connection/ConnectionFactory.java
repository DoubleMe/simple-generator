package com.chm.generator.connection;

import com.chm.generator.configuration.JDBCConnectionConfiguration;
import com.chm.generator.message.MessageSource;
import com.chm.generator.utils.ClassLoaderHolder;

import static com.chm.generator.utils.StringUtility.stringHasValue;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/19 17:31.
 */
public class ConnectionFactory {

    private static Connection connection = null;

    /**
     * 获取数据库连接信息
     *
     * @param configuration
     * @return
     */
    public static Connection getConnection(JDBCConnectionConfiguration configuration) {

        if (connection == null) {
            Properties props = new Properties();

            if (stringHasValue(configuration.getUserId())) {
                props.setProperty("user", configuration.getUserId());
            }

            if (stringHasValue(configuration.getPassword())) {
                props.setProperty("password", configuration.getPassword());
            }

            props.putAll(configuration.getProperties());

            Driver driver = getDriver(configuration.getDriverClass());
            try {
                connection = driver.connect(configuration.getConnectionURL(), props);
            } catch (SQLException e) {
                throw new RuntimeException(MessageSource.getMessage("RuntimeError.1"), e);
            }
        }

        return connection;
    }

    /**
     * 数据库连接驱动
     *
     * @param driverClass
     * @return Driver
     */
    public static Driver getDriver(String driverClass) {

        Driver driver = null;

        try {
            Class<?> clazz = ClassLoaderHolder.externalClassForName(driverClass);
            driver = (Driver) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(MessageSource.getMessage("RuntimeError.0"), e);
        }

        return driver;
    }
}
