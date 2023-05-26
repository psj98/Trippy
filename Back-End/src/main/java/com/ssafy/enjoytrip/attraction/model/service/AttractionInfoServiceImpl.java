package com.ssafy.enjoytrip.attraction.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.attraction.model.dao.IAttractionInfoDao;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;

@Service
public class AttractionInfoServiceImpl implements IAttractionInfoService {
	private IAttractionInfoDao attractionInfoDao;

	@Autowired
	public AttractionInfoServiceImpl(IAttractionInfoDao attractionInfoDao) {
		super();
		this.attractionInfoDao = attractionInfoDao;
	}

	@Override
	public List<AttractionInfoDto> findAttractionList(Map<String, Object> map) {
		return attractionInfoDao.selectAll(map);
	}

	@Override
	public AttractionInfoDto findAttraction(int contentId) {
		return attractionInfoDao.selectId(contentId);
	}
}
