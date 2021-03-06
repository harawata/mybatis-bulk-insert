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
package mybatis;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import mapper.Mapper50Cols;

/**
 * @author Iwao AVE!
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MybatisBulkInsert50ColsTest extends MybatisBulkInsertTestBase {
  protected static Class<?> mapperClass = Mapper50Cols.class;

  @BeforeClass
  public static void setUp() throws Exception {
    buildSqlSessionFactory(mapperClass);
  }

  protected Class<?> mapperClass() {
    return Mapper50Cols.class;
  }
}
