package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.contract.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserApiService;

@RestController
public class UserApiController {

	@Autowired
	private UserApiService service;

	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user){
		int result = service.signIn(user);
		return new ResponseDto<Integer>(HttpStatus.OK,result);
	}
}
