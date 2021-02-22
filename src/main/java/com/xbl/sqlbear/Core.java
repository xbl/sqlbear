package com.xbl.sqlbear;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.xbl.sqlbear.util.Output.error;

public class Core {

    private PrintWriter logWriter;
    private Configuration configuration;
    private PrintWriter errorWriter;

    public Core(PrintWriter logWriter, Configuration configuration, PrintWriter errorWriter) {
        this.logWriter = logWriter;
        this.configuration = configuration;
        this.errorWriter = errorWriter;
    }

    public void executeSqlFile(String sqlFilePath) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, FileNotFoundException, SQLException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(sqlFilePath);
        } catch (FileNotFoundException e) {
            exit("未找到 SQL 文件：" + sqlFilePath);
            throw e;
        }
        ScriptRunner runner = new ScriptRunner(getConnection());
        runner.setLogWriter(logWriter);
        runner.runScript(fileReader);
        runner.setErrorLogWriter(errorWriter);
        runner.closeConnection();
    }

    private Connection getConnection() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class.forName(configuration.getDriver(), true, classLoader).getDeclaredConstructor().newInstance();

        // 创建一个数据源
        try {
            Connection connection = DriverManager.getConnection(configuration.getUrl(), configuration.getUser(), configuration.getPassword());
            connection.setAutoCommit(true);
            return connection;
        } catch (SQLException e) {
            exit("数据库链接失败！");
            throw e;
        }
    }

    private void exit(String message) {
        errorWriter.println(error(message));
        errorWriter.flush();
    }
}
