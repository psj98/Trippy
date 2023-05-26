package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionSidoDto;

public interface IAttractionSidoService {
	AttractionSidoDto findSido(int sidoCode); // sidoCode에 따라 sido 정보 가져오기
	
	List<AttractionSidoDto> selectAll();
}
