package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.contract.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserApiService;

@RestController
public class UserApiController {

	@Autowired
	private UserApiService service;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user){
		user.setRole(RoleType.USER);
		service.signUp(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	// 전통적 방식의 로그인 구현
	/*@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
		User principal = service.signIn(user); // 접근 주체
		if(principal != null){
			session.setAttribute("principal",principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}*/

}
