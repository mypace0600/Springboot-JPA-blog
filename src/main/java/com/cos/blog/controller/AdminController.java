package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.RoleType;
import com.cos.blog.service.AdminApiService;

@Controller
public class AdminController {

	@Autowired
	private AdminApiService service;

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
}
