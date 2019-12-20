package com.xbl.sqlbear;

import com.xbl.sqlbear.util.ConfigUtils;

import java.io.PrintWriter;

public class Command {

    public static void main(String args[]) {
        String sqlFilePath = args[0];

        try {
            Configuration configuration = ConfigUtils.load(Configuration.getInputStreamByConf());
            Core core = new Core(new PrintWriter(System.out), configuration);
            core.executeSqlFile(sqlFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
