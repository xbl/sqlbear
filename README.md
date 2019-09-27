# SQL Bear

这是一个在命令行中执行 SQL 的工具，由于需要多众多数据库进行兼容，这世界上只有Java 一种语言有统一的连接数据库标准 —— [JDBC（Java Database Connectivity）](https://zh.wikipedia.org/wiki/Java%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5)，所以本工具采用 Java 编写。

代码参考了部分 [Flyway](https://github.com/flyway/flyway) 的源码和实现方式，感谢开源作者们的无私。

目前支持 MYSQL、POSTGRESQL ，ORACLE 由于 Docker Image 太大😓 还有没有测试，从经验来看应该没问题，后面回去测试。

