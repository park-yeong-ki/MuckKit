package com.ssafy.notice.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.notice.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	int createNotice(NoticeDto noticeDto);
	NoticeDto read(int noticeId);
	List<NoticeDto> readAll(String sort);
	int update(NoticeDto noticeDto);
	int delete(int noticeId);
	void updateHit(int noticeId);
}
