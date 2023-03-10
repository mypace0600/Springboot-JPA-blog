package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.contract.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.AdminApiService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminApiController {

	private final AdminApiService service;

	@PutMapping("/admin/role/")
	public ResponseDto<Integer> adminRole(@RequestBody User user) throws Exception{
		try {
			service.adminRoleUpdate(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
