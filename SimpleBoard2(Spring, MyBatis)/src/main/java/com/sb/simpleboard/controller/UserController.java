package com.sb.simpleboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sb.simpleboard.model.dto.UserDto;
import com.sb.simpleboard.model.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService us;

	@PostMapping("/login")
	public String login(@ModelAttribute UserDto user, HttpSession session, RedirectAttributes attr) {
		UserDto userinfo=null;
		try {
			userinfo=us.select(user);
			if(userinfo==null) {
				attr.addFlashAttribute("msg", "아이디 또는 패스워드를 확인해 주세요");
				return "redirect:/index";
			}else
				session.setAttribute("user", userinfo);
		} catch (SQLException e) {
			return "redirect:/error500";
		}
		return "redirect:/index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession s) {
		s.invalidate();
		return "redirect:/index";
	}
	
	@PostMapping("/regist")
	public String userregist(@ModelAttribute UserDto user,BindingResult br ,RedirectAttributes attr){
		if(br.hasErrors()) {
			attr.addFlashAttribute("msg", "회원정보를 확인해 주세요");
			return "redirect:/user/mvregist";
		}
		try {
			if(us.insert(user)) {
				attr.addFlashAttribute("msg", "회원가입 완료! 로그인 후 이용해 주세요");
			}else {
				attr.addFlashAttribute("msg", "회원정보를 확인해 주세요");
				return "redirect:/user/mvregist";
			}
		} catch (SQLException e) {
			return "redirect:/error500";
		}
		return "redirect:/index";
	}
	
	@GetMapping("/isexistid")
	@ResponseBody
	public String isexistid(@ModelAttribute UserDto user,HttpServletResponse res) {
		UserDto ret=null;
		try(PrintWriter pw=res.getWriter()) {
			ret=us.selectid(user);
			
			if(ret!=null) {
				pw.print("f");
			}else {
				pw.print("t");
			}
			
		} catch (SQLException | IOException e) {			
		}
		
		return null;
	}
	
	@PostMapping("/modify")
	public String usermodify(@ModelAttribute UserDto user,BindingResult br ,RedirectAttributes attr,HttpSession s) {
		if(br.hasErrors()) {
			attr.addFlashAttribute("msg", "회원정보를 확인해 주세요");
			return "redirect:/user/mvmodify";
		}
		try {
			if(us.modify(user)) {
				attr.addFlashAttribute("msg", "수정완료");
				s.setAttribute("user", user);
			}else {
				attr.addFlashAttribute("msg", "회원정보를 확인해 주세요");;
				return "redirect:/user/mvmodify";
			}
		} catch (SQLException e) {
			return "redirect:/error500";
		}
		return "redirect:/index";
	}
	
	@GetMapping("mvregist")
	public String mvregist() {
		return "user/regist";
	}	
	@GetMapping("mvmodify")
	public String mvmodify() {
		return "user/modify";
	}

	
}
