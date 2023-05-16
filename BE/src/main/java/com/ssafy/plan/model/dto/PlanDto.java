package com.ssafy.plan.model.dto;

public class PlanDto {
	private int planId;
	private String memberId;
	private String planTitle;
	private String startDate;
	private String endDate;
	private String planDescription;
	private int hit;
	
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getPlan_id() {
		return planId;
	}
	public void setPlan_id(int plan_id) {
		this.planId = plan_id;
	}
	public String getMember_id() {
		return memberId;
	}
	public void setMember_id(String member_id) {
		this.memberId = member_id;
	}
	public String getPlan_title() {
		return planTitle;
	}
	public void setPlan_title(String plan_title) {
		this.planTitle = plan_title;
	}
	public String getStart_date() {
		return startDate;
	}
	public void setStart_date(String start_date) {
		this.startDate = start_date;
	}
	public String getEnd_date() {
		return endDate;
	}
	public void setEnd_date(String end_date) {
		this.endDate = end_date;
	}
	public String getPlan_description() {
		return planDescription;
	}
	public void setPlan_description(String plan_description) {
		this.planDescription = plan_description;
	}
	
	public PlanDto() {}
	public PlanDto(int planId, String memberId, String planTitle, String startDate, String endDate,
			String planDescription, int hit) {
		super();
		this.planId = planId;
		this.memberId = memberId;
		this.planTitle = planTitle;
		this.startDate = startDate;
		this.endDate = endDate;
		this.planDescription = planDescription;
		this.hit = hit;
	}
	
	
}
