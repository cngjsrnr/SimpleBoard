package com.sb.simpleboard.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.simpleboard.model.dto.ReplyDto;
import com.sb.simpleboard.model.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ReplyDto> listReply(int bno) throws SQLException {
		return sqlSession.getMapper(ReplyMapper.class).listReply(bno);
	}

	@Override
	public boolean modifyReply(ReplyDto replyDto) throws SQLException {
		replyDto.setRcontent(replyDto.getRcontent().trim());
		if(replyDto.getRno()<=0 || replyDto.getRcontent().length()==0)
			return false;
		sqlSession.getMapper(ReplyMapper.class).modifyReply(replyDto);
		return true;
	}

	@Override
	public void deleteReply(int rno) throws SQLException {
		sqlSession.getMapper(ReplyMapper.class).deleteReply(rno);
	}

	@Override
	public boolean writeReply(ReplyDto replyDto) throws SQLException {
		replyDto.setRcontent(replyDto.getRcontent().trim());
		if(replyDto.getBno()<=0 || replyDto.getUid()==null || replyDto.getRcontent().length()==0)
			return false;
		sqlSession.getMapper(ReplyMapper.class).writeReply(replyDto);
		return true;
	}
}
