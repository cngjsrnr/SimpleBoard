package com.sb.simpleboard.model.service;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.simpleboard.model.dto.UserDto;
import com.sb.simpleboard.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean insert(UserDto user) throws SQLException {
		if(user.getUid()=="" || user.getUname()=="" || user.getUpass()=="0")
			return false;
		sqlSession.getMapper(UserMapper.class).insert(user);
		return true;
	}

	@Override
	public boolean modify(UserDto user) throws SQLException {
		if(user.getUid()=="" || user.getUname()=="" || user.getUpass()=="0")
			return false;
		sqlSession.getMapper(UserMapper.class).modify(user);
		return true;
	}

	@Override
	public UserDto select(UserDto user) throws SQLException {
		return sqlSession.getMapper(UserMapper.class).select(user);
	}
	@Override
	public UserDto selectid(UserDto user) throws SQLException {
		return sqlSession.getMapper(UserMapper.class).selectid(user);
	}

}
