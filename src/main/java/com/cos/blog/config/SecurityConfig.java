package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;

// 아래 세개는 세트다
@Configuration // 빈등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@EnableWebSecurity // 시큐리티 필터 추가 = 스프링 시큐리티가 활성화 되어 있는데 어떤 설정을 해당 파일에서 하겠다는 뜻
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권하 및 인증을 미리 체크하는 것
public class SecurityConfig  {

	@Autowired
	private PrincipalDetailService principalDetailService;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	// Hash로 인코딩 : 고정길이의 문자열로 바꿔줌
	@Bean // IoC가 됨 (리턴하는 값을 스프링이 관리함. 필요할 때 마다 가져와서 쓰면 됨)
	public BCryptPasswordEncoder encodePWD(){
		return new BCryptPasswordEncoder();
	}

	// 시큐리티가 대신 로그인을 해주는데 Password를 가로채기를 하는데 해당 password가 뭘로 hash가 되서 회원가입이 되었는지 알아야
	// 같은 hash로 암호화 해서 db에 있는 값과 비교를 할 수 있다.
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable() // csrf 토큰 비활성화 (테스트시에만 걸어두는게 좋음)
				.authorizeHttpRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/img/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인을 한다.
				.defaultSuccessUrl("/") // 로그인 성공시 "/" 주소로 들어가게 됨
		;
		return http.build();
	}

	// xss
	// cross site scripting :  자바스크립트를 공격하는 것
	// 글쓰기 할 때 글을 <script> for(var i = 0; i<50000; i++){alert("hello");}</script> 이런 식으로 작성하면
	// 게시판에 alert 메세지가 5만번 떠서 마비가 됨
	// lucy xx filter 를 통해서 간단히 막을 수 있음

	// csrf
	// cross-site request forgery : 사이트간 요청 위조
	// http://naver.com/user/admin/point?id=ssar&point=50000
	// 1. 요청을 post 방식으로 해야 함 (하이퍼링크 공격 방지)
}
