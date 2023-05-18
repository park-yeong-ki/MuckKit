package com.ssafy.notice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.notice.model.dto.NoticeDto;
import com.ssafy.notice.model.service.NoticeService;
import com.ssafy.security.auth.MemberDetails;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	private NoticeService service;
	
	public NoticeController(NoticeService service) {
		super();
		this.service = service;
	}

	// 공지 작성
	@PostMapping("/writeNotice")
	public ResponseEntity<?> writeNotice(@RequestBody NoticeDto noticeDto, @AuthenticationPrincipal MemberDetails memberDetails) {
		MemberDto loginMember = memberDetails.getMember();
		if (loginMember.getMemberRole().equals("관리자") && noticeDto.getNoticeWriter().equals(loginMember.getMemberId())) {
			if (service.writeNotice(noticeDto) > 0) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 공지 상세조회
	@GetMapping("/{notice_id}")
	public ResponseEntity<?> read(@PathVariable("notice_id") int noticeId) {
		NoticeDto noticeDto = service.selectOne(noticeId);
		if (noticeDto != null) {
			service.updateHit(noticeId);
			return new ResponseEntity<>(noticeDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 전체조회
	@GetMapping
	public ResponseEntity<?> readList(@RequestParam("sort") String sort) {
		
		return new ResponseEntity<>(service.selectAll(sort), HttpStatus.OK);
	}
	
	// 공지 수정
	@PutMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody NoticeDto noticeDto, @AuthenticationPrincipal MemberDetails memberDetails) {
		MemberDto loginMember = memberDetails.getMember();
		if (noticeDto.getNoticeWriter().equals(loginMember.getMemberId())) {
			if (service.modify(noticeDto) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// 공지 삭제
	@DeleteMapping("/{notice_id}")
	public ResponseEntity<?> remove(@PathVariable("notice_id") int noticeId, @AuthenticationPrincipal MemberDetails memberDetails) {
		MemberDto loginMember = memberDetails.getMember();
		if (service.selectOne(noticeId).getNoticeWriter().equals(loginMember.getMemberId())) {
			if (service.remove(noticeId) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
