package com.ssafy.enjoytrip.attraction.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionGugunDto;

@Mapper
public interface IAttractionGugunDao {
	List<AttractionGugunDto> selectGugun(int sidoCode); // sido에 따라 gugun 가져오기
}
