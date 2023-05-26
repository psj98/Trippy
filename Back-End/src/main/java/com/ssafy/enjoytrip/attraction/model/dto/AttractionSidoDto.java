package com.ssafy.enjoytrip.attraction.model.dto;

public class AttractionSidoDto {
	private int sidoCode;
	private String sidoName;

	public AttractionSidoDto() {
		super();
	}

	public AttractionSidoDto(int sidoCode, String sidoName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}

	public int getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	@Override
	public String toString() {
		return "AttractionSidoDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}
}
