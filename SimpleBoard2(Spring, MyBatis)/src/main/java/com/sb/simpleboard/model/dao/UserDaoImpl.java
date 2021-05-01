package com.sb.simpleboard.model.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sb.simpleboard.model.dto.UserDto;
import com.sb.simpleboard.util.SqlMapConfig;

@Repository
public class UserDaoImpl implements UserDao {
	
	private final String NAMESPACE = "com.sb.simpleboard.model.dao.";
	
	@Override
	public void insert(UserDto user) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.insert(NAMESPACE + "insert", user);//product.xml에 있는 namespace + id 
			sqlSession.commit();
		}
	}

	@Override
	public void modify(UserDto user) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.update(NAMESPACE + "modify", user);
			sqlSession.commit();
		}
	}

	@Override
	public UserDto select(UserDto user) throws SQLException {
		UserDto ret=null;
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			ret=sqlSession.selectOne(NAMESPACE + "select", user);
		}
		return ret;
	}
	@Override
	public UserDto selectid(UserDto user) throws SQLException {
		UserDto ret=null;
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			ret=sqlSession.selectOne(NAMESPACE + "selectid", user);
		}
		return ret;
	}
}
