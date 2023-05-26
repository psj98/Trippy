package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;

public interface IAttractionInfoService {
	List<AttractionInfoDto> findAttractionList(Map<String, Object> map); // sido, gugun, 관광지 유형에 따른 전체

	AttractionInfoDto findAttraction(int contentId); // contentId에 해당하는 관광지 찾기
}
