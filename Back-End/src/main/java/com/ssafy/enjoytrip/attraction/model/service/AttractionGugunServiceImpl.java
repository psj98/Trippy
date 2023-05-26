package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.model.dao.IAttractionGugunDao;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionGugunDto;

@Service
public class AttractionGugunServiceImpl implements IAttractionGugunService {
	private IAttractionGugunDao attractionGugunDao;

	@Autowired
	public AttractionGugunServiceImpl(IAttractionGugunDao attractionGugunDao) {
		super();
		this.attractionGugunDao = attractionGugunDao;
	}

	@Override
	public List<AttractionGugunDto> findGugunList(int sidoCode) {
		return attractionGugunDao.selectGugun(sidoCode);
	}
}
