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

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Before;
import org.junit.Test;

import common.BulkInsertTestBase;
import domain.Person;
import mapper.Mapper;

/**
 * Performs bulk insert in three different ways.
 * <ul>
 * <li>multi-row insert</li>
 * <li>batch insert</li>
 * <li>plain insert</li>
 * </ul>
 * 
 * @author Iwao AVE!
 */
public abstract class MybatisBulkInsertTestBase extends BulkInsertTestBase {

  protected static SqlSessionFactory sqlSessionFactory;

  public static void buildSqlSessionFactory(Class<?> mapperClass) throws Exception {
    UnpooledDataSourceFactory dataSourceFactory = new UnpooledDataSourceFactory();
    dataSourceFactory.setProperties(datasourceProperties);

    Environment environment = new Environment("test", new JdbcTransactionFactory(), dataSourceFactory.getDataSource());
    Configuration configuration = new Configuration();
    configuration.setEnvironment(environment);
    if (isOracle()) {
      // In XML mappers, statements with databaseId="oracle" will be used.
      configuration.setDatabaseId("oracle");
    }
    configuration.addMapper(mapperClass);
    sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
  }

  @Before
  public void initDb() throws IOException {
    SqlSession session = sqlSessionFactory.openSession();
    Connection conn = session.getConnection();
    Reader reader = Resources.getResourceAsReader("create.sql");
    ScriptRunner runner = new ScriptRunner(conn);
    runner.setLogWriter(null);
    runner.runScript(reader);
    reader.close();
    session.close();
  }

  @Test
  public void insertMultiRow() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = (Mapper) sqlSession.getMapper(mapperClass());
      mapper.insertMultiRow(persons);
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
    assertRowCount(numOfRows);
  }

  @Test
  public void insertBatch() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
    Mapper mapper = (Mapper) sqlSession.getMapper(mapperClass());
    try {
      int size = persons.size();
      for (int i = 0; i < size;) {
        mapper.insert(persons.get(i));
        i++;
        if (i % batchSize == 0 || i == size) {
          sqlSession.flushStatements();
          sqlSession.clearCache();
        }
      }
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
    assertRowCount(numOfRows);
  }

  @Test
  public void insert() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    Mapper mapper = (Mapper) sqlSession.getMapper(mapperClass());
    try {
      for (Person person : persons) {
        mapper.insert(person);
      }
      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
    assertRowCount(numOfRows);
  }

  protected void assertRowCount(int expectedCount) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Mapper mapper = (Mapper) sqlSession.getMapper(mapperClass());
      int rows = mapper.countRows();
      assertEquals(expectedCount, rows);
    } finally {
      sqlSession.close();
    }
  }

  protected abstract Class<?> mapperClass();
}