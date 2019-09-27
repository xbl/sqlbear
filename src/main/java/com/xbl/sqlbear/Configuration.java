package com.xbl.sqlbear;

public class Configuration {
    private String url;
    private String user;
    private String password;

    public static String SCRIPTS_FOLDER = "./scripts";
    public static String CONFIG_FOLDER = "./config";

    public Configuration() {
    }

    public Configuration(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
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

    @Override
    public boolean equals(Object obj) {
        Configuration configuration = (Configuration)obj;
        return this.password.equals(configuration.password)
                    && this.url.equals(configuration.url)
                    && this.password.equals(configuration.password);
    }
}
