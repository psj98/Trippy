package com.ssafy.enjoytrip.question.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.question.model.dao.QuestionDao;
import com.ssafy.enjoytrip.question.model.dto.QuestionDto;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.SizeConstant;

@Service
public class QuestionServiceImpl implements QuestionService {
	private QuestionDao questionDao;

	@Autowired
	public QuestionServiceImpl(QuestionDao questionDao) {
		super();
		this.questionDao = questionDao;
	}

	@Override
	public List<Map<String, String>> listArticle(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");

//		if("userid".equals(key))
//			key = "user_id";

		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));

		int pgno = Integer.parseInt(map.get("pgno"));
		int start = pgno * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return questionDao.listArticle(param);
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

		int totalCount = questionDao.getTotalArticleCount(param);
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
		return questionDao.getArticle(articleNo);
	}

	@Override
	public int writeArticle(QuestionDto questionDto) throws Exception {
		return questionDao.writeArticle(questionDto);
	}

	@Override
	public int updateHit(int articleNo) throws Exception {
		return questionDao.updateHit(articleNo);
	}

	@Override
	public int modifyArticle(QuestionDto questionDto) throws Exception {
		return questionDao.modifyArticle(questionDto);
	}

	@Override
	public int deleteArticle(int articleNo) throws Exception {
		return questionDao.deleteArticle(articleNo);
	}
}