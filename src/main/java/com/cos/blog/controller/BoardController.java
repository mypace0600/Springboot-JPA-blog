package com.cos.blog.controller;

import com.cos.blog.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardApiService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
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
		System.out.println("");
		return "board/saveForm";
	}

	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model){
		model.addAttribute("board",service.boardDetail(id));
		return "/board/detail";
	}

	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model){
		model.addAttribute("board",service.boardDetail(id));
		return "/board/updateForm";
	}

}
