package com.ssafy.member.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.member.model.dto.MemberDto;

@Mapper
public interface MemberMapper {
	//회원 정보 가입
	public int create(MemberDto mDto);
	//회원 정보 조회
	public MemberDto read(String memberId);
	//회원 정보 수정
	public int update(MemberDto mDto);
	//회원 정보 삭제(탈퇴)
	public int delete(String memberId);
	//로그인
	public MemberDto login(@Param("memberId")String memberId, @Param("memberPw")String memberPw);
	
}
