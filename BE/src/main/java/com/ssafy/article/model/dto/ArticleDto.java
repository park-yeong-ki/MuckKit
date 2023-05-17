package com.ssafy.article.model.dto;

import java.util.List;

import com.ssafy.comment.model.dto.CommentDto;

public class ArticleDto {
	private int articleId;
	private String articleTitle;
	private String articleContent;
	private String createdTime;
	private String articleWriter;
	private int articleType;
	private int hit;
	private int articleHeart;
	private List<CommentDto> comments;
	
	public ArticleDto() {}
	
	public ArticleDto(int articleId, String articleTitle, String articleContent, String createdTime,
			String articleWriter, int articleType, int hit, int articleHeart, List<CommentDto> comments) {
		super();
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.createdTime = createdTime;
		this.articleWriter = articleWriter;
		this.articleType = articleType;
		this.hit = hit;
		this.articleHeart = articleHeart;
		this.comments = comments;
	}

	public int getArticle_heart() {
		return articleHeart;
	}

	public void setArticle_heart(int article_heart) {
		this.articleHeart = article_heart;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}


	public int getArticle_id() {
		return articleId;
	}

	public void setArticle_id(int article_id) {
		this.articleId = article_id;
	}

	public String getArticle_title() {
		return articleTitle;
	}

	public void setArticle_title(String article_title) {
		this.articleTitle = article_title;
	}

	public String getArticle_content() {
		return articleContent;
	}

	public void setArticle_content(String article_content) {
		this.articleContent = article_content;
	}

	public String getCreated_time() {
		return createdTime;
	}

	public void setCreated_time(String created_time) {
		this.createdTime = created_time;
	}

	public String getArticle_writer() {
		return articleWriter;
	}

	public void setArticle_writer(String article_writer) {
		this.articleWriter = article_writer;
	}

	public int getArticle_type() {
		return articleType;
	}

	public void setArticle_type(int article_type) {
		this.articleType = article_type;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
	
	
}
