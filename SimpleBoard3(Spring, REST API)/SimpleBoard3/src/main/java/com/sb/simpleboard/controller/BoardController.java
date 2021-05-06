package com.sb.simpleboard.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sb.simpleboard.model.dto.BoardDto;
import com.sb.simpleboard.model.dto.UserDto;
import com.sb.simpleboard.model.service.BoardService;
import com.sb.simpleboard.util.PageNavigation;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService bs;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	

//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@PostMapping("/write")
	public String write(@ModelAttribute BoardDto article,BindingResult br,HttpSession session,RedirectAttributes attr) {
		if(br.hasErrors()) {
			attr.addFlashAttribute("msg", "글 작성 실패 잠시후 다시 시도해 주세요");
			return "redirect:/index";
		}
		UserDto user=(UserDto)session.getAttribute("user");
		article.setBauthor(user.getUname());
		article.setBauthorid(user.getUid());
		try {
			if(bs.insert(article)) {
				attr.addFlashAttribute("msg", "글 작성 성공");				
			}else {
				attr.addFlashAttribute("msg", "글 작성 실패 잠시후 다시 시도해 주세요");
			}				
		} catch (SQLException e) {
			return "redirect:/error500";
		}		
		return "redirect:/index";
	}
	
	@GetMapping("/list")
	public String list(@RequestParam(defaultValue = "1") int pg, @RequestParam(defaultValue="") String key,@RequestParam(defaultValue="") String word,Model model) {
		List<BoardDto> list=null;
		try {
			list=bs.selectList(pg,key,word);

			model.addAttribute("boards", list);
			PageNavigation navigation = bs.makeNavigator(pg, key, word);
			model.addAttribute("navigator", navigation);
			model.addAttribute("pg", pg);
			model.addAttribute("key", key);
			model.addAttribute("word", word);			
		} catch (SQLException e) {
			model.addAttribute("msg","검색 중 에러가 발생하였습니다");
		}
		
		return "index";	
	}
	
	@GetMapping("/info")
	public String info(@ModelAttribute BoardDto article, BindingResult br,RedirectAttributes attr, Model model) {
		if(br.hasErrors()) {
			attr.addFlashAttribute("msg", "글을 불러오는중 오류가 발생했습니다 잠시후 다시 시도해 주세요");
			return "redirect:/index";
		}
		BoardDto ret=null;
		try {
			ret=bs.select(article);
			if(ret==null) {
				model.addAttribute("msg", "글을 읽어오는중 오류가 발생하였습니다 잠시후 다시 시도해 주세요");
				return "redirect:/index";
			}else {
				model.addAttribute("article", ret);
			}				
		} catch (SQLException e) {
			return "redirect:/error500";
		}		
		return "board/articleinfo";
	}
	
	@PostMapping("/modify")
	public String modify(@ModelAttribute BoardDto article,BindingResult br ,HttpSession session,RedirectAttributes attr) {
		if(br.hasErrors()) {
			attr.addFlashAttribute("msg", "글 수정 실패 잠시후 다시 시도해 주세요");
			return "redirect:/index";
		}
		UserDto user=(UserDto)session.getAttribute("user");
		article.setBauthor(user.getUname());
		article.setBauthorid(user.getUid());		

		try {
			
			if(bs.modify(article)) {
				attr.addFlashAttribute("msg", "글 수정 성공");
				attr.addAttribute("bno",article.getBno());
			}else {			
				attr.addFlashAttribute("msg", "글 수정 실패 잠시후 다시 시도해 주세요");
				return "redirect:/index";
			}				
		} catch (SQLException e) {
			return "redirect:/error500";
		}		
		return "redirect:/board/info";
	}
	
	@GetMapping("/delete")
	public String delete(@ModelAttribute BoardDto article, BindingResult br,RedirectAttributes attr) {
		if(br.hasErrors()) {
			attr.addFlashAttribute("msg", "글 삭제 실패 잠시후 다시 시도해 주세요");
			return "redirect:index";
		}
		try {
			bs.delete(article);
			attr.addFlashAttribute("msg", "글 삭제 성공");
				
		} catch (SQLException e) {
			return "redirect:/error500";
		}		
		return "redirect:/index";
	}
	
	@GetMapping("/mvwrite")
	public String mvwrite() {
		return "board/write";		
	}
	@GetMapping("/mvmodify")
	public String mvmodify(@RequestParam int bno, Model model,RedirectAttributes attr) {		
		BoardDto article=new BoardDto();
		article.setBno(bno);
		
		try {
			article=bs.select(article);
			model.addAttribute("article",article);
		} catch (SQLException e) {
			attr.addFlashAttribute("msg", "해당 글을 찾을 수 없습니다");			
			return "redirect:/index";
		}		
		return "board/modify";		
	}	
}
