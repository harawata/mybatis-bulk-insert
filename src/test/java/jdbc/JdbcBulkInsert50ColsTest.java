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

/**
 * The same as {@link JdbcBulkInsertTest}, but with 50 columns per row.
 * 
 * @author Iwao AVE!
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JdbcBulkInsert50ColsTest extends JdbcBulkInsertTestBase {
  private static final String cols = " (id, name1, name2, name3, name4, name5, name6, name7, name8, name9, name10, name11, name12, name13, name14, name15, name16, name17, name18, name19, name20, name21, name22, name23, name24, name25, name26, name27, name28, name29, name30, name31, name32, name33, name34, name35, name36, name37, name38, name39, name40, name41, name42, name43, name44, name45, name46, name47, name48, name49) ";

  private static final String placeholders = " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

  @Test
  public void insertMultiRow() throws Exception {
    PreparedStatement stmt = conn.prepareStatement(buildMultiRowStatement(cols, placeholders));
    for (int i = 0; i < numOfRows; i++) {
      int j = i * 50;
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
      stmt.setString(j + 11, person.getName10());
      stmt.setString(j + 12, person.getName11());
      stmt.setString(j + 13, person.getName12());
      stmt.setString(j + 14, person.getName13());
      stmt.setString(j + 15, person.getName14());
      stmt.setString(j + 16, person.getName15());
      stmt.setString(j + 17, person.getName16());
      stmt.setString(j + 18, person.getName17());
      stmt.setString(j + 19, person.getName18());
      stmt.setString(j + 20, person.getName19());
      stmt.setString(j + 21, person.getName20());
      stmt.setString(j + 22, person.getName21());
      stmt.setString(j + 23, person.getName22());
      stmt.setString(j + 24, person.getName23());
      stmt.setString(j + 25, person.getName24());
      stmt.setString(j + 26, person.getName25());
      stmt.setString(j + 27, person.getName26());
      stmt.setString(j + 28, person.getName27());
      stmt.setString(j + 29, person.getName28());
      stmt.setString(j + 30, person.getName29());
      stmt.setString(j + 31, person.getName30());
      stmt.setString(j + 32, person.getName31());
      stmt.setString(j + 33, person.getName32());
      stmt.setString(j + 34, person.getName33());
      stmt.setString(j + 35, person.getName34());
      stmt.setString(j + 36, person.getName35());
      stmt.setString(j + 37, person.getName36());
      stmt.setString(j + 38, person.getName37());
      stmt.setString(j + 39, person.getName38());
      stmt.setString(j + 40, person.getName39());
      stmt.setString(j + 41, person.getName40());
      stmt.setString(j + 42, person.getName41());
      stmt.setString(j + 43, person.getName42());
      stmt.setString(j + 44, person.getName43());
      stmt.setString(j + 45, person.getName44());
      stmt.setString(j + 46, person.getName45());
      stmt.setString(j + 47, person.getName46());
      stmt.setString(j + 48, person.getName47());
      stmt.setString(j + 49, person.getName48());
      stmt.setString(j + 50, person.getName49());
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
      stmt.setString(11, person.getName10());
      stmt.setString(12, person.getName11());
      stmt.setString(13, person.getName12());
      stmt.setString(14, person.getName13());
      stmt.setString(15, person.getName14());
      stmt.setString(16, person.getName15());
      stmt.setString(17, person.getName16());
      stmt.setString(18, person.getName17());
      stmt.setString(19, person.getName18());
      stmt.setString(20, person.getName19());
      stmt.setString(21, person.getName20());
      stmt.setString(22, person.getName21());
      stmt.setString(23, person.getName22());
      stmt.setString(24, person.getName23());
      stmt.setString(25, person.getName24());
      stmt.setString(26, person.getName25());
      stmt.setString(27, person.getName26());
      stmt.setString(28, person.getName27());
      stmt.setString(29, person.getName28());
      stmt.setString(30, person.getName29());
      stmt.setString(31, person.getName30());
      stmt.setString(32, person.getName31());
      stmt.setString(33, person.getName32());
      stmt.setString(34, person.getName33());
      stmt.setString(35, person.getName34());
      stmt.setString(36, person.getName35());
      stmt.setString(37, person.getName36());
      stmt.setString(38, person.getName37());
      stmt.setString(39, person.getName38());
      stmt.setString(40, person.getName39());
      stmt.setString(41, person.getName40());
      stmt.setString(42, person.getName41());
      stmt.setString(43, person.getName42());
      stmt.setString(44, person.getName43());
      stmt.setString(45, person.getName44());
      stmt.setString(46, person.getName45());
      stmt.setString(47, person.getName46());
      stmt.setString(48, person.getName47());
      stmt.setString(49, person.getName48());
      stmt.setString(50, person.getName49());
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
      stmt.setString(11, person.getName10());
      stmt.setString(12, person.getName11());
      stmt.setString(13, person.getName12());
      stmt.setString(14, person.getName13());
      stmt.setString(15, person.getName14());
      stmt.setString(16, person.getName15());
      stmt.setString(17, person.getName16());
      stmt.setString(18, person.getName17());
      stmt.setString(19, person.getName18());
      stmt.setString(20, person.getName19());
      stmt.setString(21, person.getName20());
      stmt.setString(22, person.getName21());
      stmt.setString(23, person.getName22());
      stmt.setString(24, person.getName23());
      stmt.setString(25, person.getName24());
      stmt.setString(26, person.getName25());
      stmt.setString(27, person.getName26());
      stmt.setString(28, person.getName27());
      stmt.setString(29, person.getName28());
      stmt.setString(30, person.getName29());
      stmt.setString(31, person.getName30());
      stmt.setString(32, person.getName31());
      stmt.setString(33, person.getName32());
      stmt.setString(34, person.getName33());
      stmt.setString(35, person.getName34());
      stmt.setString(36, person.getName35());
      stmt.setString(37, person.getName36());
      stmt.setString(38, person.getName37());
      stmt.setString(39, person.getName38());
      stmt.setString(40, person.getName39());
      stmt.setString(41, person.getName40());
      stmt.setString(42, person.getName41());
      stmt.setString(43, person.getName42());
      stmt.setString(44, person.getName43());
      stmt.setString(45, person.getName44());
      stmt.setString(46, person.getName45());
      stmt.setString(47, person.getName46());
      stmt.setString(48, person.getName47());
      stmt.setString(49, person.getName48());
      stmt.setString(50, person.getName49());
      stmt.executeUpdate();
    }
    stmt.close();
    conn.commit();
    assertRowCount();
  }
}