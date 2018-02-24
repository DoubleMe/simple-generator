package com.chm.generator;

import com.chm.generator.configuration.Configuration;
import com.chm.generator.configuration.ConfigurationParser;
import com.chm.generator.configuration.config.Context;
import com.chm.generator.connection.executor.DBConfigAdapter;
import com.chm.generator.dataobject.Table;
import com.chm.generator.generate.AbstractGenerator;
import com.chm.generator.generate.GeneratorConfigHolder;
import com.chm.generator.generate.GeneratorExecutor;
import com.chm.generator.generate.javafile.JavaModelGenerator;

import java.io.File;
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
public class ShellRunner {

    private static final String CONFIG_FILE = "-configfile";

    private static final String PROJECT_PATH = "-project";

    public static void main(String[] args) throws Exception {

        Map<String, String> map = parseCommandLine(args);

        String configFile = map.get(CONFIG_FILE);
        if (configFile == null){
            writeLine("error :configFile is null");
            System.exit(0);
        }
        String projectPath = map.get(PROJECT_PATH);
        if (projectPath == null){
            writeLine("warning : projectPath is null");
        }
        File configurationFile = new File(configFile);
        if (!configurationFile.exists()){
            writeLine("error : configFile is not exists");
            System.exit(0);
        }

        InputStream inputStream = new FileInputStream(configurationFile);
        ConfigurationParser parser = new ConfigurationParser();

        Configuration configuration = parser.parseConfiguration(inputStream);

        List<Context> contexts = configuration.getContexts();
        for (Context context : contexts) {

            if (projectPath != null){
                context.resetProjectPath(projectPath);
            }
            DBConfigAdapter dbConfigAdapter = new DBConfigAdapter(context);
            GeneratorConfigHolder configHolder = dbConfigAdapter.getGeneratorConfigHolder();
            GeneratorExecutor.execute(configHolder);

        }

        writeLine("+---------------------------------------------------+");
        writeLine("|                                                   |");
        writeLine("| B U I L D I N G SUCCESSFUL                         ");
        writeLine("|                                                   |");
        writeLine("+---------------------------------------------------+");
    }

    private static Map<String, String> parseCommandLine(String[] args) {

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (CONFIG_FILE.equalsIgnoreCase(args[i])) {
                map.put(CONFIG_FILE, args[i + 1]);
                i++;
            }
            if (PROJECT_PATH.equals(args[i])){
                map.put(PROJECT_PATH, args[i + 1]);
                i++;
            }
        }

        return map;
    }

    private static void writeLine(String line) {

        System.out.println(line);
    }
}
