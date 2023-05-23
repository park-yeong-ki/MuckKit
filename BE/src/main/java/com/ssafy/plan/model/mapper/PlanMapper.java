package com.ssafy.plan.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.article.model.dto.ArticleDto;
import com.ssafy.hashtag.model.dto.HashtagDto;
import com.ssafy.plan.model.dto.PlanDto;

@Mapper
public interface PlanMapper {

	List<PlanDto> readAll();

	PlanDto read(int planId);

	int update(PlanDto dto);

	int delete(int planId);

	void updateHit(int planId);

	int insert(PlanDto dto);

	List<PlanDto> readMyPlan(String memberId);
	
	List<PlanDto> readMyHeart(String memberId);
	
	String getPlanHeart(@Param("memberId")String memberId, @Param("planId")int planId);
	
	int createHeart(@Param("memberId")String memberId, @Param("planId")int planId);
	
	int deleteHeart(@Param("memberId")String memberId, @Param("planId")int planId);
	
	int createPlanAttraction(@Param("planId")int planId, @Param("contentId")int contentId);
	
	int deletePlanAttraction(int planId);
	
	int createPlanHashtag(@Param("planId") int planId, @Param("hashtagId") int hashtagId);
	
	int deletePlanHashtag(int planId);
	
	List<HashtagDto> readHashtags(int planId);
	
	List<PlanDto> readByHashtag(int hashtagId);
}
