package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionGugunDto;

public interface IAttractionGugunService {
	List<AttractionGugunDto> findGugunList(int sido_code); // sido에 따라 gugun 목록 가져오기
}
