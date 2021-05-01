package com.sb.simpleboard.model.mapper;

import java.sql.SQLException;

import com.sb.simpleboard.model.dto.UserDto;

public interface UserMapper {
	//회원가입
	void insert(UserDto user)throws SQLException;
	//회원정보 수정
	void modify(UserDto user)throws SQLException;
	//회원 탈퇴
//	int delete(UserDto user)throws SQLException;
	//로그인
	UserDto select(UserDto user)throws SQLException;
	//존재하는 아이디인지 확인
	UserDto selectid(UserDto user) throws SQLException;
}