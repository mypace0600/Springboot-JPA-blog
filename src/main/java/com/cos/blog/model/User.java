package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java(다른언어) Object를 테이블로 매핑해주는 기술
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto-increment

	@Column(nullable = false, length = 30)
	private String userName; // 아이디

	@Column(nullable = false, length = 100) // hash 암호화 예정
	private String password; // 비밀번호

	@Column(nullable = false, length = 50)
	private String email; // 이메일

	@ColumnDefault("'user'") //"" 사이에''을 넣어서 주어야 함
	private String role; // 권한, Enum을 쓰는게 좋다 (admin,user,manager으로 도메인 범위 설정 가능으로 이외의 데이터 입력을 제한함)

	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate; // 등록일
}