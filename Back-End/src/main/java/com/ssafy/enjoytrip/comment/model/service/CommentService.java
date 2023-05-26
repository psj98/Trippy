package com.ssafy.enjoytrip.comment.model.service;

import java.util.List;

import com.ssafy.enjoytrip.comment.model.dto.CommentDto;

public interface CommentService{
	
	List<CommentDto> listComment(String boardType, int articleNo) throws Exception;
	
	int writeComment(String boardType, CommentDto commentDto) throws Exception;
	
	int modifyComment(String boardType, CommentDto commentDto) throws Exception;

	int deleteComment(String boardType, int commentNo) throws Exception;
}
