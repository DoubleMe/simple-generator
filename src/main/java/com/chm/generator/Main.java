package com.chm.generator;

import com.chm.generator.configuration.Configuration;
import com.chm.generator.configuration.ConfigurationParser;
import com.chm.generator.configuration.config.Context;
import com.chm.generator.connection.executor.DBConfigAdapter;
import com.chm.generator.generate.GeneratorConfigHolder;
import com.chm.generator.generate.GeneratorExecutor;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen-hongmin
 * @version V1.0
 * @since 2017/9/10 15:30.
 */
public class Main {



    public static void main(String[] args) throws Exception {



        String configFile = "/Users/yuwen/tools/generate/config.xml";
        InputStream inputStream = new FileInputStream(configFile);
        ConfigurationParser parser = new ConfigurationParser();

        Configuration configuration = parser.parseConfiguration(inputStream);

        List<Context> contexts = configuration.getContexts();
        for (Context context : contexts) {

            DBConfigAdapter dbConfigAdapter = new DBConfigAdapter(context);
            GeneratorConfigHolder configHolder = dbConfigAdapter.getGeneratorConfigHolder();
            GeneratorExecutor.execute(configHolder);

        }
    }

}
