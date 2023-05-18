package com.ssafy.plan.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.article.model.dto.ArticleDto;
import com.ssafy.attraction.model.dto.AttractionDto;
import com.ssafy.plan.model.dto.PlanDto;
import com.ssafy.plan.model.mapper.PlanMapper;

@Service
public class PlanService {
	
	private PlanMapper mapper;
	
	public PlanService(PlanMapper mapper) {
		this.mapper = mapper;
	}
	
	
	public List<PlanDto> selectAll() {
		return mapper.readAll();
	}
	
	public PlanDto select(int planId) {
		return mapper.read(planId);
	}

	@Transactional
	public int modify(PlanDto dto) {
		mapper.deletePlanAttraction(dto.getPlanId());
		if (dto.getAttractions() != null) {
			for (AttractionDto aDto : dto.getAttractions()) {
				mapper.createPlanAttraction(dto.getPlanId(), aDto.getContentId());
			}	
		}
		return mapper.update(dto);
	}


	public int remove(int planId) {
		return mapper.delete(planId);
	}


	public void updateHit(int planId) {
		mapper.updateHit(planId);
		
	}

	@Transactional
	public int write(PlanDto dto) {
		int result = mapper.insert(dto);
		if (dto.getAttractions() != null) {
			for (AttractionDto aDto : dto.getAttractions()) {
				mapper.createPlanAttraction(dto.getPlanId(), aDto.getContentId());
			}	
		}
		return result;
	}
	
	//내가 작성한 여행계획리스트 불러오기
	public List<PlanDto> selectMyPlan(String memberId){
		return mapper.readMyPlan(memberId);
	}
	
	//회원이 좋아요한 게시글 조회
	public List<PlanDto> selectMyHeart(String memberId){
		return mapper.readMyHeart(memberId);
	}
	
	//플랜 좋아요 누르기
	public int pressHeart(String memberId, int planId) {
		String result = mapper.getPlanHeart(memberId, planId);
		
		if (result == null) { //하트를 누르지 않은 경우
			return mapper.createHeart(memberId, planId);
		} else { //이미 하트를 누른 경우
			return mapper.deleteHeart(memberId, planId);
		}
	}
}
