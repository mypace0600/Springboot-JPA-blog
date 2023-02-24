package com.cos.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;

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

	public Page<Board> getList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
}