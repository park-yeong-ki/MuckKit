package com.ssafy.article.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.article.model.dto.ArticleDto;
import com.ssafy.article.model.service.ArticleService;
import com.ssafy.attraction.model.service.AttractionService;
import com.ssafy.comment.model.service.CommentService;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.plan.model.service.PlanService;
import com.ssafy.security.auth.MemberDetails;

@RestController
@RequestMapping("article")
public class ArticleController {
	private ArticleService service;

	public ArticleController(ArticleService service) {
		super();
		this.service = service;
	}

	// 게시글 상세조회
	@GetMapping("/{article_id}")
	public ResponseEntity<?> read(@PathVariable("article_id") int articleId) {
		ArticleDto articleDto = service.selectOne(articleId);
		if (articleDto != null) {
			service.updateHit(articleId);
			return new ResponseEntity<>(articleDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 전체조회(공지(=2), 일반 게시글(=1) 카테고리 번호로 구분)
	@GetMapping
	public ResponseEntity<?> readList(@RequestParam("sort") String sort) {
		
		return new ResponseEntity<>(service.selectAll(sort), HttpStatus.OK);
	}

	// 게시글 작성
	@PostMapping("/writeArticle")
	public ResponseEntity<?> writeArticle(@RequestBody ArticleDto articleDto, @AuthenticationPrincipal MemberDetails memberDetails) {
		MemberDto loginMember = memberDetails.getMember();
		if (articleDto.getArticleWriter().equals(loginMember.getMemberId())) {
			if (service.writeArticle(articleDto) > 0) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
	}

	// 게시글 수정
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody ArticleDto articleDto, @AuthenticationPrincipal MemberDetails memberDetails) {
		MemberDto loginMember = memberDetails.getMember();
		if (articleDto.getArticleWriter().equals(loginMember.getMemberId())) {
			if (service.modify(articleDto) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 게시글 삭제
	@DeleteMapping("/{article_id}")
	public ResponseEntity<?> remove(@PathVariable("article_id") int articleId, @AuthenticationPrincipal MemberDetails memberDetails) {
		MemberDto loginMember = memberDetails.getMember();
		if (loginMember.getMemberRole().equals("관리자") || service.selectOne(articleId).getArticleWriter().equals(loginMember.getMemberId())) {
			if (service.remove(articleId) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//게시글 좋아요
	@GetMapping("/heart/{article_id}")
	public ResponseEntity<?> heart(@PathVariable("article_id") int articleId, @AuthenticationPrincipal MemberDetails memberDetails){
		MemberDto loginMember = memberDetails.getMember();
		if (service.pressHeart(loginMember.getMemberId(), articleId) > 0) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
