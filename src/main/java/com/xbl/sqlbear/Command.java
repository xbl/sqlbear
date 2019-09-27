package com.xbl.sqlbear;

import com.xbl.sqlbear.util.ConfigUtils;
import com.xbl.sqlbear.util.DbDriverMapper;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        Configuration configuration = ConfigUtils.load();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class.forName(DbDriverMapper.getDiverName(configuration.getUrl()), true, classLoader).getDeclaredConstructor().newInstance();

        // 创建一个数据源
        return DriverManager.getConnection(configuration.getUrl(), configuration.getUser(), configuration.getPassword());
    }
}
