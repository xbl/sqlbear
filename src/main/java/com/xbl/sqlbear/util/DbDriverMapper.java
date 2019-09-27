package com.xbl.sqlbear.util;

import java.util.HashMap;
import java.util.Map;

public class DbDriverMapper {

    public static Map<String, String> prefixMap = new HashMap<String, String>() {
        {
            put("mysql", "com.mysql.cj.jdbc.Driver");
            put("postgresql", "org.postgresql.Driver");
            put("oracle", "oracle.jdbc.OracleDriver");
            put("mariadb", "org.mariadb.jdbc.Driver");
        }
    };

    public static String getDiverName(String url) {
        String keyword = url.split(":")[1];
        return prefixMap.get(keyword);
    }
}
