package com.ssafy.article.model.dto;

import java.util.List;

import com.ssafy.comment.model.dto.CommentDto;
import com.ssafy.plan.model.dto.PlanDto;

public class ArticleDto {
	private int articleId;
	private String articleTitle;
	private String articleContent;
	private String createdTime;
	private String articleWriter;
	private int hit;
	private int planId;
	private int articleHeart;
	private List<CommentDto> comments;
	private PlanDto plan;
	
	public ArticleDto() {}

	public ArticleDto(int articleId, String articleTitle, String articleContent, String createdTime,
			String articleWriter, int hit, int planId, int articleHeart, List<CommentDto> comments, PlanDto plan) {
		super();
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.createdTime = createdTime;
		this.articleWriter = articleWriter;
		this.hit = hit;
		this.planId = planId;
		this.articleHeart = articleHeart;
		this.comments = comments;
		this.plan = plan;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getArticleWriter() {
		return articleWriter;
	}

	public void setArticleWriter(String articleWriter) {
		this.articleWriter = articleWriter;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getArticleHeart() {
		return articleHeart;
	}

	public void setArticleHeart(int articleHeart) {
		this.articleHeart = articleHeart;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public PlanDto getPlan() {
		return plan;
	}

	public void setPlan(PlanDto plan) {
		this.plan = plan;
	}
}
