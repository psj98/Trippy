package com.ssafy.enjoytrip.myplan.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.myplan.model.dto.MyPlanDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface MyPlanService {
	List<Map<String, String>> listArticle(String id, String key, String word) throws Exception;

	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

	Map<String, String> getArticle(int articleNo) throws Exception;

	int writeArticle(MyPlanDto myPlanDto) throws Exception;

	int updateHit(int articleNo) throws Exception;

	int modifyArticle(MyPlanDto myPlanDto) throws Exception;

	int deleteArticle(int articleNo) throws Exception;
	
	int deleteAllArticle(String userId) throws SQLException;
	
	List<Map<String, String>> shareArticle(String key, String word) throws SQLException;
	
	int updateShare(int articleNo) throws SQLException;
	
	int updateNoShare(int articleNo) throws SQLException;
}
