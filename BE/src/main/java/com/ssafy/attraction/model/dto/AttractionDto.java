package com.ssafy.attraction.model.dto;

public class AttractionDto {
	private int contentId;
	private int contentTypeId;
	private String addr1;
	private String addr2;
	private String first_image;
	private String first_image2;
	private String latitude;
	private String longitude;
	private String title;
	private String homepage;
	private String overview;
	
	public AttractionDto() {}
	
	public AttractionDto(int contentId, int contentTypeId, String addr1, String addr2, String first_image,
			String first_image2, String latitude, String longitude, String title, String homepage, String overview) {
		super();
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.first_image = first_image;
		this.first_image2 = first_image2;
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.homepage = homepage;
		this.overview = overview;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getFirst_image() {
		return first_image;
	}

	public void setFirst_image(String first_image) {
		this.first_image = first_image;
	}

	public String getFirst_image2() {
		return first_image2;
	}

	public void setFirst_image2(String first_image2) {
		this.first_image2 = first_image2;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}
}
