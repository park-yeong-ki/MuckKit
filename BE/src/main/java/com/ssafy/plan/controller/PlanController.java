package com.ssafy.plan.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.service.AttractionService;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.plan.model.dto.PlanDto;
import com.ssafy.plan.model.service.PlanService;
import com.ssafy.security.auth.MemberDetails;

@RestController
@RequestMapping("plan")
public class PlanController {
	private PlanService service;
	private AttractionService attractionService;
		
	public PlanController(PlanService service, AttractionService attractionService) {
		this.service = service;
		this.attractionService = attractionService;
	}

	// 플랜 전체 조회
	@GetMapping
	public ResponseEntity<?> readPlan(){
		return new ResponseEntity<>(service.selectAll(), HttpStatus.OK);
	}
	
	//플랜 상세 조회
	@GetMapping("/{plan_id}")
	public ResponseEntity<?> read(@PathVariable("plan_id") int planId){
		PlanDto dto = service.select(planId);
		if(dto != null) {
			service.updateHit(planId);
			dto.setAttractions(attractionService.selectByPlanId(planId));
			return new ResponseEntity<> (dto, HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 플랜 정렬 조회
	

	// 플랜 작성
	@PostMapping("/write")
	public ResponseEntity<?> write(@RequestBody PlanDto dto){
		if(service.write(dto) > 0) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 플랜 수정
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody PlanDto dto, @AuthenticationPrincipal MemberDetails memberDetails) {
		MemberDto loginMember = memberDetails.getMember();
		if (loginMember.getMemberId().equals(dto.getMemberId())) {
			if (service.modify(dto) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	// 플랜 삭제
	@DeleteMapping("/{plan_id}")
	public ResponseEntity<?> delete(@PathVariable("plan_id") int planId, @AuthenticationPrincipal MemberDetails memberDetails){
		MemberDto loginMember = memberDetails.getMember();
		if (loginMember.getMemberRole().equals("관리자") || loginMember.getMemberId().equals(service.select(planId).getMemberId())) {
			if(service.remove(planId) > 0) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//플랜 좋아요
	@GetMapping("/heart/{plan_id}")
	public ResponseEntity<?> heart(@PathVariable("plan_id") int planId, @AuthenticationPrincipal MemberDetails memberDetails){
		MemberDto loginMember = memberDetails.getMember();
		if (service.pressHeart(loginMember.getMemberId(), planId) > 0) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
