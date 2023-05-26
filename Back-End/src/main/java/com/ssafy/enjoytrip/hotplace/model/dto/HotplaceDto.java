package com.ssafy.enjoytrip.hotplace.model.dto;

public class HotplaceDto {
	private int contentId;
	private String date;
	private int planned_time;
	private int like;
	
	public HotplaceDto(int contentId, String date, int planned_time, int like) {
		super();
		this.contentId = contentId;
		this.date = date;
		this.planned_time = planned_time;
		this.like = like;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPlanned_time() {
		return planned_time;
	}

	public void setPlanned_time(int planned_time) {
		this.planned_time = planned_time;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	@Override
	public String toString() {
		return "HotplaceDto [contentId=" + contentId + ", date=" + date + ", planned_time=" + planned_time + ", like="
				+ like + "]";
	}
	
}
