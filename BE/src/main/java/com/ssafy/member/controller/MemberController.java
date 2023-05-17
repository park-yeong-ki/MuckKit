package com.ssafy.member.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.article.model.service.ArticleService;
import com.ssafy.comment.model.service.CommentService;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.plan.model.service.PlanService;
import com.ssafy.security.auth.JwtProvider;
import com.ssafy.security.auth.MemberDetails;
import com.ssafy.security.auth.TokenResponse;

@RestController
@RequestMapping("/member")
public class MemberController {
	private final MemberService memberService;
	private final JwtProvider jwtProvider;
	private final ArticleService articleService;
	private final PlanService planService;
	private final CommentService commentService;

	public MemberController(MemberService memberService, JwtProvider jwtProvider, ArticleService articleService,
			PlanService planService, CommentService commentService) {
		super();
		this.memberService = memberService;
		this.jwtProvider = jwtProvider;
		this.articleService = articleService;
		this.planService = planService;
		this.commentService = commentService;
	}

	// 회원 정보 가입
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody MemberDto mDto) {
		if (memberService.join(mDto) > 0) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 회원 정보 조회
	@GetMapping("/{member-id}")
	public ResponseEntity<?> selectOne(@PathVariable("member-id") String memberId, @AuthenticationPrincipal MemberDetails memberDetails) {
		if (memberDetails.getMember().getMemberId().equals(memberId)) {
			MemberDto memberDto = memberService.selectOne(memberId);
			if (memberDto != null) {
				memberDto.setArticleList(articleService.selectMyArticle(memberId));
				memberDto.setHeartArticleList(articleService.selectMyHeart(memberId));
				memberDto.setPlanList(planService.selectMyPlan(memberId));
				memberDto.setHeartPlanList(planService.selectMyHeart(memberId));
				memberDto.setCommentsList(commentService.selectMyComments(memberId));
				return new ResponseEntity<>(memberDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 회원 정보 수정
	@PutMapping("/modify/{member-id}")
	public ResponseEntity<?> modify(@PathVariable("member-id") String memberId, @RequestBody MemberDto mDto, @AuthenticationPrincipal MemberDetails memberDetails) {
		if (memberDetails.getMember().getMemberId().equals(memberId)) {
			mDto.setMemberId(memberId);
			if (memberService.modify(mDto) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}

	// 회원 정보 삭제(탈퇴)
	@DeleteMapping("/{member-id}")
	public ResponseEntity<?> delete(@PathVariable("member-id") String memberId, @AuthenticationPrincipal MemberDetails memberDetails) {
		if (memberDetails.getMember().getMemberId().equals(memberId)) {
			if (memberService.delete(memberId) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 비밀번호 찾기
	@PostMapping("/find-password")
	public ResponseEntity<?> sendPasswordEmail(@RequestBody MemberDto mDto)	throws MessagingException, UnsupportedEncodingException {
		MemberDto findMember = memberService.selectOne(mDto.getMemberId());
		// 입력받은 이메일이 존재하는 경우
		if (findMember != null) {
			if (memberService.findPassword(findMember) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/join/send-mail")
    public ResponseEntity<?> sendEmail(@RequestBody MemberDto mDto) throws MessagingException, UnsupportedEncodingException {
		MemberDto findMember = memberService.selectOne(mDto.getMemberId());
		//가입되지 않은 회원인 경우
		if (findMember == null) {
			memberService.authMail(mDto.getMemberId());
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}             
    }
	
	@PostMapping("/join/auth-code")
    public ResponseEntity<?> authEmail(@RequestBody Map<String, String> param) {
		if (memberService.verifyAuthCode(param.get("memberId"), param.get("authCode"))) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody MemberDto mDto) throws JsonProcessingException {
		MemberDto findMember = memberService.login(mDto.getMemberId(), mDto.getMemberPw());
		if (findMember != null) {
			TokenResponse jwt = jwtProvider.createTokensByLogin(findMember);
			HttpHeaders headers = new HttpHeaders();
			headers.add("access-token", jwt.getAtk());
			headers.add("refresh-token", jwt.getRtk());
			return new ResponseEntity<>("로그인 성공",headers, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/reissue")
	public ResponseEntity<?> reissue(@AuthenticationPrincipal MemberDetails memberDetails) throws Exception{
		MemberDto member = memberService.selectOne(memberDetails.getMember().getMemberId()) ;
		HttpHeaders headers = new HttpHeaders();
		headers.add("access-token", jwtProvider.reissueAtk(member).getAtk());
		return new ResponseEntity<>("access-token 재발급", headers, HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(@AuthenticationPrincipal MemberDetails memberDetails, 
			@RequestHeader("Authorization") String bearerAtk){
		MemberDto member = memberService.selectOne(memberDetails.getMember().getMemberId());
		jwtProvider.setBlackListAtk(bearerAtk);
		jwtProvider.deleteRtk(member);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
