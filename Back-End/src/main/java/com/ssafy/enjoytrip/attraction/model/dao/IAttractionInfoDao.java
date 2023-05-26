package com.ssafy.enjoytrip.attraction.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;

@Mapper
public interface IAttractionInfoDao {
	List<AttractionInfoDto> selectAll(Map<String, Object> map); // sido, gugun, 관광지 유형에 따른 전체 정보 가져오기

	AttractionInfoDto selectId(int contentId); // contentId로 관광지 찾기
}
