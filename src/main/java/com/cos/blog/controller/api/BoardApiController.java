package com.cos.blog.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.contract.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardApiService;

@Slf4j
@RestController
public class BoardApiController {

	@Autowired
	private BoardApiService service;

	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principal) {
		service.save(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer> deleteById(@PathVariable int id,@AuthenticationPrincipal PrincipalDetail principal){
		service.deleteById(id,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer> updateById(@PathVariable int id, @RequestBody Board requestBoard, @AuthenticationPrincipal PrincipalDetail principal){
		System.out.println("@@@@@@@ id :{}"+id);
		System.out.println("@@@@@@@ requestBoard :{}"+requestBoard);
		System.out.println("@@@@@@@ user :{}"+principal.getUser());
		service.updateById(id,principal.getUser(),requestBoard);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
		service.replySave(boardId,reply,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
