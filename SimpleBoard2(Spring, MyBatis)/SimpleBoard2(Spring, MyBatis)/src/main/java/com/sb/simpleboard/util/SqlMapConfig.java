package com.sb.simpleboard.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class SqlMapConfig {

	private static SqlSessionFactory factory;
	
	static {
		try {
			String resource = "mybatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSession getSqlSession() {
		return factory.openSession();
	}
	
}
