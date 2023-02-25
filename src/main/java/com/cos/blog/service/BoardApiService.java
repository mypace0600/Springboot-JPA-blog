package com.cos.blog.service;


import java.util.List;

import com.cos.blog.contract.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class BoardApiService {
	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void save(Board board, User user){
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	@Transactional(readOnly = true)
	public Page<Board> getList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}


	@Transactional(readOnly = true)
	public Board boardDetail(int id){
		return boardRepository.findById(id).orElseThrow(()->{return new IllegalArgumentException("글 상세보기 실패 : 게시글 정보를 찾을 수 없습니다.");});
	}

	@Transactional
	public void deleteById(int id,User user){
		int userId = user.getId();
		int writerId = boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 삭제 실패 : 작성자가 아닙니다.");
		}).getUser().getId();

		System.out.println("@@@@ userId : "+ userId);
		System.out.println("@@@@ writerId : "+ writerId);

		if(userId == writerId) {
			boardRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("글 삭제 실패 : 글 삭제 권한이 없습니다.");
		}
	}
}
