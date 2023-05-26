package com.ssafy.enjoytrip.user.model.dto;

import java.util.List;

public class UserDto {
	private String id;
	private String password;
	private String name;
	private String emailId;
	private String emailDomain;
	private int citySido;
	private int cityGugun;
	private String userGrade;
	private List<FileInfoDto> fileInfos;

	public UserDto() {
		super();
	}

	public UserDto(String id, String password, String name, String emailId, String emailDomain, int citySido,
			int cityGugun, String userGrade, List<com.ssafy.enjoytrip.user.model.dto.FileInfoDto> fileInfos) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.citySido = citySido;
		this.cityGugun = cityGugun;
		this.userGrade = userGrade;
		this.fileInfos = fileInfos;
	}

	public List<FileInfoDto> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfoDto> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public int getCitySido() {
		return citySido;
	}

	public void setCitySido(int citySido) {
		this.citySido = citySido;
	}

	public int getCityGugun() {
		return cityGugun;
	}

	public void setCityGugun(int cityGugun) {
		this.cityGugun = cityGugun;
	}

	public String getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", password=" + password + ", name=" + name + ", emailId=" + emailId
				+ ", emailDomain=" + emailDomain + ", citySido=" + citySido + ", cityGugun=" + cityGugun
				+ ", userGrade=" + userGrade + ", fileInfos=" + fileInfos + "]";
	}

	
}
