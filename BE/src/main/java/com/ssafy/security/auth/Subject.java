package com.ssafy.security.auth;

public class Subject {
	private String memberId;
	private String name;
	private String type;
	
	private Subject(String memberId, String name, String type) {
		this.memberId = memberId;
		this.name = name;
		this.type = type;
	}
	
	private Subject() {}
	
	public static Subject atk(String memberId, String name) {
		return new Subject(memberId, name, "ATK");
	}
	
	public static Subject rtk(String memberId, String name) {
		return new Subject(memberId, name, "RTK");
	}

	public String getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
	
	
}
