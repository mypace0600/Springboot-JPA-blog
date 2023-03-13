package com.cos.blog.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

	public void hashEncode(){
		String enc = new BCryptPasswordEncoder().encode("1234");
	}
}
