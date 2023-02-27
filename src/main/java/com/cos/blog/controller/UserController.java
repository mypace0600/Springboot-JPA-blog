package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// 인증 안된 사용자들은  auth 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 내용 허용

@Controller
public class UserController {
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

	@Value("${API-KEY.clientKey}")
	String clientKey;

	@Value("${API-KEY.callBackUri}")
	String callBackUri;

	@Value("${API-KEY.tokenRequestUri}")
	String tokenRequestUri;
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCallbak(String code){

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
		System.out.println(oAuthToken.getAccess_token());
		return "카카오 인증 완료 토큰 요청에 대한 응답 : "+response.getBody();
	}

}
