package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.util.StringUtils;

@Service // 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌 IoC를 해준다.
public class UserApiService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;



	@Transactional
	public void signUp(User user){
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		userRepository.save(user);
	}

	// 전통적 방식의 로그인 구현
	/*@Transactional(readOnly = true) // select 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 유지)
	public User signIn(User user){
		return userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
	}*/

	@Transactional
	public void update(User requestUser){
		User user = userRepository.findById(requestUser.getId()).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저가 없습니다.");
		});

		// Validation 체크
		if(!StringUtils.hasText(user.getOauth())) {
			String rawPassword = requestUser.getPassword();
			String encPassword = encoder.encode(rawPassword);
			user.setPassword(encPassword);
			user.setEmail(requestUser.getEmail());
		}
	}

	@Transactional
	public User find(String userName){
		return userRepository.findByUserName(userName).orElseGet(()->{
			return new User();
		});
	}

	public void deleteUser(int id){
		userRepository.deleteById(id);
	}
}


// service가 필요한 이유
// 트랜젝션 관리를 위해서
// service의 의미
// 데이터베이스 처리 로직을 다양하게 처리하기 위해서
