package com.ssafy.enjoytrip.attraction.model.dto;

public class AttractionInfoDto {
	private int contentId;
	private int contentTypeId;
	private String title;
	private String addr1;
	private String addr2;
	private String zipCode;
	private String tel;
	private String firstImage;
	private String secondImage;
	private String readCount;
	private String sidoCode;
	private String gugunCode;
	private double latitude;
	private double longitude;
	private String mLevel;
	private String contentTypeName;

	private AttractionDescDto descDto;

	public AttractionInfoDto() {
		super();
	}

	public AttractionInfoDto(int contentId, int contentTypeId, String title, String addr1, String addr2, String zipCode,
			String tel, String firstImage, String secondImage, String readCount, String sidoCode, String gugunCode,
			double latitude, double longitude, String mLevel, String contentTypeName, AttractionDescDto descDto) {
		super();
		this.contentId = contentId;
		this.contentTypeId = contentTypeId;
		this.title = title;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipCode = zipCode;
		this.tel = tel;
		this.firstImage = firstImage;
		this.secondImage = secondImage;
		this.readCount = readCount;
		this.sidoCode = sidoCode;
		this.gugunCode = gugunCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.mLevel = mLevel;
		this.contentTypeName = contentTypeName;
		this.descDto = descDto;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getSecondImage() {
		return secondImage;
	}

	public void setSecondImage(String secondImage) {
		this.secondImage = secondImage;
	}

	public String getReadCount() {
		return readCount;
	}

	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}

	public String getSidoCode() {
		return sidoCode;
	}

	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}

	public String getGugunCode() {
		return gugunCode;
	}

	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getmLevel() {
		return mLevel;
	}

	public void setmLevel(String mLevel) {
		this.mLevel = mLevel;
	}

	public String getContentTypeName() {
		return contentTypeName;
	}

	public void setContentTypeName(String contentTypeName) {
		this.contentTypeName = contentTypeName;
	}

	public AttractionDescDto getDescDto() {
		return descDto;
	}

	public void setDescDto(AttractionDescDto descDto) {
		this.descDto = descDto;
	}

	@Override
	public String toString() {
		return "AttractionInfoDto [contentId=" + contentId + ", contentTypeId=" + contentTypeId + ", title=" + title
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", zipCode=" + zipCode + ", tel=" + tel + ", firstImage="
				+ firstImage + ", secondImage=" + secondImage + ", readCount=" + readCount + ", sidoCode=" + sidoCode
				+ ", gugunCode=" + gugunCode + ", latitude=" + latitude + ", longitude=" + longitude + ", mLevel="
				+ mLevel + ", contentTypeName=" + contentTypeName + ", descDto=" + descDto + "]";
	}
}
