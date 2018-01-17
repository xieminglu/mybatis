package com.fanle.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.fanle.mapper.AgentMapper;

public class MybatisJavaConfig {
	
	private static SqlSessionFactory sqlSessionFactory;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	
	static {
		//创建配置对象
		Configuration configuration = new Configuration();
		
		//创建数据源
		PooledDataSource dataSource = new PooledDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:6331/yj_cps");
		dataSource.setUsername("root");
		dataSource.setPassword("myserver");
		dataSource.setDriver("com.mysql.jdbc.Driver");
		
		//创建数据源环境对象
		Environment environment = new Environment("prime", new JdbcTransactionFactory(), dataSource);
		configuration.setEnvironment(environment);
		
		configuration.addMapper(AgentMapper.class);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	public static SqlSession getSession() {
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession == null) {
			sqlSession = sqlSessionFactory.openSession();
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}
	
	public static void closeSession() {
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession != null) {
			sqlSession.close();
			threadLocal.set(null);
		}
	}

}
