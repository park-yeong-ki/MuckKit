package com.ssafy.attraction.controller;

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

import com.ssafy.attraction.model.dto.AttractionDto;
import com.ssafy.attraction.model.service.AttractionService;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.security.auth.MemberDetails;

@RestController
@RequestMapping("/attraction")
public class AttractionController {
	private AttractionService attractionService;

	public AttractionController(AttractionService attractionService) {
		this.attractionService = attractionService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addAttraction(@RequestBody AttractionDto aDto, @AuthenticationPrincipal MemberDetails memberDetails){
		MemberDto loginMember = memberDetails.getMember();
		if (loginMember.getMemberRole().equals("관리자")) {
			if (attractionService.addAttraction(aDto) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{content-id}")
	public ResponseEntity<?> selectOne(@PathVariable("content-id") int contentId){
		AttractionDto findAttraction = attractionService.selectOne(contentId);
		if (findAttraction != null) {
			return new ResponseEntity<>(findAttraction, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> selectAttracions(@RequestParam("keyword") String keyword){
		return new ResponseEntity<>(attractionService.selectAttractions(keyword), HttpStatus.OK);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<?> modifyAttraction(@RequestBody AttractionDto aDto, @AuthenticationPrincipal MemberDetails memberDetails){
		MemberDto loginMember = memberDetails.getMember();
		if (loginMember.getMemberRole().equals("관리자")) {
			if (attractionService.modifyAttraction(aDto) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{content-id}")
	public ResponseEntity<?> removeAttraction(@PathVariable("content-id") int contentId, @AuthenticationPrincipal MemberDetails memberDetails){
		MemberDto loginMember = memberDetails.getMember();
		if (loginMember.getMemberRole().equals("관리자")) {
			if (attractionService.removeAttraction(contentId) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
