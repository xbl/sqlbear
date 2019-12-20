package com.xbl.sqlbear;

import com.xbl.sqlbear.util.ConfigUtils;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.xbl.sqlbear.util.Output.error;

public class Core {

    private PrintWriter writer;
    private Configuration configuration;

    public Core(PrintWriter writer, Configuration configuration) {
        this.writer = writer;
        this.configuration = configuration;
    }

    public void executeSqlFile(String sqlFilePath) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(sqlFilePath);
        } catch (FileNotFoundException e) {
            exit("未找到 SQL 文件：" + sqlFilePath);
            return ;
        }
        ScriptRunner runner = new ScriptRunner(getConnection());
        runner.setLogWriter(writer);
        runner.runScript(fileReader);
        runner.closeConnection();
    }

    private Connection getConnection() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
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

    private void exit(String message) {
        writer.println(error(message));
    }
}
