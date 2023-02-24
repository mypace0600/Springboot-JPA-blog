package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


// 아래 세개는 세트다
@Configuration // 빈등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@EnableWebSecurity // 시큐리티 필터 추가 = 스프링 시큐리티가 활성화 되어 있는데 어떤 설정을 해당 파일에서 하겠다는 뜻
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권하 및 인증을 미리 체크하는 것
public class SecurityConfig  {

	// Hash로 인코딩 : 고정길이의 문자열로 바꿔줌

	@Bean // IoC가 됨 (리턴하는 값을 스프링이 관리함. 필요할 때 마다 가져와서 쓰면 됨)
	public BCryptPasswordEncoder encodePWD(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
				.authorizeHttpRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/img/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/auth/loginForm");

		return http.build();
	}
}
