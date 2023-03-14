package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import com.cos.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.contract.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserApiService;

@RestController
public class UserApiController {

	@Autowired
	private UserApiService service;
	@Autowired
	private AuthenticationManager authenticationManager;
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user){
		user.setRole(RoleType.USER);
		service.signUp(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	// 전통적 방식의 로그인 구현
	/*@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
		User principal = service.signIn(user); // 접근 주체
		if(principal != null){
			session.setAttribute("principal",principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}*/


	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		service.update(user);
		// 위 서비스가 끝나고 여기로 오면 트랜잭션 종료되어 db의 값은 변경되었지만 세션값은 변경되지 않은 상태
		// 세션값 변경을 직접 해줘야 함

		// 세션 등록
		// 토큰 생성시 필요한 값이 다 있는지 확인 필요
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						user.getUserName(),
						user.getPassword()
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

	@DeleteMapping("/user/{id}")
	public ResponseDto<Integer> deleteUser(@PathVariable int id) throws Exception{
		try {
			service.deleteUser(id);
		} catch (Exception e){;
			throw new IllegalArgumentException("회원탈퇴 실패");
		}
		SecurityContextHolder.clearContext();
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}

}
