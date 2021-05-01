package com.sb.simpleboard.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.sb.simpleboard.model.dto.BoardDto;
import com.sb.simpleboard.util.SqlMapConfig;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	private final String NAMESPACE = "com.sb.simpleboard.model.dao.BoardDao.";

	@Override
	public void insert(BoardDto board) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.insert(NAMESPACE + "insert", board);//product.xml에 있는 namespace + id 
			sqlSession.commit();
		}
	}

	@Override
	public void modify(BoardDto board) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.update(NAMESPACE + "modify", board);
			sqlSession.commit();
		}
	}

	@Override
	public void delete(BoardDto board) throws SQLException {
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			sqlSession.delete(NAMESPACE + "delete", board);
			sqlSession.commit();
		}
	}

	@Override
	public List<BoardDto> selectList(Map<String, Object> m) throws SQLException {
		List<BoardDto> ret=null;
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			ret=sqlSession.selectList(NAMESPACE + "selectList", m);
		}
		return ret;
	}

	@Override
	public BoardDto select(BoardDto board) throws SQLException {
		BoardDto ret=null;
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			ret=sqlSession.selectOne(NAMESPACE + "select", board);
		}
		return ret;
	}

	@Override
	public int getTotalCount(Map<String, Object> m) throws SQLException {
		int cnt = 0;
		try(SqlSession sqlSession = SqlMapConfig.getSqlSession()) {
			cnt=sqlSession.selectOne(NAMESPACE + "totalCount", m);
		}
		return cnt;
	}
}
