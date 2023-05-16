package com.ssafy.member.model.dto;

import java.util.List;

public class MemberDto {
	private String memberId;
	private String memberName;
	private String memberPw;
	private String memberPhone;
	private String memberRole;
	private List<String> heartList;

	public MemberDto() {}
	
	public MemberDto(String memberId, String memberName, String memberPw, String memberPhone, String memberRole,
			List<String> heartList) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPw = memberPw;
		this.memberPhone = memberPhone;
		this.memberRole = memberRole;
		this.heartList = heartList;
	}
	public List<String> getHeartList() {
		return heartList;
	}
	public void setHeartList(List<String> heartList) {
		this.heartList = heartList;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberRole() {
		return memberRole;
	}
	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}
	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", memberName=" + memberName + ", memberPw=" + memberPw
				+ ", memberPhone=" + memberPhone + ", memberRole=" + memberRole + "]";
	}
	
}
