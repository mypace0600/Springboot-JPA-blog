package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	// 전통적 방식의 로그인 구현 1
	// JPA Naming Query
	// SELECT * FROM user WHERE username = ? AND password = ?;
	/*User findByUserNameAndPassword(String userName, String password);*/

	// 전통적 방식의 로그인 구현 2
	/*@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
	User login(String username, String password);*/
}
