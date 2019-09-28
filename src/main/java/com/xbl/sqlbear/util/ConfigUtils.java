package com.xbl.sqlbear.util;

import com.xbl.sqlbear.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.xbl.sqlbear.Configuration.CONFIG_FOLDER;

public class ConfigUtils {
    public static Configuration load(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        String url = properties.getProperty("sqlbear.url");
        String user = properties.getProperty("sqlbear.user");
        String password = properties.getProperty("sqlbear.password");
        String driver = properties.getProperty("sqlbear.driver");

        if (driver == null || "".equals(driver)) {
            return new Configuration(url, user, password);
        }
        return new Configuration(url, user, password, driver);
    }
}
