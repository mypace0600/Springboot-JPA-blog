package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String title; // 제목

	@Lob // 대용량 데이터 사용시
	private String content; // 내용, 섬머노트 라이브러리 사용 예정 html 태그 섞여서 디자인 됨

	private int count; // 조회수

	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One 한명의 유저가 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name="userId")
	private User user;
	// DB는 오브젝트를 저장할 수 없다. 그래서 FK를 사용해야 하지만 자바는 오브젝트를 저장할 수 있다.

	// @JoinColumn(name="replyId") 필요 없음, 원자성이 깨지기 때문
	// cascade = CascadeType.REMOVE : 게시물 지울 때 댓글도 다 지우는 것
	@JsonIgnoreProperties({"board"})
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //mappedBy 연관관계의 주인이 아니다. FK가 아니다. Reply 테이블의 board가 FK키다. DB에 컬럼을 만들지 마.
	@OrderBy("id desc ")
	private List<Reply> replys;
	// OneToMany의 Default FetchType은 LAZY이지만, 로직상 EAGER 전략으로 바꿔야 함

	@CreationTimestamp
	private Timestamp createDate; // 등록일

	private Boolean hidden;
}
