package com.sb.simpleboard.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.simpleboard.model.dto.ReplyDto;
import com.sb.simpleboard.model.service.ReplyService;

@RestController
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ReplyService rs;

	
	@GetMapping(value = "/reply/{bno}")
	public ResponseEntity<List<ReplyDto>> listReply(@PathVariable("bno") int bno) {
		List<ReplyDto> list;
		try {
			list = rs.listReply(bno);
		} catch (SQLException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}	
		
		if(list != null && !list.isEmpty()) {
			return new ResponseEntity<List<ReplyDto>>(list, HttpStatus.OK);//넘겨야할 데이터와 상태코드를 리턴해줌		
			
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value = "/reply")
	public ResponseEntity writeReply(@RequestBody ReplyDto replyDto) {
		try {
			boolean ret=rs.writeReply(replyDto);
			if(!ret)
				return new ResponseEntity(HttpStatus.BAD_REQUEST);//400
		} catch (SQLException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}	
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PutMapping(value = "/reply")
	public ResponseEntity modifyReply(@RequestBody ReplyDto replyDto) {
		try {
			boolean ret=rs.modifyReply(replyDto);
			if(!ret)
				return new ResponseEntity(HttpStatus.BAD_REQUEST);//400
		} catch (SQLException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}	
		return new ResponseEntity(HttpStatus.OK);
	}
	@DeleteMapping(value = "/reply/{rno}")
	public ResponseEntity modifyReply(@PathVariable("rno") int rno) {
		try {
			rs.deleteReply(rno);
		} catch (SQLException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}	
		return new ResponseEntity(HttpStatus.OK);
	}	
}