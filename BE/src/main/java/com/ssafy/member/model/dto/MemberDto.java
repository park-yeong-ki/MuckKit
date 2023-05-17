package com.ssafy.member.model.dto;

import java.util.List;

import com.ssafy.article.model.dto.ArticleDto;
import com.ssafy.plan.model.dto.PlanDto;

public class MemberDto {
	private String memberId;
	private String memberName;
	private String memberPw;
	private String memberPhone;
	private String memberRole;
	private List<ArticleDto> heartArticleList;
	private List<PlanDto> planList;
	private List<ArticleDto> articleList;
	private List<PlanDto> heartPlanList;

	public MemberDto() {}

	public MemberDto(String memberId, String memberName, String memberPw, String memberPhone, String memberRole,
			List<ArticleDto> heartArticleList, List<PlanDto> planList, List<ArticleDto> articleList,
			List<PlanDto> heartPlanList) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPw = memberPw;
		this.memberPhone = memberPhone;
		this.memberRole = memberRole;
		this.heartArticleList = heartArticleList;
		this.planList = planList;
		this.articleList = articleList;
		this.heartPlanList = heartPlanList;
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

	public List<ArticleDto> getHeartArticleList() {
		return heartArticleList;
	}

	public void setHeartArticleList(List<ArticleDto> heartArticleList) {
		this.heartArticleList = heartArticleList;
	}

	public List<PlanDto> getPlanList() {
		return planList;
	}

	public void setPlanList(List<PlanDto> planList) {
		this.planList = planList;
	}

	public List<ArticleDto> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleDto> articleList) {
		this.articleList = articleList;
	}

	public List<PlanDto> getHeartPlanList() {
		return heartPlanList;
	}

	public void setHeartPlanList(List<PlanDto> heartPlanList) {
		this.heartPlanList = heartPlanList;
	}
}
