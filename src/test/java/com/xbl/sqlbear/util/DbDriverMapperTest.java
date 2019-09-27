package com.xbl.sqlbear.util;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DbDriverMapperTest {

    @Test
    public void shoud_return_mysql_driver_given_starts_with_jdbc_mysql() {
        String driverName = DbDriverMapper.getDiverName("jdbc:mysql://localhost:3306/orders");
        String expectedResult = "com.mysql.cj.jdbc.Driver";

        assertEquals(driverName, expectedResult);
    }

    @Test
    public void shoud_return_postgres_driver_given_starts_with_jdbc_postgresql() {
        String driverName = DbDriverMapper.getDiverName("jdbc:postgresql://127.0.0.1:5432/test");
        String expectedResult = "org.postgresql.Driver";

        assertEquals(driverName, expectedResult);
    }

    @Test
    public void shoud_return_oracle_driver_given_starts_with_jdbc_oracle() {
        String driverName = DbDriverMapper.getDiverName("jdbc:oracle:thin:@//<host>:<port>/<service>");
        String expectedResult = "oracle.jdbc.OracleDriver";

        assertEquals(driverName, expectedResult);
    }

    @Test
    public void shoud_return_mariadb_driver_given_starts_with_jdbc_mariadb() {
        String driverName = DbDriverMapper.getDiverName("jdbc:mariadb://<host>:<port>/<database>?<key1>=<value1>&<key2>=<value2>");
        String expectedResult = "org.mariadb.jdbc.Driver";

        assertEquals(driverName, expectedResult);
    }
}
