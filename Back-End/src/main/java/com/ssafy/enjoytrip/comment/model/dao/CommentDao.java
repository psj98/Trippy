package com.ssafy.enjoytrip.comment.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.comment.model.dto.CommentDto;

@Mapper
public interface CommentDao {
	
	List<CommentDto> listComment(Map<String, Object> param) throws SQLException;
	
	int writeComment(Map<String, Object> param) throws SQLException;
	
	int modifyComment(Map<String, Object> param) throws SQLException;

	int deleteComment(Map<String, Object> param) throws SQLException;
	
}
