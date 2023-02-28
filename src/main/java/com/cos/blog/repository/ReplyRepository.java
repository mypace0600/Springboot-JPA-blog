package com.cos.blog.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {

	@Modifying
	@Query(value="INSERT INTO reply (content,userId, boardId,  createDate) VALUES (:content,:userId,:boardId,now())",nativeQuery = true)
	int replyAutoSave(String content,int userId, int boardId);
}
