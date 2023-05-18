package com.ssafy.hashtag.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.hashtag.model.dto.HashtagDto;

@Mapper
public interface HashtagMapper {
	public int createHashtag(HashtagDto hDto);
		
	public HashtagDto selectOne(String tagName);
	
	public int deleteHashtag(int hashtagId);
}
