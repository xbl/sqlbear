package com.xbl.sqlbear.util;

import com.xbl.sqlbear.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.xbl.sqlbear.Configuration.CONFIG_FOLDER;

public class ConfigUtils {
    public static Configuration load() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(CONFIG_FOLDER + "/sqlbear.conf"));
        String url = properties.getProperty("sqlbear.url");
        String user = properties.getProperty("sqlbear.user");
        String password = properties.getProperty("sqlbear.password");
        return new Configuration(url, user, password);
    }
}
