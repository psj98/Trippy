package com.ssafy.enjoytrip.attraction.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.model.dao.IAttractionDescDao;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionDescDto;

@Service
public class AttractionDescServiceImpl implements IAttractionDescService {
	private IAttractionDescDao attractionDescDao;

	@Autowired
	public AttractionDescServiceImpl(IAttractionDescDao attractionDescDao) {
		super();
		this.attractionDescDao = attractionDescDao;
	}

	@Override
	public AttractionDescDto findDesc(int contentId) {
		return attractionDescDao.select(contentId);
	}
}
