package com.sb.simpleboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = {"/","/index","/main"})
	public String index() {
		return "forward:/board/list";
	}
	
	@RequestMapping("/error500")
	public String error500() {
		return "error/error500";
	}
}
