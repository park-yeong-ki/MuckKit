package com.ssafy.plan.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.article.model.dto.ArticleDto;
import com.ssafy.attraction.model.dto.AttractionDto;
import com.ssafy.attraction.model.mapper.AttractionMapper;
import com.ssafy.hashtag.model.dto.HashtagDto;
import com.ssafy.hashtag.model.mapper.HashtagMapper;
import com.ssafy.plan.model.dto.PlanDto;
import com.ssafy.plan.model.mapper.PlanMapper;

@Service
public class PlanService {
	private PlanMapper mapper;
	private HashtagMapper hashtagMapper;
	private AttractionMapper attractionMapper;
	
	public PlanService(PlanMapper mapper, HashtagMapper hashtagMapper, AttractionMapper attractionMapper) {
		super();
		this.mapper = mapper;
		this.hashtagMapper = hashtagMapper;
		this.attractionMapper = attractionMapper;
	}

	@Transactional
	public Map<String, Object> selectAll() {
		List<PlanDto> planDtos = mapper.readAll();
		for (int i = 0; i < planDtos.size(); i++) {
			planDtos.get(i).setHashtags(mapper.readHashtags(planDtos.get(i).getPlanId()));
			planDtos.get(i).setAttractions(attractionMapper.readPlanAttraction(planDtos.get(i).getPlanId()));
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("plans", planDtos);
		map.put("hashtags", hashtagMapper.selectTop10());
		return map;
	}
	
	@Transactional
	public PlanDto select(int planId) {
		PlanDto findPlan =  mapper.read(planId);
		findPlan.setHashtags(mapper.readHashtags(findPlan.getPlanId()));
		findPlan.setAttractions(attractionMapper.readPlanAttraction(findPlan.getPlanId()));
		return findPlan;
	}

	@Transactional
	public int modify(PlanDto dto) {
		mapper.deletePlanAttraction(dto.getPlanId());
		if (dto.getAttractions() != null) {
			for (AttractionDto aDto : dto.getAttractions()) {
				mapper.createPlanAttraction(dto.getPlanId(), aDto.getContentId());
			}	
		}
		
		mapper.deletePlanHashtag(dto.getPlanId());
		List<HashtagDto> hList = dto.getHashtags();
		if (hList != null) {
			for (int i = 0; i < hList.size(); i++) {
				HashtagDto findHashtag = hashtagMapper.selectOne(hList.get(i).getTagName());
				if (findHashtag == null) {
					hashtagMapper.createHashtag(hList.get(i));
					mapper.createPlanHashtag(dto.getPlanId(), hList.get(i).getHashtagId());
				}else {
					mapper.createPlanHashtag(dto.getPlanId(), findHashtag.getHashtagId());
				}
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
		
		List<HashtagDto> hList = dto.getHashtags();
		if (hList != null) {
			for (int i = 0; i < hList.size(); i++) {
				HashtagDto findHashtag = hashtagMapper.selectOne(hList.get(i).getTagName());
				if (findHashtag == null) {
					hashtagMapper.createHashtag(hList.get(i));
					mapper.createPlanHashtag(dto.getPlanId(), hList.get(i).getHashtagId());
				}else {
					mapper.createPlanHashtag(dto.getPlanId(), findHashtag.getHashtagId());
				}
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
	@Transactional
	public int pressHeart(String memberId, int planId) {
		String result = mapper.getPlanHeart(memberId, planId);
		
		if (result == null) { //하트를 누르지 않은 경우
			return mapper.createHeart(memberId, planId);
		} else { //이미 하트를 누른 경우
			return mapper.deleteHeart(memberId, planId);
		}
	}
	
	//해쉬태그별 플랜조회
	@Transactional
	public Map<String, Object> selectByHashtag(int hashtagId) {
		List<PlanDto> planDtos = mapper.readByHashtag(hashtagId);
		for (int i = 0; i < planDtos.size(); i++) {
			planDtos.get(i).setHashtags(mapper.readHashtags(planDtos.get(i).getPlanId()));
			planDtos.get(i).setAttractions(attractionMapper.readPlanAttraction(planDtos.get(i).getPlanId()));
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("plans", planDtos);
		map.put("hashtags", hashtagMapper.selectTop10());
		return map;
	}
}
