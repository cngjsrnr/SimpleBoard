package com.sb.service;

import java.sql.SQLException;

import com.sb.dao.UserDao;
import com.sb.dao.UserDaoImpl;
import com.sb.dto.UserDto;

public class UserServiceImpl implements UserService {
	static private UserService instance=null;
	private UserDao dao=null;
	private UserServiceImpl() {
		if(dao==null)
			dao=UserDaoImpl.getInstance();
	}
	static public UserService getInstance() {
		if(instance==null)
			instance=new UserServiceImpl();
		return instance;
	}

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

}
