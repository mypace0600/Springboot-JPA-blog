package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
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

	@PutMapping("/admin/role/request")
	public ResponseDto<Integer> adminRoleRequest(@AuthenticationPrincipal PrincipalDetail principal) throws Exception{
		try {
			User user = service.adminRoleRequest(principal);
			principal.getUser().setRole(user.getRole());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@PutMapping("/admin/role/{id}")
	public ResponseDto<Integer> updateAdminRole(@PathVariable int id, @RequestBody User requestUser) throws Exception{
		try {
			service.updateAdminRole(id,requestUser);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@DeleteMapping("/admin/role/{id}")
	public ResponseDto<Integer> deleteUser(@PathVariable int id) throws Exception{
		service.deleteUser(id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
