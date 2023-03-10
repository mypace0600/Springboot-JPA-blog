package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminApiService {
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public Page<User> getList(Pageable pageable){
		return userRepository.findAll(pageable);
	}

	@Transactional
	public void adminRoleUpdate(User requestUser)throws Exception{
		User user = userRepository.findById(requestUser.getId()).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저가 없습니다.");
		});
		if(user.getRole().equals(RoleType.ADMIN)){
			throw new IllegalArgumentException("이미 관리자 입니다.");
		}
		if(user.getRole().equals(RoleType.REQUEST)){
			throw new IllegalArgumentException("이미 요청했습니다.");
		}
		user.setRole(RoleType.REQUEST);
	}

}
