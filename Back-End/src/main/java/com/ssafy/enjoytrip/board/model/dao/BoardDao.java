package com.ssafy.enjoytrip.board.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;

@Mapper
public interface BoardDao {
	List<Map<String, String>> listArticle(Map<String, Object> param) throws SQLException;

	Map<String, String> getBoardName(String boardType) throws SQLException;
	
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	Map<String, String> getArticle(Map<String, Object> param) throws SQLException;

	int writeArticle(Map<String, Object> param) throws SQLException;

	int updateHit(Map<String, Object> param) throws SQLException;

	int modifyArticle(Map<String, Object> param) throws SQLException;

	int deleteArticle(Map<String, Object> param) throws SQLException;
}
