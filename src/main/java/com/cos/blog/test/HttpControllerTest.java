package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	
	@GetMapping("/test/http")
	public String getTest() {
		return "get 요청";
	}
	
	@PostMapping("/test/http")
	public String postTest() {
		return "post 요청";
	}
	
	@PutMapping("/test/http")
	public String putTest() {
		return "put 요청";
	}
	
	@DeleteMapping("/test/http")
	public String deleteTest() {
		return "delete 요청";
	}

}
