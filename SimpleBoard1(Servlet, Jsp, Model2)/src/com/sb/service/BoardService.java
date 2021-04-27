package com.sb.service;

import java.sql.SQLException;
import java.util.List;

import com.sb.dto.BoardDto;
import com.sb.util.PageNavigation;

public interface BoardService {
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
	//페이지내이션
	PageNavigation makeNavigator(int pg, String key, String word)throws SQLException;
}
