package com.ssafy.enjoytrip.hotplace.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotplaceDao {
	int plusLiked(int contentNo) throws SQLException;
	int plusPlanned(String[] list) throws SQLException;
	int minusPlanned(List<Integer> list) throws SQLException;
	List<Map<String, Object>> getTodaysAttr() throws SQLException;
}
