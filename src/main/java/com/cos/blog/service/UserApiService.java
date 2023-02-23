package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // 스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌 IoC를 해준다.
public class UserApiService {
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public int signIn(User user){
		user.setRole(RoleType.USER);
		try {
			userRepository.save(user);
			return 1;
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("UserApiService : signIn() :"+e.getMessage());
		}
		return -1;
	}
}


// service가 필요한 이유
// 트랜젝션 관리를 위해서
// service의 의미
// 데이터베이스 처리 로직을 다양하게 처리하기 위해서
