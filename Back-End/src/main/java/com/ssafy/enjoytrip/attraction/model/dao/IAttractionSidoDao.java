package com.ssafy.enjoytrip.attraction.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionSidoDto;

@Mapper
public interface IAttractionSidoDao {
	AttractionSidoDto selectSido(int sidoCode); // sidoCode에 따라 sido 정보 가져오기
	List<AttractionSidoDto> selectAll();
}
