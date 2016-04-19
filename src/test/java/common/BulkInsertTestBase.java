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
package common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

import domain.Person;

/**
 * @author Iwao AVE!
 */
public abstract class BulkInsertTestBase {
  protected List<Person> persons = createItems(numOfRows);

  protected static Properties datasourceProperties = loadDataSourceProperties();

  protected static final int numOfRows = 10000;

  protected static final int batchSize = 100;

  private static final String propertyPrefix = "hsqldb";

  protected static Properties loadDataSourceProperties() {
    Properties result = new Properties();
    try {
      Properties datasourceProperties = Resources.getResourceAsProperties("datasource.properties");
      result.setProperty("driver", datasourceProperties.getProperty(propertyPrefix + ".driver"));
      result.setProperty("url", datasourceProperties.getProperty(propertyPrefix + ".url"));
      result.setProperty("username", datasourceProperties.getProperty(propertyPrefix + ".username"));
      result.setProperty("password", datasourceProperties.getProperty(propertyPrefix + ".password"));
      return result;
    } catch (IOException e) {
      throw new RuntimeException("Failed to load datasource.properties", e);
    }
  }

  protected static List<Person> createItems(int numOfRows) {
    List<Person> list = new ArrayList<Person>();
    for (int i = 0; i < numOfRows; i++) {
      Person person = new Person();
      person.setId(i);
      list.add(person);
    }
    return list;
  }

  protected static boolean isOracle() {
    return datasourceProperties.getProperty("url").contains("oracle");
  }
}
