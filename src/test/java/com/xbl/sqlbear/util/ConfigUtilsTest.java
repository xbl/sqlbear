package com.xbl.sqlbear.util;

import com.xbl.sqlbear.Configuration;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConfigUtilsTest {

    @Test
    public void shoud_get_properties_when_load_without_driver() throws IOException {
        String inputStr = "sqlbear.url=jdbc:mysql://localhost:3306/orders\n" +
                "# sqlbear.driver=\n" +
                "sqlbear.user=root\n" +
                "sqlbear.password=example";

        InputStream inputStream = new ByteArrayInputStream(inputStr.getBytes());
        Configuration configuration = ConfigUtils.load(inputStream);
        Configuration expectedConfigration = new Configuration("jdbc:mysql://localhost:3306/orders", "root", "example");
        assertEquals(configuration, expectedConfigration);
    }

    @Test
    public void shoud_get_properties_when_load_conf_with_driver() throws IOException {
        String inputStr = "sqlbear.url=jdbc:mysql://localhost:3306/orders\n" +
                "sqlbear.driver=com.xbl.mydriver\n" +
                "sqlbear.user=root\n" +
                "sqlbear.password=example";

        InputStream inputStream = new ByteArrayInputStream(inputStr.getBytes());
        Configuration configuration = ConfigUtils.load(inputStream);
        Configuration expectedConfigration = new Configuration("jdbc:mysql://localhost:3306/orders", "root", "example", "com.xbl.mydriver");
        assertEquals(configuration, expectedConfigration);
    }
}
