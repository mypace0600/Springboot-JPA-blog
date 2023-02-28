package com.cos.blog.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.contract.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardApiService {
	/*@Autowired
	private BoardRepository boardRepository;*/
	private final BoardRepository boardRepository;
	/*@Autowired
	private ReplyRepository replyRepository;*/
	private final ReplyRepository replyRepository;

	@Transactional
	public void save(Board board, User user){
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	@Transactional(readOnly = true)
	public Page<Board> getList(Pageable pageable){
		return boardRepository.findAll(pageable);
	}


	@Transactional(readOnly = true)
	public Board boardDetail(int id){
		return boardRepository.findById(id).orElseThrow(()->{return new IllegalArgumentException("글 상세보기 실패 : 게시글 정보를 찾을 수 없습니다.");});
	}

	@Transactional
	public void deleteById(int id,User user){
		int userId = user.getId();
		int writerId = boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 삭제 실패 : 작성자가 아닙니다.");
		}).getUser().getId();


		if(userId == writerId) {
			boardRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("글 삭제 실패 : 글 삭제 권한이 없습니다.");
		}
	}

	@Transactional
	public void updateById(int id, User user, Board requestBoard){
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 찾기 실패 : 글을 찾을 수 없습니다.");
		}); // 영속화
		if(board.getUser().getId()!=user.getId()){
			throw new IllegalArgumentException("글 수정 실패 : 글 수정 권한이 없습니다.");
		} else {
			board.setTitle(requestBoard.getTitle());
			board.setContent(requestBoard.getContent());
			// 서비스가 종료될 때 트랜잭션이 종료되어 더티체킹이 일어나 자동 업데이트가 됨(db flush)
		}
	}

	@Transactional
	public void replySave(ReplySaveRequestDto dto){

		// 영속화 방법
		/*User user = userRepository.findById(dto.getUserId()).orElseThrow(()->{
			return new IllegalArgumentException("회원을 찾을 수 없습니다.");
		});

		Board board = boardRepository.findById(dto.getBoardId()).orElseThrow(()->{
			return new IllegalArgumentException("게시 글을 찾을 수 없습니다.");
		});*/

		// 따로 model에 함수를 만들어서 매칭을 시키는 것
		// Reply reply = new Reply();
		// reply.autoMatching(user, board,dto.getContent());

		/*Reply reply = Reply.builder()
				.user(user)
				.board(board)
				.content(dto.getContent())
				.build();

		replyRepository.save(reply);*/


		// 영속화 없이 nativeQuery를 통해 사용하는 방법
		replyRepository.replyAutoSave(dto.getContent(),dto.getUserId(),dto.getBoardId());
	}

	public void replyDelete(int replyId, User principalUser) throws Exception {
		User user = replyRepository.findById(replyId).orElseThrow(()->{
			return new IllegalArgumentException("댓글을 찾을 수 업습니다.");
		}).getUser();
		if(user.getId() == principalUser.getId()) {
			replyRepository.deleteById(replyId);
		} else {
			throw new Exception("댓글 삭제 권한이 없습니다.");
		}
	}

	public void replyUpdate(Reply requestReply, User principalUser) throws Exception {
		log.info("@@@@@ requestReply :{}",requestReply);
		Reply reply = replyRepository.findById(requestReply.getId()).orElseThrow(()->{
			return new IllegalArgumentException("댓글을 찾을 수 없습니다.");
		});
		log.info("@@@@@ reply :{}",reply);

		User user = reply.getUser();
		log.info("@@@@@ user :{}",user);
		if(user.getId() != principalUser.getId()) {
			throw new Exception("댓글 수정 권한이 없습니다.");
		} else {
			log.info("111");
			reply.setContent(reply.getContent());
		}
	}

}
