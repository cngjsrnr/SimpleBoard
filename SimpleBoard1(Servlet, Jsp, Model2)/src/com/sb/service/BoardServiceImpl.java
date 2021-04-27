package com.sb.service;

import java.sql.SQLException;
import java.util.List;

import com.sb.dao.BoardDao;
import com.sb.dao.BoardDaoImpl;
import com.sb.dto.BoardDto;

public class BoardServiceImpl implements BoardService {
	static private BoardService instance=null;
	private BoardDao dao=null;
	private BoardServiceImpl() {
		if(dao==null)
			dao=BoardDaoImpl.getInstance();
	}
	
	static public BoardService getInstance() {
		if(instance==null)
			instance=new BoardServiceImpl();
		return instance;
	}

	@Override
	public int insert(BoardDto board) throws SQLException {
		return dao.insert(board);
	}

	@Override
	public int modify(BoardDto board) throws SQLException {
		return dao.modify(board);
	}

	@Override
	public int delete(BoardDto board) throws SQLException {
		return dao.delete(board);
	}

	@Override
	public List<BoardDto> select() throws SQLException {
		return dao.select();
	}

	@Override
	public BoardDto select(BoardDto board) throws SQLException {
		return dao.select(board);
	}

}
