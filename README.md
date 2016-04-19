# Testing bulk insert using JDBC and MyBatis

## Configuration

```datasource.properties``` is to define datasource properties.
Each set should have unique prefix.

```ini
# HSQLDB
hsqldb.driver=org.hsqldb.jdbcDriver
hsqldb.url=jdbc:hsqldb:mem:bulkinsert
hsqldb.username=sa
hsqldb.password=

# PostgreSQL
postgresql.driver=org.postgresql.Driver
postgresql.url=jdbc:postgresql:bulkinsert
postgresql.username=bulkinsert
postgresql.password=

# Oracle
oracle.driver=oracle.jdbc.driver.OracleDriver
oracle.url=jdbc:oracle:thin:@192.168.57.101:1521:ORCL
oracle.username=bulkinsert
oracle.password=bulkinsert
```

In ```BulkInsertTestBase.java```, you can configure basic runtime parameters.

```java
// the number of rows to insert
protected static final int numOfRows = 10000;
// batch size for batch insert
protected static final int batchSize = 100;
// which datasource setting to use
private static final String propertyPrefix = "hsqldb";
```

## Test cases

There are four JUnit test classes:

- jdbc/JdbcBulkInsertTest : perform bulk insert using JDBC
- jdbc/JdbcBulkInsert50ColsTest : same as above but with 50 columns per row
- mybatis/MybatisBulkInsertTest : perform bulk insert using MyBatis
- mybatis/MybatisBulkInsert50ColsTest : same as above but with 50 columns per row

And each class has three test methods.

- insert : perform plain insert (non-batch)
- insertBatch : perform batch insert
- insertMultiRow : multi-row insert

In Eclipse, you can just right-click a class or a ```@Test``` method and run it as a JUnit test case.

## Profiling

I used [VisualVM](https://visualvm.java.net/) with its [Eclipse launcher](https://visualvm.java.net/eclipse-launcher.html).

