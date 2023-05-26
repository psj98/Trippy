package com.ssafy.enjoytrip.myplan.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.myplan.model.dto.MyPlanDto;

@Mapper
public interface MyPlanDao {
	List<Map<String, String>> listArticle(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	Map<String, String> getArticle(int articleNo) throws SQLException;

	int writeArticle(MyPlanDto myPlanDto) throws SQLException;

	int updateHit(int articleNo) throws SQLException;

	int modifyArticle(MyPlanDto myPlanDto) throws SQLException;

	int deleteArticle(int articleNo) throws SQLException;
	
	int deleteAllArticle(String userId) throws SQLException;
	
	List<Map<String, String>> shareArticle(Map<String, Object> param) throws SQLException;
	
	int updateShare(int articleNo) throws SQLException;
	
	int updateNoShare(int articleNo) throws SQLException;
}
