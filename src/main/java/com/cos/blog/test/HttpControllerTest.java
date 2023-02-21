package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

	private static final String TAG = "HttpControllerTest :";

	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder()
				.username("ssar")
				.password("1234")
				.email("email")
				.build();
		System.out.println(TAG + "getter :" + m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG + "setter :" + m.getUsername());
		return "lombok test 완료";
	}

	@GetMapping("/test/http")
	public String getTest(Member m) {
		return "get 요청 :"+ m.getId();
	}

	@PostMapping("/test/http")
	public String postTest(@RequestBody Member m) {
		return "post 요청 :"+ m.getId() + m.getUsername() + m.getEmail();
	}

	@PutMapping("/test/http")
	public String putTest(@RequestBody Member m) {
		return "put 요청 :"+ m.getId() + m.getUsername() + m.getEmail();
	}

	@DeleteMapping("/test/http")
	public String deleteTest(@RequestBody Member m) {
		return "delete 요청"+ m.getId() + m.getUsername() + m.getEmail();
	}

}
