package com.cos.blog.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplySaveRequestDto {
	private int userId;
	private int boardId;
	private String content;
}

// DTO 의 장점
// 필요한 데이터를 한번에 받을 수 있다
