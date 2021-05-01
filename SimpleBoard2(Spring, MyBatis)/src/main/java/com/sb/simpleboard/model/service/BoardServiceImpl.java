package com.sb.simpleboard.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.simpleboard.model.dao.BoardDao;
import com.sb.simpleboard.model.dao.BoardDaoImpl;
import com.sb.simpleboard.model.dto.BoardDto;
import com.sb.simpleboard.util.PageConstance;
import com.sb.simpleboard.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;

	@Override
	public void insert(BoardDto board) throws SQLException {
		dao.insert(board);
	}

	@Override
	public void modify(BoardDto board) throws SQLException {
		dao.modify(board);
	}

	@Override
	public void delete(BoardDto board) throws SQLException {
		dao.delete(board);
	}

	@Override
	public List<BoardDto> selectList(int pg,String key, String word) throws SQLException {
		
		int start = (pg-1)*PageConstance.LIST_SIZE;
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("start", start);
		m.put("key", key);
		m.put("word", word);
		m.put("spp", PageConstance.LIST_SIZE);
		return dao.selectList(m);
	}

	@Override
	public BoardDto select(BoardDto board) throws SQLException {
		return dao.select(board);
	}
	
	@Override
	public PageNavigation makeNavigator(int pg, String key, String word)  throws SQLException {
		Map<String, Object> m=new HashMap<String, Object>();
		key = key == null ? "" : key;
		word = word == null ? "" : word;
		m.put("key", key);
		m.put("word", word);
		
		int naviSize = PageConstance.NAVI_SIZE;
		PageNavigation navigation = new PageNavigation();
		int totalCount = dao.getTotalCount(m);
		navigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount-1)/PageConstance.LIST_SIZE+1;//190 개 글이 있을때 189/10 +1 해준거임
		navigation.setTotalPageCount(totalPageCount);
		navigation.setCurrentPage(pg);
		navigation.setNaviSize(naviSize);
		navigation.setStartRange(pg<= naviSize);//이전페이지 못누르게 하는거 (현재페이지가 10이하이면 이전페이지 누르면 음수니까 그거 못하게하는거임)
		navigation.setEndRange(((totalPageCount-1)/naviSize*naviSize<pg));// 다음페이지 누를때 현재페이지가 몇페이지일때 못누르게하는지(28페이지까지 있으면 20페이지까지만 다음페이지 누를수잇음)
		return navigation;
	}

}
