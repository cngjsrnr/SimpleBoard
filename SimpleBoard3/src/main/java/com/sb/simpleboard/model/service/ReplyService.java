package com.sb.simpleboard.model.service;

import java.sql.SQLException;
import java.util.List;

import com.sb.simpleboard.model.dto.ReplyDto;

public interface ReplyService {
	public List<ReplyDto> listReply(int bno)throws SQLException;
	public boolean modifyReply(ReplyDto replyDto) throws SQLException;
	public void deleteReply(int rno) throws SQLException;
	public boolean writeReply(ReplyDto replyDto) throws SQLException;
}
