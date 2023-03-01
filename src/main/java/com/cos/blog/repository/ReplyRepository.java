package com.cos.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;
import org.springframework.transaction.annotation.Transactional;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {

	@Modifying
	@Query(value="INSERT INTO reply (content,userId, boardId,  createDate) VALUES (:content,:userId,:boardId,now())",nativeQuery = true)
	int replyAutoSave(String content,int userId, int boardId);

	@Modifying
	@Transactional
	@Query(value="UPDATE reply SET content = :content WHERE id = :replyId",nativeQuery = true)
	int replyUpdate(String content, int replyId)throws Exception;
}
