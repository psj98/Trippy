package com.ssafy.enjoytrip.hotplace.model.service;

import java.util.List;
import java.util.Map;

public interface HotplaceService {
	int plusLiked(int contentNo) throws Exception;
	int plusPlanned(String parser) throws Exception;
	int minusPlanned(List<Integer> list) throws Exception;
	List<Map<String, Object>> getTodaysAttr() throws Exception;
}
