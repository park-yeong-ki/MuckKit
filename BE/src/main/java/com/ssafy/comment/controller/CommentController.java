package com.ssafy.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.comment.model.dto.CommentDto;
import com.ssafy.comment.model.service.CommentService;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.security.auth.MemberDetails;

@RestController
@RequestMapping("/comment")
public class CommentController {
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	//댓글 작성
	@PostMapping("/write")
	public ResponseEntity<?> writeComment(@RequestBody CommentDto cDto, @AuthenticationPrincipal MemberDetails memberDetails){
		MemberDto loginMember = memberDetails.getMember();
		if (loginMember.getMemberId().equals(cDto.getCommentWriter())) {
			if (commentService.write(cDto) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//댓글 조회
	@GetMapping("/{comment-id}")
	public ResponseEntity<?> readComment(@PathVariable("comment-id") int commentId, @AuthenticationPrincipal MemberDetails memberDetails){
		MemberDto loginMember = memberDetails.getMember();
		CommentDto findComment = commentService.selectOne(commentId);
		if (loginMember.getMemberId().equals(findComment.getCommentWriter())) {
			return new ResponseEntity<>(findComment, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//댓글 수정
	@PutMapping("/modify")
	public ResponseEntity<?> modifyComment(@RequestBody CommentDto cDto, @AuthenticationPrincipal MemberDetails memberDetails){
		MemberDto loginMember = memberDetails.getMember();
		if (loginMember.getMemberId().equals(cDto.getCommentWriter())) {
			if (commentService.modify(cDto) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//댓글 삭제
	@DeleteMapping("/{comment-id}")
	public ResponseEntity<?> removeComment(@PathVariable("comment-id") int commentId, @AuthenticationPrincipal MemberDetails memberDetails){
		MemberDto loginMember = memberDetails.getMember();
		if (loginMember.getMemberId().equals(commentService.selectOne(commentId).getCommentWriter())) {
			if (commentService.remove(commentId) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
