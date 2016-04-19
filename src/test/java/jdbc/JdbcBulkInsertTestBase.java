/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package jdbc;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import common.BulkInsertTestBase;

/**
 * @author Iwao AVE!
 */
public abstract class JdbcBulkInsertTestBase extends BulkInsertTestBase {
  protected static Connection conn;

  @BeforeClass
  public static void setUp() throws Exception {
    Class.forName(datasourceProperties.getProperty("driver"));
    String url = datasourceProperties.getProperty("url");
    String username = datasourceProperties.getProperty("username");
    String password = datasourceProperties.getProperty("password");

    conn = DriverManager.getConnection(url, username, password);
    conn.setAutoCommit(false);
  }

  @Before
  public void initDb() throws IOException {
    Reader reader = Resources.getResourceAsReader("create.sql");
    ScriptRunner runner = new ScriptRunner(conn);
    runner.setLogWriter(null);
    runner.runScript(reader);
    reader.close();
  }

  @AfterClass
  public static void shutDown() throws Exception {
    conn.close();
  }

  protected static String buildStatement(String cols, String placeholders) {
    StringBuilder sql = new StringBuilder(" insert into person ").append(cols).append(" values ").append(placeholders);
    return sql.toString();
  }

  protected static String buildMultiRowStatement(String cols, String placeholders) {
    if (isOracle()) {
      return buildOracleMultiRowStatement(cols, placeholders);
    } else {
      return buildStandardMultiRowStatement(cols, placeholders);
    }
  }

  private static String buildStandardMultiRowStatement(String cols, String placeholders) {
    StringBuilder sql = new StringBuilder(" insert into person ").append(cols).append(" values ");
    for (int i = 0; i < numOfRows; i++) {
      if (i > 0) {
        sql.append(",");
      }
      sql.append(placeholders);
    }
    return sql.toString();
  }

  private static String buildOracleMultiRowStatement(String cols, String placeholders) {
    StringBuilder sql = new StringBuilder("insert all ");
    for (int i = 0; i < numOfRows; i++) {
      sql.append(" into person ").append(cols).append(" values ").append(placeholders);
    }
    sql.append(" select * from dual");
    return sql.toString();
  }

  protected void assertRowCount() throws SQLException {
    PreparedStatement stmt = conn.prepareStatement("select count(*) from person");
    ResultSet rs = stmt.executeQuery();
    rs.next();
    int rows = rs.getInt(1);
    assertEquals(numOfRows, rows);
  }
}