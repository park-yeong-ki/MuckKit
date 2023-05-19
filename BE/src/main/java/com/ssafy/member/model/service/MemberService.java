package com.ssafy.member.model.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.article.model.mapper.ArticleMapper;
import com.ssafy.comment.model.mapper.CommentMapper;
import com.ssafy.email.EmailService;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.plan.model.mapper.PlanMapper;

@Service
public class MemberService {
	private final MemberMapper mapper;
	private final EmailService emailService;
	private final ArticleMapper articleMapper;
	private final PlanMapper planMapper;
	private final CommentMapper commentMapper;

	public MemberService(MemberMapper mapper, EmailService emailService, ArticleMapper articleMapper,
			PlanMapper planMapper, CommentMapper commentMapper) {
		super();
		this.mapper = mapper;
		this.emailService = emailService;
		this.articleMapper = articleMapper;
		this.planMapper = planMapper;
		this.commentMapper = commentMapper;
	}

	//회원 정보 가입
	public int join(MemberDto mDto) {
		return mapper.create(mDto);
	}
	
	//회원 정보 조회
	@Transactional
	public MemberDto selectOne(String memberId) {
		MemberDto findMember = mapper.read(memberId);
		findMember.setArticleList(articleMapper.readMyArticle(memberId));
		findMember.setPlanList(planMapper.readMyPlan(memberId));
		findMember.setHeartArticleList(articleMapper.readMyHeart(memberId));
		findMember.setHeartPlanList(planMapper.readMyHeart(memberId));
		findMember.setCommentsList(commentMapper.readMyComments(memberId));
		return findMember;
	}
	
	//회원 정보 수정
	public int modify(MemberDto mDto) {
		return mapper.update(mDto);
	}
	
	//회원 정보 삭제(탈퇴)
	public int delete(String memberId) {
		return mapper.delete(memberId);
	}
	
	//로그인
	public MemberDto login(String memberId, String memberPw) {
		return mapper.login(memberId, memberPw);
	}
		
	//비밀번호 찾기
	@Transactional
	public int findPassword(MemberDto mDto) throws UnsupportedEncodingException, MessagingException {
		String pw = emailService.sendPasswordMail(mDto.getMemberId());
		mDto.setMemberPw(pw);
		return mapper.update(mDto);
	}
	
	//인증 이메일 보내기
	public void authMail(String memberId) throws UnsupportedEncodingException, MessagingException {
		emailService.sendAuthMail(memberId);
	}
	
	//인증 코드 검증
	public boolean verifyAuthCode(String memberId, String authCode) {
		return emailService.verifyAuthCode(memberId, authCode);
	}
}
