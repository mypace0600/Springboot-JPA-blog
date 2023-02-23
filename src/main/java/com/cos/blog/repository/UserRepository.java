package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	// JPA Naming 전략
	// SELECT * FROM user WHERE username = ? AND password = ?;
	User findByUserNameAndPassword(String userName, String password);

	/*@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
	User login(String username, String password);*/
}
