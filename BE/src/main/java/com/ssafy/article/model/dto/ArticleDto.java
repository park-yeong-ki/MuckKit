package com.ssafy.article.model.dto;

public class ArticleDto {
	private int article_id;
	private String article_title;
	private String article_content;
	private String created_time;
	private String article_writer;
	private int article_type;
	private int hit;
	
	public ArticleDto() {}
	
	public ArticleDto(int article_id, String article_title, String article_content, String created_time,
			String article_writer, int article_type, int hit) {
		this.article_id = article_id;
		this.article_title = article_title;
		this.article_content = article_content;
		this.created_time = created_time;
		this.article_writer = article_writer;
		this.article_type = article_type;
	}
	
	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}


	public int getArticle_id() {
		return article_id;
	}

	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getArticle_writer() {
		return article_writer;
	}

	public void setArticle_writer(String article_writer) {
		this.article_writer = article_writer;
	}

	public int getArticle_type() {
		return article_type;
	}

	public void setArticle_type(int article_type) {
		this.article_type = article_type;
	}
	
	
}
