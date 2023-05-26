package com.ssafy.enjoytrip.attraction.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionDescDto;

@Mapper
public interface IAttractionDescDao {
	AttractionDescDto select(int contentId); // contentId에 따른 설명 가져오기 -> view
}
