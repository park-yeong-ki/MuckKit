package com.ssafy.member.model.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.email.EmailService;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

@Service
public class MemberService {
	private final MemberMapper mapper;
	private final EmailService emailService;

	public MemberService(MemberMapper mapper, EmailService emailService) {
		this.mapper = mapper;
		this.emailService = emailService;
	}

	//회원 정보 가입
	public int join(MemberDto mDto) {
		return mapper.create(mDto);
	}
	
	//회원 정보 조회
	public MemberDto selectOne(String memberId) {
		return mapper.read(memberId);
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
