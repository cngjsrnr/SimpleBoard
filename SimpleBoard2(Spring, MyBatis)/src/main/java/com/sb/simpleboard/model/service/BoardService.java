package com.sb.simpleboard.model.service;

import java.sql.SQLException;
import java.util.List;

import com.sb.simpleboard.model.dto.BoardDto;
import com.sb.simpleboard.util.PageNavigation;

public interface BoardService {
	//게시글 작성
	boolean insert(BoardDto board) throws SQLException;
	//게시글 수정
	boolean modify(BoardDto board) throws SQLException;
	//게시글 삭제
	void delete(BoardDto board) throws SQLException;
	//게시글 목록
	List<BoardDto> selectList(int start,String key, String word)throws SQLException;
	//게시글 보기
	BoardDto select(BoardDto board)throws SQLException;
	//페이지내이션
	PageNavigation makeNavigator(int pg, String key, String word)throws SQLException;
}
