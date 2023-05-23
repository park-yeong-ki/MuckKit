package com.ssafy.article.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.article.model.dto.ArticleDto;
import com.ssafy.article.model.mapper.ArticleMapper;
import com.ssafy.attraction.model.mapper.AttractionMapper;
import com.ssafy.comment.model.mapper.CommentMapper;
import com.ssafy.hashtag.model.mapper.HashtagMapper;
import com.ssafy.plan.model.mapper.PlanMapper;

@Service
public class ArticleService {

	private ArticleMapper mapper;
	private PlanMapper planMapper;
	private CommentMapper commentMapper;
	private AttractionMapper attractionMapper;
	private HashtagMapper hashtagMapper;
	
	public ArticleService(ArticleMapper mapper, PlanMapper planMapper, CommentMapper commentMapper,
			AttractionMapper attractionMapper, HashtagMapper hashtagMapper) {
		super();
		this.mapper = mapper;
		this.planMapper = planMapper;
		this.commentMapper = commentMapper;
		this.attractionMapper = attractionMapper;
		this.hashtagMapper = hashtagMapper;
	}

	//게시글 작성
	public int writeArticle(ArticleDto articleDto) {
		return mapper.createArticle(articleDto);
	}
	
	//게시글 조회
	@Transactional
	public ArticleDto selectOne(int articleId) {
		ArticleDto findArticle = mapper.read(articleId);
		findArticle.setPlan(planMapper.read(findArticle.getPlanId()));
		findArticle.getPlan().setAttractions(attractionMapper.readPlanAttraction(findArticle.getPlanId()));
		findArticle.getPlan().setHashtags(planMapper.readHashtags(findArticle.getPlanId()));
		findArticle.setComments(commentMapper.readComments(findArticle.getArticleId()));
		return findArticle;
	}
	
	//게시글 전체 조회(조건 검색)
	@Transactional
	public List<ArticleDto> selectAll(String sort){
		List<ArticleDto> findArticles =  mapper.readAll(sort);
		for (int i = 0; i < findArticles.size(); i++) {
			findArticles.get(i).setPlan(planMapper.read(findArticles.get(i).getPlanId()));
			findArticles.get(i).getPlan().setAttractions(attractionMapper.readPlanAttraction(findArticles.get(i).getPlanId()));
			findArticles.get(i).getPlan().setHashtags(planMapper.readHashtags(findArticles.get(i).getPlanId()));
		}
		return findArticles;
	}
	
	//게시글 수정
	public int modify(ArticleDto articleDto) {
		return mapper.update(articleDto);
	}
	
	//게시글 삭제
	public int remove(int articleId) {
		return mapper.delete(articleId);
	}

	public void updateHit(int articleId) {
		mapper.updateHit(articleId);
	}
	
	//회원 작성 목록 조회
	public List<ArticleDto> selectMyArticle(String memberId) {
		return mapper.readMyArticle(memberId);
	}
	
	//회원이 좋아요한 게시글 조회
	public List<ArticleDto> selectMyHeart(String memberId){
		return mapper.readMyHeart(memberId);
	}
	
	//게시글 좋아요 누르기
	@Transactional
	public int pressHeart(String memberId, int articleId) {
		String result = mapper.getArticleHeart(memberId, articleId);
		
		if (result == null) { //하트를 누르지 않은 경우
			return mapper.createHeart(memberId, articleId);
		} else { //이미 하트를 누른 경우
			return mapper.deleteHeart(memberId, articleId);
		}
	}

}
