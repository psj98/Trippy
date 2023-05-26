package com.ssafy.enjoytrip.hotplace.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.hotplace.model.dao.HotplaceDao;

@Service
public class HotplaceServiceImpl implements HotplaceService {
	private HotplaceDao hotplaceDao;
	
	@Autowired
	public HotplaceServiceImpl(HotplaceDao hotplaceDao) {
		super();
		this.hotplaceDao = hotplaceDao;
	}

	@Override
	public int plusLiked(int contentNo) throws Exception {
		return hotplaceDao.plusLiked(contentNo);
	}

	@Override
	public int plusPlanned(String parser) throws Exception {
		String[] list = parser.split("&");
		
		return hotplaceDao.plusPlanned(list);
	}

	@Override
	public int minusPlanned(List<Integer> list) throws Exception {
		return hotplaceDao.minusPlanned(list);
	}

	@Override
	public List<Map<String, Object>> getTodaysAttr() throws Exception {
		return hotplaceDao.getTodaysAttr();
	}

}
