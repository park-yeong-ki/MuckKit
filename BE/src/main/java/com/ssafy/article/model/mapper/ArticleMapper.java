package com.ssafy.article.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.article.model.dto.ArticleDto;

@Mapper
public interface ArticleMapper {

	int createArticle(ArticleDto articleDto);

	ArticleDto read(int articleId);

	List<ArticleDto> readAll(String sort);

	int update(ArticleDto articleDto);

	int delete(int articleId);

	void updateHit(int articleId);

	List<ArticleDto> readMyArticle(String memberId);
	
	List<ArticleDto> readMyHeart(String memberId);
	
	String getArticleHeart(@Param("memberId")String memberId, @Param("articleId")int articleId);
	
	int createHeart(@Param("memberId")String memberId, @Param("articleId")int articleId);
	
	int deleteHeart(@Param("memberId")String memberId, @Param("articleId")int articleId);
}
