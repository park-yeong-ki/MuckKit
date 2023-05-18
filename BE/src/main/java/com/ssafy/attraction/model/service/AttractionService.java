package com.ssafy.attraction.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.attraction.model.dto.AttractionDto;
import com.ssafy.attraction.model.mapper.AttractionMapper;

@Service
public class AttractionService {
	private AttractionMapper mapper;

	public AttractionService(AttractionMapper mapper) {
		this.mapper = mapper;
	}
	
	//관광지 추가
	public int addAttraction(AttractionDto aDto) {
		return mapper.createAttraction(aDto);
	}
	
	//관광지 수정
	public int modifyAttraction(AttractionDto aDto) {
		return mapper.updateAttraction(aDto);
	}
	
	//관광지 조회
	public AttractionDto selectOne(int contentId) {
		return mapper.readOne(contentId);
	}
	
	//관광지 검색어 조회
	public List<AttractionDto> selectAttractions(String keyword){
		return mapper.readAttractions("%"+keyword+"%");
	}
	
	//관광지 삭제
	public int removeAttraction(int contentId) {
		return mapper.deleteAttraction(contentId);
	}
	
	//플랜에 포함된 관광지 조회
	public List<AttractionDto> selectByPlanId(int planId){
		return mapper.readPlanAttraction(planId);
	}
}
