package com.ssafy.enjoytrip.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.notice.model.dao.NoticeDao;
import com.ssafy.enjoytrip.notice.model.dto.NoticeDto;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.SizeConstant;

@Service
public class NoticeServiceImpl implements NoticeService {
	private NoticeDao noticeDao;

	@Autowired
	public NoticeServiceImpl(NoticeDao noticeDao) {
		super();
		this.noticeDao = noticeDao;
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

		return noticeDao.listArticle(param);
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

		int totalCount = noticeDao.getTotalArticleCount(param);
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
		return noticeDao.getArticle(articleNo);
	}

	@Override
	public int writeArticle(NoticeDto noticeDto) throws Exception {
		return noticeDao.writeArticle(noticeDto);
	}

	@Override
	public int updateHit(int articleNo) throws Exception {
		return noticeDao.updateHit(articleNo);
	}

	@Override
	public int modifyArticle(NoticeDto noticeDto) throws Exception {
		return noticeDao.modifyArticle(noticeDto);
	}

	@Override
	public int deleteArticle(int articleNo) throws Exception {
		return noticeDao.deleteArticle(articleNo);
	}
}