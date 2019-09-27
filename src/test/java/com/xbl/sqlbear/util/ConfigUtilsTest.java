package com.xbl.sqlbear.util;

import com.xbl.sqlbear.Configuration;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class ConfigUtilsTest {

    @Test
    public void shoud_get_properties_when_load() throws IOException {
        Configuration configuration =  ConfigUtils.load();
        Configuration expectedConfigration = new Configuration("jdbc:mysql://localhost:3306/orders", "root", "example");
        assertEquals(configuration, expectedConfigration);
    }
}
