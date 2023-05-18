package com.ssafy.hashtag.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.hashtag.model.dto.HashtagDto;
import com.ssafy.hashtag.model.mapper.HashtagMapper;

@Service
public class HashtagService {
	private HashtagMapper mapper;

	public HashtagService(HashtagMapper mapper) {
		this.mapper = mapper;
	}
	
	//해시태그 작성
	public int createHashtag(HashtagDto hDto) {
		return mapper.createHashtag(hDto);
	}
	
	//해시태그 조회
	public HashtagDto readHashtag(String tagName) {
		return mapper.selectOne(tagName);
	}
	
	//해시태그 삭제
	public int removeHashtag(int hashtagId) {
		return mapper.deleteHashtag(hashtagId);
	}
}
