package com.sb.simpleboard.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.simpleboard.model.dao.UserDao;
import com.sb.simpleboard.model.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;
	
	@Override
	public void insert(UserDto user) throws SQLException {
		dao.insert(user);
	}

	@Override
	public void modify(UserDto user) throws SQLException {
		dao.modify(user);
	}

	@Override
	public UserDto select(UserDto user) throws SQLException {
		return dao.select(user);
	}
	@Override
	public UserDto selectid(UserDto user) throws SQLException {
		return dao.selectid(user);
	}

}
