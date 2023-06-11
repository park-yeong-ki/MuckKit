package com.ssafy.comment.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.comment.model.dto.CommentDto;

@Mapper
public interface CommentMapper {
	//댓글 작성
	public int createComment(CommentDto cDto);
	
	//댓글 조회
	public CommentDto read(int commentId);
	
	//게시글별 댓글 조회
	public List<CommentDto> readComments(int articleId);
	
	//댓글 수정
	public int updateComment(CommentDto cDto);
	
	//댓글 삭제
	public int deleteComment(int commentId);
	
	//작성 댓글 조회
	public List<CommentDto> readMyComments(String memberId);
}
