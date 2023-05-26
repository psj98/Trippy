package com.ssafy.enjoytrip.attraction.model.dto;

public class AttractionGugunDto {
	private int gugunCode;
	private String gugunName;
	private int sidoCode;

	public AttractionGugunDto() {
		super();
	}

	public AttractionGugunDto(int gugunCode, String gugunName, int sidoCode) {
		super();
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
		this.sidoCode = sidoCode;
	}

	public int getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	@Override
	public String toString() {
		return "AttractionGugunDto [gugunCode=" + gugunCode + ", gugunName=" + gugunName + ", sidoCode=" + sidoCode
				+ "]";
	}
}
