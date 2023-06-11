package com.ssafy.comment.model.dto;

public class CommentDto {
	private int commentId;
	private String content;
	private String commentWriter;
	private String createdTime;
	private int articleId;
	
	public CommentDto() {}

	public CommentDto(int commentId, String content, String commentWriter, String createdTime, int articleId) {
		this.commentId = commentId;
		this.content = content;
		this.commentWriter = commentWriter;
		this.createdTime = createdTime;
		this.articleId = articleId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCommentWriter() {
		return commentWriter;
	}

	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
}
