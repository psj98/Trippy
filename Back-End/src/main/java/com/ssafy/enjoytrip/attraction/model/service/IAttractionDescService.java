package com.ssafy.enjoytrip.attraction.model.service;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionDescDto;

public interface IAttractionDescService {
	AttractionDescDto findDesc(int contentId); // contentId에 따른 설명 가져오기 -> view
}
