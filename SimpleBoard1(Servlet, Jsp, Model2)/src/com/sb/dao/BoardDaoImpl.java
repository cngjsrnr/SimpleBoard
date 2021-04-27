package com.sb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sb.dto.BoardDto;
import com.sb.util.DBUtil;

public class BoardDaoImpl implements BoardDao {
	static private BoardDao instance=null;
	private DBUtil util=null;
	private BoardDaoImpl() {
		if(util==null)
			util=DBUtil.getUtil();
	}
	
	static public BoardDao getInstance() {
		if(instance==null)
			instance=new BoardDaoImpl();
		return instance;
	}

	@Override
	public int insert(BoardDto board) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int ret=0;
		try {
			conn=util.getConnect();
			String sql="insert into board(bno, btitle, bcontent,bauthor) values(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBno());
			pstmt.setString(2, board.getBtitle());
			pstmt.setString(3, board.getBcontent());
			pstmt.setString(4, board.getBauthor());
			
			ret=pstmt.executeUpdate();
		}finally {
			util.close(pstmt,conn);
		}
		return ret;
	}

	@Override
	public int modify(BoardDto board) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int ret=0;
		try {
			conn=util.getConnect();
			String sql="update board set btitle=?, bcontent=?,bauthor=? where bno=?";
			pstmt=conn.prepareStatement(sql);
		
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontent());
			pstmt.setString(3, board.getBauthor());
			pstmt.setInt(4, board.getBno());
			
			ret=pstmt.executeUpdate();
		}finally {
			util.close(pstmt,conn);
		}
		return ret;
	}

	@Override
	public int delete(BoardDto board) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int ret=0;
		try {
			conn=util.getConnect();
			String sql="delete from board where bno=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBno());
			
			ret=pstmt.executeUpdate();
		}finally {
			util.close(pstmt,conn);
		}
		return ret;
	}

	@Override
	public List<BoardDto> select() throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardDto> ret=new ArrayList<>();
		try {
			conn=util.getConnect();
			String sql="select bno,btitle,bcontent,bauthor,bdate";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDto b=new BoardDto();
				b.setBno(rs.getInt(1));
				b.setBtitle(rs.getString(2));
				b.setBcontent(rs.getString(3));
				b.setBauthor(rs.getString(4));
				b.setBdate(rs.getString(5));
				ret.add(b);				
			}			
		}finally {
			util.close(rs,pstmt,conn);
		}
		return ret;
	}

	@Override
	public BoardDto select(BoardDto board) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BoardDto ret=null;
		try {
			conn=util.getConnect();
			String sql="select bno,btitle,bcontent,bauthor,bdate where bno=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBno());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ret=new BoardDto();
				ret.setBno(rs.getInt(1));
				ret.setBtitle(rs.getString(2));
				ret.setBcontent(rs.getString(3));
				ret.setBauthor(rs.getString(4));
				ret.setBdate(rs.getString(5));			
			}			
		}finally {
			util.close(rs,pstmt,conn);
		}
		return ret;
	}

}
