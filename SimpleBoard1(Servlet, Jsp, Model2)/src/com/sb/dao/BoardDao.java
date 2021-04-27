package com.sb.dao;

import java.sql.SQLException;
import java.util.List;

import com.sb.dto.BoardDto;

public interface BoardDao {
	//게시글 작성
	int insert(BoardDto board) throws SQLException;
	//게시글 수정
	int modify(BoardDto board) throws SQLException;
	//게시글 삭제
	int delete(BoardDto board) throws SQLException;
	//게시글 목록
	List<BoardDto> select(int start,String key, String word)throws SQLException;
	//게시글 보기
	BoardDto select(BoardDto board)throws SQLException;
	//페이지네이션 총 페이지수
	int getTotalCount(String key, String word) throws SQLException;
}
