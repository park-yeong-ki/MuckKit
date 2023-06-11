package com.ssafy.comment.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.comment.model.dto.CommentDto;
import com.ssafy.comment.model.mapper.CommentMapper;

@Service
public class CommentService {
	private CommentMapper commentMapper;

	public CommentService(CommentMapper commentMapper) {
		super();
		this.commentMapper = commentMapper;
	}
	
	//댓글작성
	public int write(CommentDto cDto) {
		return commentMapper.createComment(cDto);
	}
	
	//댓글수정
	public int modify(CommentDto cDto) {
		return commentMapper.updateComment(cDto);
	}
	
	public CommentDto selectOne(int commentId) {
		return commentMapper.read(commentId);
	}
	
	//게시글별 댓글조회
	public List<CommentDto> selectComments(int articleId){
		return commentMapper.readComments(articleId);
	}
	
	//댓글삭제
	public int remove(int commentId) {
		return commentMapper.deleteComment(commentId);
	}
	
	//작성 댓글 목록
	public List<CommentDto> selectMyComments(String memberId){
		return commentMapper.readMyComments(memberId);
	}
}
