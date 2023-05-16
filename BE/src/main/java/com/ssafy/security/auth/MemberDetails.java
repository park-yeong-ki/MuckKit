package com.ssafy.security.auth;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ssafy.member.model.dto.MemberDto;

public class MemberDetails extends User {
	private final MemberDto member;

	public MemberDetails(MemberDto mDto) {
		super(mDto.getMemberId(), mDto.getMemberPw(), Arrays.asList(new SimpleGrantedAuthority("USER")));
		this.member = mDto;
	}

	public MemberDto getMember() {
		return member;
	}
}
