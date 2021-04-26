package com.sb.service;

import java.sql.SQLException;

import com.sb.dto.UserDto;

public interface UserService {
	//회원가입
	void insert(UserDto user)throws SQLException;
	//회원정보 수정
	void modify(UserDto user)throws SQLException;
	//회원 탈퇴
//	void delete(UserDto user)throws SQLException;
	//로그인
	UserDto select(UserDto user)throws SQLException;
}
