package com.ssafy.attraction.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.attraction.model.dto.AttractionDto;

@Mapper
public interface AttractionMapper {
	public int createAttraction(AttractionDto aDto);
	
	public AttractionDto readOne(int contentId);
	
	public List<AttractionDto> readAttractions(String keyword);
	
	public int updateAttraction(AttractionDto aDto);
	
	public int deleteAttraction(int contentId);
	
	public List<AttractionDto> readPlanAttraction(int planId);
}
