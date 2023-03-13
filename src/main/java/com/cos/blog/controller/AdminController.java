package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.service.AdminApiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminController {

	@Autowired
	private AdminApiService service;

	@GetMapping("/admin/main")
	public String adminMain(@AuthenticationPrincipal PrincipalDetail principal){
		if(principal.getUser().getRole().equals(RoleType.ADMIN)){
			return "admin/main";
		} else {
			return "admin/error";
		}
	}

	@GetMapping("/admin/users")
	public String adminUsers(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal
	PrincipalDetail principal) throws Exception{
		if(principal.getUser().getRole().equals(RoleType.ADMIN)) {
			model.addAttribute("users", service.getList(pageable));
		} else {
			model.addAttribute("user",principal.getUser());
			return "admin/error";
		}
		return "admin/users";
	}

	@GetMapping("/admin/user={id}/boards")
	public String adminUserBoards(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable int id, @AuthenticationPrincipal
	PrincipalDetail principal) throws Exception{
		if(principal.getUser().getRole().equals(RoleType.ADMIN)) {
			Page<Board> boardList = service.getBoardList(pageable, id);
			if(!boardList.isEmpty()){
				try {
					model.addAttribute("writer", service.getUser(id));
				} catch (Exception e){
					e.printStackTrace();
					throw new RuntimeException("해당 사용자가 없습니다.");
				}
				model.addAttribute("boards", boardList);
			} else {
				model.addAttribute("boards",null);
			}
		} else {
			model.addAttribute("user",principal.getUser());
			return "admin/error";
		}
		return "admin/userBoards";
	}

	@GetMapping("/admin/boards")
	public String adminFavoriteBoards(Model model, @PageableDefault(size = 10, sort = "count", direction = Sort.Direction.DESC) Pageable pageable){
		Page<Board> boardList = service.getFavoriteBoards(pageable);
		if(!boardList.isEmpty()){
			model.addAttribute("boards",boardList);
		} else {
			model.addAttribute("boards",null);
		}
		return "admin/adminBoards";
	}
}
