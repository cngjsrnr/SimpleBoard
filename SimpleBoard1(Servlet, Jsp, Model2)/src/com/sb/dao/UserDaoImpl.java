package com.sb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sb.dto.UserDto;
import com.sb.util.DBUtil;

public class UserDaoImpl implements UserDao {
	static private UserDao instance=null;
	private DBUtil util=null;
	private UserDaoImpl() {
		if(util==null)
			util=DBUtil.getUtil();
	}
	static public UserDao getInstance() {
		if(instance==null)
			instance=new UserDaoImpl();
		return instance;
	}
	
	@Override
	public int insert(UserDto user) throws SQLException {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		int ret=0;
		try {
			conn=util.getConnect();
			String sql="insert into user(uid, upass,uname) values(?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getUpass());
			pstmt.setString(3, user.getUname());
			ret=pstmt.executeUpdate();
		}finally {
			util.close(pstmt,conn);
		}
		return ret;
	}

	@Override
	public int modify(UserDto user) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int ret=0;
		try {
			conn=util.getConnect();
			String sql="update user set upass=?, uname=? where uid=?";
			pstmt=conn.prepareStatement(sql);			
			pstmt.setString(1, user.getUpass());
			pstmt.setString(2, user.getUname());
			pstmt.setString(3, user.getUid());
			ret=pstmt.executeUpdate();
		}finally {
			util.close(pstmt,conn);
		}
		return ret;
	}

	@Override
	public UserDto select(UserDto user) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UserDto ret=null;
		try {
			conn=util.getConnect();
			String sql="select uname where uid=? and upass=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUid());
			pstmt.setString(2, user.getUpass());
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ret=new UserDto();
				ret.setUid(user.getUid());
				ret.setUpass(user.getUpass());
				ret.setUname(user.getUname());				
			}
		}finally {
			util.close(rs,pstmt,conn);
		}
		return ret;
	}
}
