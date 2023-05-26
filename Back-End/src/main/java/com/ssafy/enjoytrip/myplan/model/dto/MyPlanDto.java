package com.ssafy.enjoytrip.myplan.model.dto;

public class MyPlanDto {
	private int articleNo;
	private String userId;
	private String subject;
	private String content;
	private String attractionIdList;
	private int hit;
	private String registerTime;
	private int shareCheck;

	public MyPlanDto() {
		super();
	}

	public MyPlanDto(int articleNo, String userId, String subject, String content, String attractionIdList, int hit,
			String registerTime, int shareCheck) {
		super();
		this.articleNo = articleNo;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
		this.attractionIdList = attractionIdList;
		this.hit = hit;
		this.registerTime = registerTime;
		this.shareCheck = shareCheck;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getAttractionIdList() {
		return attractionIdList;
	}

	public void setAttractionIdList(String attractionIdList) {
		this.attractionIdList = attractionIdList;
	}

	public int getShareCheck() {
		return shareCheck;
	}

	public void setShareCheck(int shareCheck) {
		this.shareCheck = shareCheck;
	}

	@Override
	public String toString() {
		return "MyPlanDto [articleNo=" + articleNo + ", userId=" + userId + ", subject=" + subject + ", content="
				+ content + ", attractionIdList=" + attractionIdList + ", hit=" + hit + ", registerTime=" + registerTime
				+ ", shareCheck=" + shareCheck + "]";
	}
}
