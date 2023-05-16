package com.ssafy.plan.model.dto;

public class PlanDto {
	private int plan_id;
	private String member_id;
	private String plan_title;
	private String start_date;
	private String end_date;
	private String plan_description;
	private int hit;
	
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getPlan_id() {
		return plan_id;
	}
	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getPlan_title() {
		return plan_title;
	}
	public void setPlan_title(String plan_title) {
		this.plan_title = plan_title;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getPlan_description() {
		return plan_description;
	}
	public void setPlan_description(String plan_description) {
		this.plan_description = plan_description;
	}
	
	public PlanDto() {}
	public PlanDto(int plan_id, String member_id, String plan_title, String start_date, String end_date,
			String plan_description, int hit) {
		this.plan_id = plan_id;
		this.member_id = member_id;
		this.plan_title = plan_title;
		this.start_date = start_date;
		this.end_date = end_date;
		this.plan_description = plan_description;
		this.hit = hit;
	}
	
	
	
}
