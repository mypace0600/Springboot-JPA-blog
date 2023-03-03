package com.cos.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardApiService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

	@Autowired
	private BoardApiService service;
	@GetMapping({"","/"})
	public String index(Model model,@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		model.addAttribute("boards",service.getList(pageable));
		return "index"; // @Controller의 경우 viewResolver 작동되어 해당 페이지로 model의 정보를 들고 이동함
	}

	@GetMapping("/board/saveForm")
	public String saveForm(){
		return "board/saveForm";
	}

	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model, HttpServletRequest request, @AuthenticationPrincipal
						   PrincipalDetail principal){
		Board board = null;
		try {
			board = service.boardDetail(id, principal.getUser());
		} catch (Exception e) {
			return "/board/error";
		}
		model.addAttribute("board",board);
		service.updateCount(id,request,principal.getUser());
		return "/board/detail";
	}

	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model, @AuthenticationPrincipal
	PrincipalDetail principal){
		try {
			model.addAttribute("board",service.boardDetail(id, principal.getUser()));
		} catch (Exception e) {
			return "/board/error";
		}
		return "/board/updateForm";
	}

}
