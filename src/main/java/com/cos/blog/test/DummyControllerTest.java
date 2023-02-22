package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입
	private UserRepository userRepository;

	@PostMapping("/dummy/join")
	public String join(User user){
		user.setRole(RoleType.USER);
		userRepository.save(user);

		System.out.println("userName :"+user.getUserName());
		System.out.println("password :"+user.getPassword());
		System.out.println("email :"+user.getEmail());
		System.out.println("id :"+user.getId());
		System.out.println("role :"+user.getRole());
		System.out.println("createDate:"+user.getCreateDate());
		return "회원가입 완료";
	}
}
