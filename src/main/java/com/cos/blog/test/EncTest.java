package com.cos.blog.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

	@Test
	public void hashEncode(){
		String enc = new BCryptPasswordEncoder().encode("1234");
	}
}
