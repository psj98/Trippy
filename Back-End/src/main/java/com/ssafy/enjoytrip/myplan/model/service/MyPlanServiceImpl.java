package com.ssafy.enjoytrip.myplan.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.myplan.model.dao.MyPlanDao;
import com.ssafy.enjoytrip.myplan.model.dto.MyPlanDto;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.SizeConstant;

@Service
public class MyPlanServiceImpl implements MyPlanService {
	private MyPlanDao myPlanDao;

	@Autowired
	public MyPlanServiceImpl(MyPlanDao myPlanDao) {
		super();
		this.myPlanDao = myPlanDao;
	}

	@Override
	public List<Map<String, String>> listArticle(String id, String key, String word) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("id", id);
		param.put("key", (key==null || key.isEmpty()) ? "" : key);
		param.put("word", (word==null || word.isEmpty()) ? "" : word);

		return myPlanDao.listArticle(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");

//		if ("userid".equals(key))
//			key = "user_id";

		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));

		int totalCount = myPlanDao.getTotalArticleCount(param);
		pageNavigation.setTotalCount(totalCount);

		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);

		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);

		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

	@Override
	public Map<String, String> getArticle(int articleNo) throws Exception {
		return myPlanDao.getArticle(articleNo);
	}

	@Override
	public int writeArticle(MyPlanDto myPlanDto) throws Exception {
		return myPlanDao.writeArticle(myPlanDto);
	}

	@Override
	public int updateHit(int articleNo) throws Exception {
		return myPlanDao.updateHit(articleNo);
	}

	@Override
	public int modifyArticle(MyPlanDto myPlanDto) throws Exception {
		return myPlanDao.modifyArticle(myPlanDto);
	}

	@Override
	public int deleteArticle(int articleNo) throws Exception {
		return myPlanDao.deleteArticle(articleNo);
	}
	
	@Override
	public int deleteAllArticle(String userId) throws SQLException {
		return myPlanDao.deleteAllArticle(userId);
	}

	@Override
	public List<Map<String, String>> shareArticle(String key, String word) throws SQLException {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("key", (key==null || key.isEmpty()) ? "" : key);
		param.put("word", (word==null || word.isEmpty()) ? "" : word);

		return myPlanDao.shareArticle(param);
	}

	@Override
	public int updateShare(int articleNo) throws SQLException {
		return myPlanDao.updateShare(articleNo);
	}

	@Override
	public int updateNoShare(int articleNo) throws SQLException {
		return myPlanDao.updateNoShare(articleNo);
	}
}