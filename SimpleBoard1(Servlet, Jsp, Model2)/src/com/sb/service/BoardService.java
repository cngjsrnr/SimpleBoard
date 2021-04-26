package com.sb.service;

import java.sql.SQLException;
import java.util.List;

import com.sb.dto.BoardDto;

public interface BoardService {
	//게시글 작성
	void insert(BoardDto board) throws SQLException;
	//게시글 수정
	void modify(BoardDto board) throws SQLException;
	//게시글 삭제
	void delete(BoardDto board) throws SQLException;
	//게시글 목록
	List<BoardDto> select()throws SQLException;
	//게시글 보기
	BoardDto select(BoardDto board)throws SQLException;

}
