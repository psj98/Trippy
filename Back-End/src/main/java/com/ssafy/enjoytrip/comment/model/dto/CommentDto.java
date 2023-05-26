package com.ssafy.enjoytrip.comment.model.dto;

public class CommentDto {
	private int commentNo;
	private int articleNo;
	private String userId;
	private String content;
	private String registerTime;
	
	public CommentDto() {
		super();
	}

	public CommentDto(int commentNo, int articleNo, String userId, String content, String registerTime) {
		super();
		this.commentNo = commentNo;
		this.articleNo = articleNo;
		this.userId = userId;
		this.content = content;
		this.registerTime = registerTime;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "CommentDto [commentNo=" + commentNo + ", articleNo=" + articleNo + ", userId=" + userId + ", content="
				+ content + ", registerTime=" + registerTime + "]";
	}
	
}
