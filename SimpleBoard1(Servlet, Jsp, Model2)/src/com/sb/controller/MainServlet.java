package com.sb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sb.dto.BoardDto;
import com.sb.dto.UserDto;
import com.sb.service.BoardService;
import com.sb.service.BoardServiceImpl;
import com.sb.service.UserService;
import com.sb.service.UserServiceImpl;
import com.sb.util.PageNavigation;
import com.sb.util.ValidationCheck;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet({"/main","/"})
public class MainServlet extends HttpServlet {
	private UserService us=UserServiceImpl.getInstance();
	private BoardService bs=BoardServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act=request.getParameter("act");
		String root=request.getContextPath();
		String path="/main";
		if(act==null)
			act="";

		if(act.equals("login")) {//로그인
			path=login(request, response);
		}else if(act.equals("logout")) {//로그아웃
			request.getSession().invalidate();
			path="/main";
		}else if(act.equals("userregist")) {//회원가입
			path=userregist(request,response);
		}else if(act.equals("isexistid")) {//이미 존재하는 id인지 확인
			isexistid(request,response);
			return;
		}else if(act.equals("usermodify")) {//회원정보수정
			path=usermodify(request,response);
		}else if(act.equals("articlewrite")) {//게시글 작성
			articlewrite(request,response);
		}else if(act.equals("getboardlist")) {//게시글 목록
			getlist(request,response);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}else if(act.equals("articleinfo")) {//게시글 보기
			path=articleinfo(request,response);
			if(path.equals("")) {
				request.getRequestDispatcher("/board/articleinfo.jsp").forward(request, response);
				return;
			}
		}else if(act.equals("modifyboard")) {//게시글 수정
			modifyboard(request,response);
		}else if(act.equals("deleteboard")) {//게시글 삭제
			deleteboard(request,response);
		}
		
		else if(act.equals("mvlogin")){
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}else if(act.equals("mvuserregist")){
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}else if(act.equals("mvusermodify")){
			request.getRequestDispatcher("/user/modify.jsp").forward(request, response);
			return;
		}else if(act.equals("mvarticlewrite")){
			request.getRequestDispatcher("/board/write.jsp").forward(request, response);
			return;
		}else if(act.equals("mvmodifyboard")){
			request.getRequestDispatcher("/board/modify.jsp").forward(request, response);
			return;
		}else if(act.equals("err500")){
			request.getRequestDispatcher("/error/err500.jsp").forward(request, response);
			return;
		}else if(act.equals("")) {//첫페이지에서 게시물 목록 보여줘
			getlist(request,response);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(root+path);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	
	private String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDto user=new UserDto();
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		user.setUid(id);
		user.setUpass(pass);
		UserDto userinfo=null;
		try {
			userinfo=us.select(user);
			if(userinfo==null) {
				request.getSession().setAttribute("msg", "아이디 또는 패스워드를 확인해 주세요");
				return "/main?act=mvlogin";
			}else
				request.getSession().setAttribute("user", userinfo);
		} catch (SQLException e) {
			return "/main?act=err500";
		}
		return "/main";
	}
	private String userregist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		UserDto user=new UserDto();
		String id=request.getParameter("id");
		String pass=request.getParameter("password");
		String name=request.getParameter("name");
		user.setUid(id);
		user.setUpass(pass);
		user.setUname(name);
		int ret=0;
		try {
			ret=us.insert(user);
			if(ret==0) {
				request.getSession().setAttribute("msg", "회원정보를 확인해 주세요");
				return "/main?act=mvuserregist";
			}else {
				request.getSession().setAttribute("msg", "회원가입 완료! 로그인 후 이용해 주세요");
			}
		} catch (SQLException e) {
			return "/main?act=err500";
		}
		return "/main";
	}	
	private String isexistid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		UserDto user=new UserDto();
		String id=request.getParameter("id");
		user.setUid(id);
		UserDto ret=null;
		PrintWriter pw=response.getWriter();
		try {
			ret=us.selectid(user);
			
			if(ret!=null) {
				pw.print("f");
			}else {
				pw.print("t");
			}
			
		} catch (SQLException e) {			
		}finally {
			pw.close();
		}
		
		return "";
	}
	private String usermodify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		UserDto user=new UserDto();
		String id=request.getParameter("id");
		String pass=request.getParameter("password");
		String name=request.getParameter("name");
		user.setUid(id);
		user.setUpass(pass);
		user.setUname(name);
		int ret=0;
		try {
			ret=us.modify(user);
			if(ret==0) {
				request.getSession().setAttribute("msg", "회원정보를 확인해 주세요");
				return "/main?act=mvusermodify";
			}else {
				request.getSession().setAttribute("msg", "수정완료");
			}
		} catch (SQLException e) {
			return "/main?act=err500";
		}
		return "/main";
	}	
	
	
	private String getlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<BoardDto> list=null;
		int pg = ValidationCheck.NotNumberToOne(request.getParameter("pg"));
		String key = request.getParameter("key");
		String word = request.getParameter("word");
		try {
			list=bs.select(pg,key,word);

			request.getSession().setAttribute("boards", list);
			PageNavigation navigation = bs.makeNavigator(pg, key, word);
			request.setAttribute("navigator", navigation);
			request.setAttribute("pg", pg);
			request.setAttribute("key", key);
			request.setAttribute("word", word);
		} catch (SQLException e) {
			
		}
		
		return"";
	}		
	private String articlewrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		UserDto user=(UserDto)request.getSession().getAttribute("user");
		BoardDto article=new BoardDto();
		article.setBauthor(user.getUname());
		article.setBtitle(request.getParameter("title"));
		article.setBcontent(request.getParameter("content"));
		int ret=0;
		try {
			ret=bs.insert(article);
			if(ret==0) {
				request.getSession().setAttribute("msg", "글 작성 실패 잠시후 다시 시도해 주세요");
			}else {
				request.getSession().setAttribute("msg", "글 작성 성공");
			}				
		} catch (SQLException e) {
			return "/main?act=err500";
		}		
		return "/main";
	}
	private String articleinfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		BoardDto article=new BoardDto();
		article.setBno(Integer.parseInt(request.getParameter("bno")));
		BoardDto ret=null;
		try {
			ret=bs.select(article);
			if(ret==null) {
				request.getSession().setAttribute("msg", "글을 읽어오는중 오류가 발생하였습니다 잠시후 다시 시도해 주세요");
				return "/main";
			}else {
				request.getSession().setAttribute("article", ret);
				return "";
			}				
		} catch (SQLException e) {
			return "/main?act=err500";
		}		
	}
	private String modifyboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		UserDto user=(UserDto)request.getSession().getAttribute("user");
		BoardDto article=new BoardDto();
		article.setBauthor(user.getUname());
		article.setBno(Integer.parseInt(request.getParameter("bno")));
		article.setBtitle(request.getParameter("title"));
		article.setBcontent(request.getParameter("content"));
		int ret=0;
		try {
			ret=bs.modify(article);
			if(ret==0) {
				request.getSession().setAttribute("msg", "글 수정 실패 잠시후 다시 시도해 주세요");
			}else {
				request.getSession().setAttribute("msg", "글 수정 성공");
			}				
		} catch (SQLException e) {
			return "/main?act=err500";
		}		
		return "/main?act=articleinfo&bno="+article.getBno();
	}	
	private String deleteboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		BoardDto article=new BoardDto();

		article.setBno(Integer.parseInt(request.getParameter("bno")));
		int ret=0;
		try {
			ret=bs.delete(article);
			if(ret==0) {
				request.getSession().setAttribute("msg", "글 삭제 실패 잠시후 다시 시도해 주세요");
			}else {
				request.getSession().setAttribute("msg", "글 삭제 성공");
			}				
		} catch (SQLException e) {
			return "/main?act=err500";
		}		
		return "/main";
	}

}
