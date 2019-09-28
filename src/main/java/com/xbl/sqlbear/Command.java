package com.xbl.sqlbear;

import com.xbl.sqlbear.util.ConfigUtils;
import com.xbl.sqlbear.util.DbDriverMapper;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.xbl.sqlbear.Configuration.CONFIG_FOLDER;
import static com.xbl.sqlbear.Configuration.SCRIPTS_FOLDER;

public class Command {
    public static void main(String args[]) throws Exception {
        ScriptRunner runner = new ScriptRunner(getConnection());
        runner.runScript(getFileReader(args[0]));
    }

    private static FileReader getFileReader(String filename) throws FileNotFoundException {
        String path = new File(SCRIPTS_FOLDER, filename).toString();
        return new FileReader(path);
    }

    private static Connection getConnection() throws IOException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException, ClassNotFoundException, SQLException {
        Configuration configuration = ConfigUtils.load(getInputStreamByConf());
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class.forName(DbDriverMapper.getDiverName(configuration.getUrl()), true, classLoader).getDeclaredConstructor().newInstance();

        // 创建一个数据源
        return DriverManager.getConnection(configuration.getUrl(), configuration.getUser(), configuration.getPassword());
    }

    public static InputStream getInputStreamByConf() throws FileNotFoundException {
        return new FileInputStream(CONFIG_FOLDER + "/sqlbear.conf");
    }
}
