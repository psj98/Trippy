package com.ssafy.enjoytrip.question.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.question.model.dto.QuestionDto;

@Mapper
public interface QuestionDao {
	List<Map<String, String>> listArticle(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	Map<String, String> getArticle(int articleNo) throws SQLException;

	int writeArticle(QuestionDto questionDto) throws SQLException;

	int updateHit(int articleNo) throws SQLException;

	int modifyArticle(QuestionDto questionDto) throws SQLException;

	int deleteArticle(int articleNo) throws SQLException;
}
