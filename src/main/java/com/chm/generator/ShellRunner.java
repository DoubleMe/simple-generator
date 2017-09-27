package com.chm.generator;

import com.chm.generator.configuration.Configuration;
import com.chm.generator.configuration.ConfigurationParser;
import com.chm.generator.configuration.config.Context;
import com.chm.generator.connection.executor.DBConfigAdapter;
import com.chm.generator.dataobject.table.TableInfo;

import java.io.InputStream;
import java.util.List;

/**
 * @author chen-hongmin
 * @since  2017/9/10 15:30.
 * @version V1.0
 */
public class ShellRunner {


    public static void main(String[] args) throws Exception{

        String configFile = "/config/config.xml";

        ConfigurationParser parser = new ConfigurationParser();
        InputStream inputStream = parser.getClass().getResourceAsStream(configFile);

        Configuration configuration = parser.parseConfiguration(inputStream);

        List<Context> contexts = configuration.getContexts();
        for (Context context : contexts){

            DBConfigAdapter dbConfigAdapter = new DBConfigAdapter(context);
            List<TableInfo> tables = dbConfigAdapter.getTables(context);

            System.out.println(tables);
        }

        System.out.println(configFile);

    }
}
