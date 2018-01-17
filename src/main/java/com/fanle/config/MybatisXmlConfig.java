package com.fanle.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisXmlConfig {
	
	private static final String configPath = "mybatis-config.xml";
	private static SqlSessionFactory sqlSessionFactory;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	
	static {
		try {
			InputStream inputStream = Resources.getResourceAsStream(configPath);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("mybatis初始化异常");
		}
	}
	
	public static SqlSessionFactory getSqlsessionFactory() {
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
		threadLocal.set(null);
		if (sqlSession != null) {
			sqlSession.close();
		}
	}

}
