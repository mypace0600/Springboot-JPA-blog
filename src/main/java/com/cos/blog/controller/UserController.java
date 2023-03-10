package com.cos.blog.controller;

import com.cos.blog.model.KakaoUserProfile;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

// 인증 안된 사용자들은  auth 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 내용 허용

@Controller
public class UserController {

	@Autowired
	private UserApiService service;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Value("${cos.key}")
	String cosKey;
	@Value("${API-KEY.clientKey}")
	String clientKey;
	@Value("${API-KEY.callBackUri}")
	String callBackUri;
	@Value("${API-KEY.tokenRequestUri}")
	String tokenRequestUri;

	@Value("${API-KEY.profileRequestUri}")
	String profileRequestUri;

	@GetMapping("/auth/joinForm")
	public String joinForm(){
		return "user/joinForm";
	}
	@GetMapping("/auth/loginForm")
	public String loginForm(){
		return "user/loginForm";
	}

	@GetMapping("/user/updateForm")
	public String updateForm(){ return "user/updateForm";}

	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code){

		// POST 방식으로 Key-Value 타입으 데이터를 요청해야 함(카카오쪽으로)
		// a 태그는 무조건 get 방식이라서 안된다.
		// 과거에는 HttpsURLConnection, Retrofit2, OkHttp 를 써야 했음
		// 이때 RestTemplate라는 라이브러리를 써야 함
		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type","application/x-www-form-urlencoded;charset=utf-8"); //전송할 body 데이터가 key-value 형태임을 알려주는 것

		// HttpBody 오브젝트 생성
		MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
		body.add("grant_type","authorization_code");
		body.add("client_id",clientKey);
		body.add("redirect_uri",callBackUri);
		body.add("code",code);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기 (exchange 라는 함수가 HtpEntity를 받기 떄문에)
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(body,headers);

		// Http 요청하기 - POST 방식으로 - 그리고  response 변수와 응답 받음
		ResponseEntity<String> response = rt.exchange(
				tokenRequestUri,
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);

		// json 데이터를 받기 위해서 Gson, Json simple, ObjectMapper 등의 방법을 쓰는데 우리는 아래 방법으로 한다.
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;
		try {
			oAuthToken = objectMapper.readValue(response.getBody(),OAuthToken.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		headers2.add("Authorization","Bearer "+oAuthToken.getAccess_token());
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest = new HttpEntity<>(headers2);
		ResponseEntity<String> response2 = rt2.exchange(
				profileRequestUri,
				HttpMethod.POST,
				kakaoProfileRequest,
				String.class
		);
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoUserProfile kakaoUserProfile = null;
		try {
			kakaoUserProfile = objectMapper2.readValue(response2.getBody(),KakaoUserProfile.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// User 오브젝트 구성을 위해 필요한 것 : username, password, email
		String email = kakaoUserProfile.getKakao_account().getEmail();
		String userName = "kakao_"+kakaoUserProfile.getId();
		// UUID tempPassword = UUID.randomUUID(); 해당 알고리즘으로 비밀번호 구성시 매번 달라지기 때문에 고정키 필요

		User kakaoUser = User.builder()
				.userName(userName)
				.password(cosKey)
				.email(email)
				.role(RoleType.USER)
				.oauth("kakao")
				.build();

		// 기존 가입자인지 비가입자인지 체크 필요
		User originUser = service.find(kakaoUser.getUserName());
		if (originUser.getUserName() == null) {
			service.signUp(kakaoUser);
		}

		// 자동 로그인 처리
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						kakaoUser.getUserName(),
						cosKey
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "redirect:/";
	}

}
