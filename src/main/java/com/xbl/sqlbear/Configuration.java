package com.xbl.sqlbear;

import com.xbl.sqlbear.util.DbDriverMapper;

public class Configuration {
    private String url;
    private String user;
    private String password;
    private String driver;

    public static String CONFIG_FOLDER = "./config";

    public Configuration() {
    }

    public Configuration(String url, String user, String password, String driver) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.driver = driver;
    }

    public Configuration(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.driver = DbDriverMapper.getDiverName(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public boolean equals(Object obj) {
        Configuration configuration = (Configuration)obj;
        return this.password.equals(configuration.password)
                    && this.url.equals(configuration.url)
                    && this.password.equals(configuration.password)
                    && this.driver.equals(configuration.driver);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                    .append("url:").append(this.url).append(",")
                    .append("user:").append(this.user).append(",")
                    .append("password:").append(this.password).append(",")
                    .append("driver:").append(this.driver).toString();
    }
}
