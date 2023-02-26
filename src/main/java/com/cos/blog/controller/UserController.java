package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증 안된 사용자들은  auth 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 내용 허용

@Controller
public class UserController {
	@GetMapping("/auth/joinForm")
	public String joinForm(){
		return "user/joinForm";
	}
	@GetMapping("/auth/loginForm")
	public String loginForm(){
		return "user/loginForm";
	}

	@GetMapping("/user/updateForm")
	public String updateForm(){ return "user/updateForm";}
}
