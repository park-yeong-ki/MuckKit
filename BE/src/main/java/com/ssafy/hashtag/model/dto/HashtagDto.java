package com.ssafy.hashtag.model.dto;

public class HashtagDto {
	private int hashtagId;
	private String tagName;
	
	public HashtagDto() {}
	
	public HashtagDto(int hashtagId, String tagName) {
		this.hashtagId = hashtagId;
		this.tagName = tagName;
	}

	public int getHashtagId() {
		return hashtagId;
	}

	public void setHashtagId(int hashtagId) {
		this.hashtagId = hashtagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
