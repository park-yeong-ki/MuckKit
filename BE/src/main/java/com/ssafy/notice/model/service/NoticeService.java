package com.ssafy.notice.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.notice.model.dto.NoticeDto;
import com.ssafy.notice.model.mapper.NoticeMapper;

@Service
public class NoticeService {
	private NoticeMapper mapper;
	
	public NoticeService(NoticeMapper mapper) {
		this.mapper = mapper;
	}
	
	//공지 작성
	public int writeNotice(NoticeDto noticeDto) {
		return mapper.createNotice(noticeDto);
	}
	
	//공지 조회
	public NoticeDto selectOne(int noticeId) {
		return mapper.read(noticeId);
	}
	
	//공지 전체 조회(조건 검색)
	public List<NoticeDto> selectAll(String sort){
		return mapper.readAll(sort);
	}
	
	//공지 수정
	public int modify(NoticeDto noticeDto) {
		return mapper.update(noticeDto);
	}
	
	//공지 삭제
	public int remove(int noticeId) {
		return mapper.delete(noticeId);
	}

	public void updateHit(int noticeId) {
		mapper.updateHit(noticeId);
	}
}
