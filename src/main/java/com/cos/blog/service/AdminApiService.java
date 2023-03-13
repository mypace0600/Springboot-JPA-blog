package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminApiService {

	private final UserRepository userRepository;
	private final BoardRepository boardRepository;

	@Transactional(readOnly = true)
	public Page<User> getList(Pageable pageable){
		return userRepository.findAll(pageable);
	}

	@Transactional
	public User adminRoleRequest(PrincipalDetail principal)throws Exception{
		User user = userRepository.findById(principal.getUser().getId()).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저가 없습니다.");
		});
		if(user.getRole().equals(RoleType.ADMIN)){
			throw new IllegalArgumentException("이미 관리자 입니다.");
		}
		if(user.getRole().equals(RoleType.REQUEST)){
			throw new IllegalArgumentException("이미 요청했습니다.");
		}
		user.setRole(RoleType.REQUEST);
		return user;
	}

	@Transactional
	public void updateAdminRole(int id, User requestUser)throws Exception{
		log.info("@@@@@@@@@ requestUser :{}",requestUser);
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저가 없습니다.");
		});
		user.setRole(requestUser.getRole());
	}
	@Transactional
	public void deleteUser(int id){
		userRepository.deleteById(id);
	}

	@Transactional
	public Page<Board> getBoardList(Pageable pageable, int id){
		return boardRepository.findAllByUserId(pageable,id);
	}

	public User getUser(int id){
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자가 없습니다.");
		});
		return user;
	}

	public Page<Board> getFavoriteBoards(Pageable pageable){
		return boardRepository.findAll(pageable);
	}

}
