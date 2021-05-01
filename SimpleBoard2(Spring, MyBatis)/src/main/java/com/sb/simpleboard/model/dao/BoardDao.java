package com.sb.simpleboard.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sb.simpleboard.model.dto.BoardDto;

public interface BoardDao {
	//게시글 작성
	void insert(BoardDto board) throws SQLException;
	//게시글 수정
	void modify(BoardDto board) throws SQLException;
	//게시글 삭제
	void delete(BoardDto board) throws SQLException;
	//게시글 목록
	List<BoardDto> selectList(Map<String, Object> m)throws SQLException;
	//게시글 보기
	BoardDto select(BoardDto board)throws SQLException;
	//페이지네이션 총 페이지수
	int getTotalCount(Map<String, Object> m) throws SQLException;
}
