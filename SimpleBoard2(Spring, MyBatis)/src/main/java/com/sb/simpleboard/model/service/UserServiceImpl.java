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
	public boolean insert(UserDto user) throws SQLException {
		if(user.getUid()=="" || user.getUname()=="" || user.getUpass()=="0")
			return false;
		dao.insert(user);
		return true;
	}

	@Override
	public boolean modify(UserDto user) throws SQLException {
		if(user.getUid()=="" || user.getUname()=="" || user.getUpass()=="0")
			return false;
		dao.modify(user);
		return true;
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
