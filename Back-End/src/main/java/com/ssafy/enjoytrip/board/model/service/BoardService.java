package com.ssafy.enjoytrip.board.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface BoardService {
	List<Map<String, String>> listArticle(String boardType, String key, String word) throws Exception;

	Map<String, String> getBoardName(String boardType) throws SQLException;
	
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

	Map<String, String> getArticle(String boardType, int articleNo) throws Exception;

	int writeArticle(String boardType, BoardDto boardDto) throws Exception;

	int updateHit(String boardType, int articleNo) throws Exception;

	int modifyArticle(String boardType, BoardDto boardDto) throws Exception;

	int deleteArticle(String boardType, int articleNo) throws Exception;
}
