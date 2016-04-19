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

import java.sql.PreparedStatement;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import domain.Person;
import mybatis.MybatisBulkInsertTest;

/**
 * Demonstrates the same procedures as {@link MybatisBulkInsertTest} using JDBC API.
 * 
 * @author Iwao AVE!
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcBulkInsertTest extends JdbcBulkInsertTestBase {
  private static final String cols = " (id, name1, name2, name3, name4, name5, name6, name7, name8, name9) ";

  private static final String placeholders = " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

  @Test
  public void insertMultiRow() throws Exception {
    PreparedStatement stmt = conn.prepareStatement(buildMultiRowStatement(cols, placeholders));
    for (int i = 0; i < numOfRows; i++) {
      int j = i * 10;
      Person person = persons.get(i);
      stmt.setInt(j + 1, person.getId());
      stmt.setString(j + 2, person.getName1());
      stmt.setString(j + 3, person.getName2());
      stmt.setString(j + 4, person.getName3());
      stmt.setString(j + 5, person.getName4());
      stmt.setString(j + 6, person.getName5());
      stmt.setString(j + 7, person.getName6());
      stmt.setString(j + 8, person.getName7());
      stmt.setString(j + 9, person.getName8());
      stmt.setString(j + 10, person.getName9());
    }
    stmt.executeUpdate();
    stmt.close();
    conn.commit();
    assertRowCount();
  }

  @Test
  public void insertBatch() throws Exception {
    String sql = buildStatement(cols, placeholders);
    PreparedStatement stmt = conn.prepareStatement(sql);
    for (int i = 0; i < numOfRows;) {
      Person person = persons.get(i);
      stmt.setInt(1, person.getId());
      stmt.setString(2, person.getName1());
      stmt.setString(3, person.getName2());
      stmt.setString(4, person.getName3());
      stmt.setString(5, person.getName4());
      stmt.setString(6, person.getName5());
      stmt.setString(7, person.getName6());
      stmt.setString(8, person.getName7());
      stmt.setString(9, person.getName8());
      stmt.setString(10, person.getName9());
      stmt.addBatch();
      i++;
      if (i % batchSize == 0 || i == numOfRows) {
        stmt.executeBatch();
      }
    }
    stmt.close();
    conn.commit();
    assertRowCount();
  }

  @Test
  public void insert() throws Exception {
    String sql = buildStatement(cols, placeholders);
    PreparedStatement stmt = conn.prepareStatement(sql);
    for (Person person : persons) {
      stmt.setInt(1, person.getId());
      stmt.setString(2, person.getName1());
      stmt.setString(3, person.getName2());
      stmt.setString(4, person.getName3());
      stmt.setString(5, person.getName4());
      stmt.setString(6, person.getName5());
      stmt.setString(7, person.getName6());
      stmt.setString(8, person.getName7());
      stmt.setString(9, person.getName8());
      stmt.setString(10, person.getName9());
      stmt.executeUpdate();
    }
    stmt.close();
    conn.commit();
    assertRowCount();
  }
}