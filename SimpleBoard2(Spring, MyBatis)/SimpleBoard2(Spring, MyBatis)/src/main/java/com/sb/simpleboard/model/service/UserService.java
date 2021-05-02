package com.sb.simpleboard.model.service;

import java.sql.SQLException;

import com.sb.simpleboard.model.dto.UserDto;

public interface UserService {
	//회원가입
	boolean insert(UserDto user)throws SQLException;
	//회원정보 수정
	boolean modify(UserDto user)throws SQLException;
	//로그인
	UserDto select(UserDto user)throws SQLException;
	//존재하는 아이디인지 확인
	UserDto selectid(UserDto user) throws SQLException;
}
