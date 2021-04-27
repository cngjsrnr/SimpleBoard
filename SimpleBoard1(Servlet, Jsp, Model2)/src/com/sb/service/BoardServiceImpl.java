package com.sb.service;

import java.sql.SQLException;
import java.util.List;

import com.sb.dao.BoardDao;
import com.sb.dao.BoardDaoImpl;
import com.sb.dto.BoardDto;
import com.sb.util.PageConstance;
import com.sb.util.PageNavigation;

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
	public List<BoardDto> select(int pg,String key, String word) throws SQLException {
		
		int start = (pg-1)*PageConstance.LIST_SIZE;
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		return dao.select(start,key,word);
	}

	@Override
	public BoardDto select(BoardDto board) throws SQLException {
		return dao.select(board);
	}
	
	@Override
	public PageNavigation makeNavigator(int pg, String key, String word)  throws SQLException {
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		int naviSize = PageConstance.NAVI_SIZE;
		PageNavigation navigation = new PageNavigation();
		int totalCount = dao.getTotalCount(key, word);
		System.out.println("totalCount "+totalCount);
		navigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount-1)/PageConstance.LIST_SIZE+1;//190 개 글이 있을때 189/10 +1 해준거임
		System.out.println("totalPageCount "+totalPageCount);
		navigation.setTotalPageCount(totalPageCount);
		navigation.setCurrentPage(pg);
		navigation.setNaviSize(naviSize);
		navigation.setStartRange(pg<= naviSize);//이전페이지 못누르게 하는거 (현재페이지가 10이하이면 이전페이지 누르면 음수니까 그거 못하게하는거임)
		navigation.setEndRange(((totalPageCount-1)/naviSize*naviSize<pg));// 다음페이지 누를때 현재페이지가 몇페이지일때 못누르게하는지(28페이지까지 있으면 20페이지까지만 다음페이지 누를수잇음)
//		navigation.makeNavigator();
		return navigation;
	}

}
