package com.ssafy.enjoytrip.notice.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.notice.model.dto.NoticeDto;
import com.ssafy.enjoytrip.util.PageNavigation;

public interface NoticeService {
	List<Map<String, String>> listArticle(Map<String, String> map) throws Exception;

	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;

	Map<String, String> getArticle(int articleNo) throws Exception;

	int writeArticle(NoticeDto noticeDto) throws Exception;

	int updateHit(int articleNo) throws Exception;

	int modifyArticle(NoticeDto noticeDto) throws Exception;

	int deleteArticle(int articleNo) throws Exception;
}
