package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.Board;
import com.cos.blog.repository.BoardRepository;
// 무한참조 확인용
@RestController
public class ReplyControllerTest {

	@Autowired
	private BoardRepository boardRepository;

	@GetMapping("/test/board/{id}")
	public Board getBoard(@PathVariable int id){
		return boardRepository.findById(id).get();
		// jackson 라이브러리 실행됨 (오브젝트를 json 으로 리턴해줌) => 모델이 들고 있는 getter를 호출함
		// reply 안에 board가 있기 때문에 또 안에 들어가서 또 getter를 호출한다. 그러면서 무한참조가 된다.
		// 그것을 방지하기 위해서는 @JsonIgnoreProperties 어노테이션 사용 필요
	}
}
