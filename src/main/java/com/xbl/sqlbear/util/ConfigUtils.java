package com.xbl.sqlbear.util;

import com.xbl.sqlbear.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    public static Configuration load(InputStream inputStream) {
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
