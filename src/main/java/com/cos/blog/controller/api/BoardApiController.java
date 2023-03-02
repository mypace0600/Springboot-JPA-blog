package com.cos.blog.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.contract.ReplySaveRequestDto;
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
		service.updateById(id,principal.getUser(),requestBoard);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}


	// 데이터를 받을 때 컨트롤러에서 DTO 를 만들어서 받는게 좋다.
	@PostMapping("/api/board/{boardId}/reply")
	public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto dto) {
		service.replySave(dto);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@DeleteMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyDelete(@PathVariable int replyId, @AuthenticationPrincipal PrincipalDetail principal) throws
			Exception {
		service.replyDelete(replyId,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@PutMapping("/api/board/{boardId}/reply/{replyId}")
	public ResponseDto<Integer> replyUpdate(@PathVariable int replyId, @RequestBody Reply requestReply, @AuthenticationPrincipal PrincipalDetail principal) throws
			Exception {
		requestReply.setId(replyId);
		service.replyUpdate(requestReply,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

}
