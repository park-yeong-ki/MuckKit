package com.ssafy.article.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.article.model.dto.ArticleDto;
import com.ssafy.article.model.mapper.ArticleMapper;

@Service
public class ArticleService {

	private ArticleMapper mapper;
	
	public ArticleService(ArticleMapper mapper) {
		this.mapper = mapper;
	}

	//게시글 작성
	public int writeArticle(ArticleDto articleDto) {
		return mapper.createArticle(articleDto);
	}
	
	//게시글 조회
	public ArticleDto selectOne(int articleId) {
		return mapper.read(articleId);
	}
	
	//게시글 전체 조회(조건 검색)
	public List<ArticleDto> selectAll(int category, String sort){
		return mapper.readAll(category, sort);
	}
	
	//게시글 수정
	public int modify(ArticleDto articleDto) {
		return mapper.update(articleDto);
	}
	
	//게시글 삭제
	public int remove(int articleId) {
		return mapper.delete(articleId);
	}

	// 공지 작성
	public int writeNotice(ArticleDto articleDto) {
		return mapper.createNotice(articleDto);
	}

	public void updateHit(int articleId) {
		mapper.updateHit(articleId);
	}
	
}
