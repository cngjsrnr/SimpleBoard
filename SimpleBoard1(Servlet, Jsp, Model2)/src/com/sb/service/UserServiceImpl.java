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
	public int insert(UserDto user) throws SQLException {
		if(user.getUid()=="" || user.getUpass()=="") {
			return 0;
		}
		return dao.insert(user);
	}

	@Override
	public int modify(UserDto user) throws SQLException {
		return dao.modify(user);
	}

	@Override
	public UserDto select(UserDto user) throws SQLException {
		return dao.select(user);
	}
	@Override
	public int selectid(UserDto user) throws SQLException {
		return dao.selectid(user);
	}

}
