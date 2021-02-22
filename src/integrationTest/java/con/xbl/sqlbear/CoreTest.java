package con.xbl.sqlbear;

import com.xbl.sqlbear.Configuration;
import com.xbl.sqlbear.Core;
import com.xbl.sqlbear.util.ConfigUtils;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static com.xbl.sqlbear.util.Output.error;
import static org.junit.Assert.assertEquals;

public class CoreTest {

    @Test
    public void given_wrong_path_when_executeSqlFile_then_got_receive_error() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String sqlFilePath = "./wrong_path.sql";
        PrintWriter logWriter = new PrintWriter(new ByteArrayOutputStream());

        ByteArrayOutputStream errorOut = new ByteArrayOutputStream();
        PrintWriter errorWriter = new PrintWriter(errorOut);

        try {
            Configuration configuration = ConfigUtils.load(Configuration.getInputStreamByConf());
            Core core = new Core(logWriter, configuration, errorWriter);
            core.executeSqlFile(sqlFilePath);
        } catch (FileNotFoundException | SQLException e) {
            assertEquals(error("未找到 SQL 文件：" + sqlFilePath).trim(), errorOut.toString().trim());
        }
    }

    @Test
    public void given_not_create_table_when_select_then_got_error() throws Exception {
        String sqlFilePath = "./scripts/mysql_create_and_insert.sql";
        PrintWriter writer = new PrintWriter(new ByteArrayOutputStream());

        Configuration configuration = ConfigUtils.load(Configuration.getInputStreamByConf());
        Core core = new Core(writer, configuration, writer);
        core.executeSqlFile(sqlFilePath);
        assertEquals(1, getOrderCount());

        // drop table
        String dropTablePath = "./scripts/mysql_drop_table.sql";
        core.executeSqlFile(dropTablePath);
    }

    private SqlSessionFactory createSqlSessionFactory() throws FileNotFoundException {
        Configuration conf = ConfigUtils.load(Configuration.getInputStreamByConf());
        DataSource dataSource = new org.apache.ibatis.datasource.pooled.PooledDataSource(
                conf.getDriver(), conf.getUrl(), conf.getUser(), conf.getPassword());
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development",
                transactionFactory, dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
        configuration.addMapper(MysqlMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(configuration);
        return sqlSessionFactory;
    }

    public int getOrderCount() throws FileNotFoundException {
        SqlSession session = getSqlSession();
        MysqlMapper mapper = session.getMapper(MysqlMapper.class);
        int count = mapper.getCount();
        session.close();
        return count;
    }

    private SqlSession getSqlSession() throws FileNotFoundException {
        SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }

}
