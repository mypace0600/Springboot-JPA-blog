package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.contract.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardApiService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardApiService service;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		service.save(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}


}
