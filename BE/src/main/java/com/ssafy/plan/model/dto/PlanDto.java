package com.ssafy.plan.model.dto;

import java.util.List;

import com.ssafy.attraction.model.dto.AttractionDto;

public class PlanDto {
	private int planId;
	private String memberId;
	private String planTitle;
	private String startDate;
	private String endDate;
	private String planDescription;
	private int hit;
	private int planHeart;
	private List<AttractionDto> attractions;
	
	public PlanDto() {}

	public PlanDto(int planId, String memberId, String planTitle, String startDate, String endDate,
			String planDescription, int hit, int planHeart, List<AttractionDto> attractions) {
		super();
		this.planId = planId;
		this.memberId = memberId;
		this.planTitle = planTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.planDescription = planDescription;
		this.hit = hit;
		this.planHeart = planHeart;
		this.attractions = attractions;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPlanTitle() {
		return planTitle;
	}

	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getPlanHeart() {
		return planHeart;
	}

	public void setPlanHeart(int planHeart) {
		this.planHeart = planHeart;
	}

	public List<AttractionDto> getAttractions() {
		return attractions;
	}

	public void setAttractions(List<AttractionDto> attractions) {
		this.attractions = attractions;
	}	
	
}
