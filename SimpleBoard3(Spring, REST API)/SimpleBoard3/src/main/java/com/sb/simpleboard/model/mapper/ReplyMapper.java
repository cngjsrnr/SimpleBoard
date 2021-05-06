package com.sb.simpleboard.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.sb.simpleboard.model.dto.ReplyDto;

public interface ReplyMapper {
	public List<ReplyDto> listReply(int bno)throws SQLException;
	public void modifyReply(ReplyDto replyDto) throws SQLException;
	public void deleteReply(int rno) throws SQLException;
	public void writeReply(ReplyDto replyDto) throws SQLException;
}
