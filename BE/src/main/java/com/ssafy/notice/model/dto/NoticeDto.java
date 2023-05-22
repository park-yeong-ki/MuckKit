package com.ssafy.notice.model.dto;

public class NoticeDto {
	private int noticeId;
	private String noticeTitle;
	private String noticeContent;
	private String createdTime;
	private String noticeWriter;
	private int hit;
	
	public NoticeDto() {}
	public NoticeDto(int noticeId, String noticeTitle, String noticeContent, String createdTime, String noticeWriter,
			int hit) {
		super();
		this.noticeId = noticeId;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.createdTime = createdTime;
		this.noticeWriter = noticeWriter;
		this.hit = hit;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "NoticeDto [noticeId=" + noticeId + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", createdTime=" + createdTime + ", noticeWriter=" + noticeWriter + ", hit=" + hit + "]";
	}
	
	
}
