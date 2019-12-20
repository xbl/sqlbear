package com.xbl.sqlbear;

import com.xbl.sqlbear.Configuration;
import com.xbl.sqlbear.Core;
import com.xbl.sqlbear.util.ConfigUtils;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Web {

    private final static String prefix = "/sqlbear/";

    public static void main(String args[]) throws FileNotFoundException {
        Configuration configuration = ConfigUtils.load(Configuration.getInputStreamByConf());

        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        Route subRoute = router.route(prefix + "*");

        subRoute.handler(routingContext -> {
            String path = routingContext.request().path();
            String sqlFilePath = path.replace(prefix, "");

            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain; charset=utf-8");
            try {
                String chunk = excuteSql(configuration, sqlFilePath);
                response.end(chunk);
            } catch (Exception e) {
                response.setStatusCode(500);
                response.end(e.toString());
            }
        });

        router.route().handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain; charset=utf-8");
            response.end("Wecome to Sqlbear!");
        });

        server.requestHandler(router).listen(8080);
    }

    private static String excuteSql(Configuration configuration, String sqlFilePath) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);
        Core core = new Core(writer, configuration);
        // web 与 命令行不同，web 必须限定在配置的 scripts 目录中的脚本
        core.executeSqlFile(configuration.getScripts() + File.separator + sqlFilePath);
        writer.close();
        return out.toString();
    }
}
