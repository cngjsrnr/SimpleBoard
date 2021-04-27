package com.sb.dao;

import java.sql.SQLException;

import com.sb.dto.UserDto;

public interface UserDao {
	//회원가입
	int insert(UserDto user)throws SQLException;
	//회원정보 수정
	int modify(UserDto user)throws SQLException;
	//회원 탈퇴
//	int delete(UserDto user)throws SQLException;
	//로그인
	UserDto select(UserDto user)throws SQLException;
	//존재하는 아이디인지 확인
	UserDto selectid(UserDto user) throws SQLException;
}