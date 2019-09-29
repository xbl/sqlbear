package com.xbl.sqlbear;

import com.xbl.sqlbear.util.ConfigUtils;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.xbl.sqlbear.Configuration.CONFIG_FOLDER;
import static com.xbl.sqlbear.util.Output.error;

public class Command {

    public static void main(String args[]) throws Exception {
        FileReader fileReader = null;
        String sqlFilePath = args[0];
        try {
            fileReader = new FileReader(sqlFilePath);
        } catch (FileNotFoundException e) {
            exit("未找到 SQL 文件：" + sqlFilePath);
        }
        ScriptRunner runner = new ScriptRunner(getConnection());
        runner.runScript(fileReader);
        runner.closeConnection();
    }

    private static void exit(String message) {
        System.out.println(error(message));
        System.exit(1);
    }

    private static Connection getConnection() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Configuration configuration = ConfigUtils.load(getInputStreamByConf());
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class.forName(configuration.getDriver(), true, classLoader).getDeclaredConstructor().newInstance();

        // 创建一个数据源
        try {
            return DriverManager.getConnection(configuration.getUrl(), configuration.getUser(), configuration.getPassword());
        } catch (SQLException e) {
            exit("数据库链接失败！");
        }
        return null;
    }

    public static InputStream getInputStreamByConf() {
        String configFile = CONFIG_FOLDER + "/sqlbear.conf";
        try {
            return new FileInputStream(configFile);
        } catch (FileNotFoundException e) {
            exit("未找到 CONFIG 文件：" + configFile);
        }
        return null;
    }
}
