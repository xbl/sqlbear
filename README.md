# SQL Bear

![](https://github.com/xbl/sqlbear/workflows/Java%20CI/badge.svg)

这是一个在命令行中执行 SQL 的工具，由于需要多众多数据库进行兼容，这世界上只有Java 一种语言有统一的连接数据库标准 —— [JDBC（Java Database Connectivity）](https://zh.wikipedia.org/wiki/Java%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5)，所以本工具采用 Java 编写。

代码参考了部分 [Flyway](https://github.com/flyway/flyway) 的源码和实现方式，感谢开源作者们的无私。

目前支持 MYSQL、POSTGRESQL ，ORACLE 由于 Docker Image 太大😓 还有没有测试，从经验来看应该没问题，后面回去测试。



SQL bear 是用于在命令行和 web 执行SQL的工具，使用 PostMan 做接口测试时，需要执行 SQL，并 PostMan 本身并没有相应的工具或者接口。

1. 下载

   https://github.com/xbl/sqlbear-starter/releases
   
   找到 `Latest release` 标签，下载相应的 zip包


2. 在 drivers 目录添加相应的数据库驱动，drivers 已经包含了大部分常用的驱动。

3. 在 config/sqlbear.conf 添加数据库链接配置；

4. 执行

   在命令行中执行 SQL：

   ```shell
   sqlbear ./scripts/mysql_init.sql
   ```

   或者

   在 web 中执行 SQL:

   ```shell
   sqlbear-web
   ```



## web 执行SQL

web 执行的前缀为 `sqlbear`

 `http://localhost:8080/sqlbear/[sql文件名].sql`

SQL 文件必须放到 scripts 目录下才能被访问并执行。

## MacOS 注意事项
在使用中可能会出现如下提示：

`macOS无法验证此App不包含恶意软件`

需要打开安全设置，点击【允许】
