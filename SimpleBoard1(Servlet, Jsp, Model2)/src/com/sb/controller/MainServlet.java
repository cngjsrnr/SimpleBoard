package com.sb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act=request.getParameter("act");
		
		if(act==null)
			act="";
		
		if(act.equals("login")) {//로그인
			
		}else if(act.equals("logout")) {//로그아웃
			
		}else if(act.equals("regist")) {//회원가입
			
		}else if(act.equals("getlist")) {//게시글 목록
			
		}else if(act.equals("getboardinfo")) {//게시글 보기
			
		}else if(act.equals("modifyboard")) {//게시글 수정
		
		}else if(act.equals("deleteboard")) {//게시글 삭제
			
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
