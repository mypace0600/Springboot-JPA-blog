package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.blog.contract.ReplySaveRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 아이디

	@Column(nullable = false, length = 200)
	private String content; // 내용

	@ManyToOne
	@JoinColumn(name="boardId")
	private Board board;

	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	@CreationTimestamp
	private Timestamp createDate;

	/*public void autoMatching(User user, Board board, String content){
		setUser(user);
		setBoard(board);
		setContent(content);
	}*/
}
