package com.ssafy.article.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.ssafy.article.model.dto.ArticleDto;

@Mapper
public interface ArticleMapper {

	int createArticle(ArticleDto articleDto);

	ArticleDto read(int articleId);

	List<ArticleDto> readAll(@Param("category") int category, @Param("sort")String sort);

	int update(ArticleDto articleDto);

	int delete(int articleId);

	int createNotice(ArticleDto articleDto);

	void updateHit(int articleId);

}
