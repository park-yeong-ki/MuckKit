package com.ssafy.plan.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.article.model.dto.ArticleDto;
import com.ssafy.plan.model.dto.PlanDto;

@Mapper
public interface PlanMapper {

	List<PlanDto> readAll();

	PlanDto read(int plan_id);

	int update(PlanDto dto);

	int delete(int plan_id);

	void updateHit(int plan_id);

	int insert(PlanDto dto);

	List<PlanDto> readMyPlan(String memberId);
	
	List<PlanDto> readMyHeart(String memberId);
	
	String getPlanHeart(@Param("memberId")String memberId, @Param("planId")int planId);
	
	int createHeart(@Param("memberId")String memberId, @Param("planId")int planId);
	
	int deleteHeart(@Param("memberId")String memberId, @Param("planId")int planId);
}
