package com.ssafy.enjoytrip.question.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.question.model.dto.QuestionDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface QuestionService {
	List<Map<String, String>> listArticle(Map<String, String> map) throws Exception;

	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

	Map<String, String> getArticle(int articleNo) throws Exception;

	int writeArticle(QuestionDto questionDto) throws Exception;

	int updateHit(int articleNo) throws Exception;

	int modifyArticle(QuestionDto questionDto) throws Exception;

	int deleteArticle(int articleNo) throws Exception;
}
