package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌 IoC를 해준다.
public class UserApiService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void signUp(User user){
		userRepository.save(user);
	}

	// 전통적 방식의 로그인 구현
	/*@Transactional(readOnly = true) // select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 유지)
	public User signIn(User user){
		return userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
	}*/
}


// service가 필요한 이유
// 트랜젝션 관리를 위해서
// service의 의미
// 데이터베이스 처리 로직을 다양하게 처리하기 위해서
