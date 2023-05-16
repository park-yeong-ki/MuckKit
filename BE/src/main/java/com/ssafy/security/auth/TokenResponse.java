package com.ssafy.security.auth;

public class TokenResponse {
	private final String atk;
	private final String rtk;
	
	public String getAtk() {
		return atk;
	}
	public String getRtk() {
		return rtk;
	}
	
	public TokenResponse(String atk, String rtk) {
		this.atk = atk;
		this.rtk = rtk;
	}
}
