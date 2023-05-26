package com.ssafy.enjoytrip.notice.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.notice.model.dto.NoticeDto;

@Mapper
public interface NoticeDao {
	List<Map<String, String>> listArticle(Map<String, Object> param) throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	Map<String, String> getArticle(int articleNo) throws SQLException;

	int writeArticle(NoticeDto noticeDto) throws SQLException;

	int updateHit(int articleNo) throws SQLException;

	int modifyArticle(NoticeDto noticeDto) throws SQLException;

	int deleteArticle(int articleNo) throws SQLException;
}
