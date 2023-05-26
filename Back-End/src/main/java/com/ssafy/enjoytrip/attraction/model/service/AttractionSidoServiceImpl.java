package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.model.dao.IAttractionSidoDao;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionSidoDto;

@Service
public class AttractionSidoServiceImpl implements IAttractionSidoService {
	private IAttractionSidoDao attractionSidoDao;

	@Autowired
	public AttractionSidoServiceImpl(IAttractionSidoDao attractionSidoDao) {
		super();
		this.attractionSidoDao = attractionSidoDao;
	}

	@Override
	public AttractionSidoDto findSido(int sidoCode) {
		return attractionSidoDao.selectSido(sidoCode);
	}

	@Override
	public List<AttractionSidoDto> selectAll() {
		return attractionSidoDao.selectAll();
	}
}
