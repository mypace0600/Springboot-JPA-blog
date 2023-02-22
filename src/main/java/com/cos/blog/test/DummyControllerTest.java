package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired //의존성 주입
	private UserRepository userRepository;

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id){
		try {
			userRepository.deleteById(id);
		} catch (Exception e){
			return "삭제에 실패했습니다. 해당 id는 db에 없습니다.";
		}
		return "삭제 되었습니다. id :"+id;
	}

	@Transactional // 함수 종료시 자동 commit 됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser){ //Json 데이터를 스프링이 java object로 받아줌 (메세지 컨버터의 jackson 라이브러리가 받아주는거임. @RequestBody 어노테이션을 붙임으로서
		System.out.println("id :"+id);
		System.out.println("password :"+requestUser.getPassword());
		System.out.println("email :"+requestUser.getEmail());

		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다.");
		});

		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		// userRepository.save(user);
		// 더티체킹 : 변경을 감지해서 수정 요청을 진행함
		return user;
	}

	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}

	// 한 페이지당 2건의 데이터 리턴받아볼 예정
	@GetMapping("/dummy/user") //?page=0 부터
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pageUser = userRepository.findAll(pageable);
		List<User> users = pageUser.getContent();
		return users;
	}

	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id){

		// findById()는 Optional로 객체를 감싸서 반환한다. 그래서 null인지 판단해서 리턴 필요

		// 방법1
		// .get()은 절대 null일리 없을 경우
		/*User user = userRepository.findById(id).get();*/


		// 방법2
		// .orElseGet()은 null인 경우 새로 객체를 만들어서 보여주는 것
		/*User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get(){
				return new User();
			}
		});*/

		// 방법3
		// .orElseThrow()는 IllegalArgumentException을 사용하여 오류임을 알려주는 것
		/*User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
			}
		});*/

		// 방법4
		// 위의 내용을 람다식으로 작성 가능
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 사용자는 없습니다.");
		});

		// User 객체는 자바 Object임, RestController를 사용하다보니 Data를 리턴하기에 Web브라우져는 오브젝트 이해르 못해서 변환이 필요
		// web브라우져가 이해할 수 있는 데이터인 json으로 변환되어야 함
		// Spring boot의 메세지 컨버터가 응답시 자동작동하여 jackson이라는 라이브러리를 호출하여 object를 json으로 변환해서 브라우져로 전달함
		return user;
	}

	@PostMapping("/dummy/join")
	public String join(User user){
		user.setRole(RoleType.USER);
		userRepository.save(user);

		System.out.println("userName :"+user.getUserName());
		System.out.println("password :"+user.getPassword());
		System.out.println("email :"+user.getEmail());
		System.out.println("id :"+user.getId());
		System.out.println("role :"+user.getRole());
		System.out.println("createDate:"+user.getCreateDate());
		return "회원가입 완료";
	}
}
