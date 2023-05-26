package com.ssafy.enjoytrip.board.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.board.model.dao.BoardDao;
import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.util.PageNavigation;
import com.ssafy.enjoytrip.util.SizeConstant;

@Service
public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao;

	@Autowired
	public BoardServiceImpl(BoardDao boardDao) {
		super();
		this.boardDao = boardDao;
	}

	@Override
	public List<Map<String, String>> listArticle(String boardType, String key, String word) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		if (boardType == null || boardType.isEmpty())
			return null;

		param.put("boardType", boardType);
		param.put("key", (key == null || key.isEmpty()) ? "" : key);
		param.put("word", (word == null || word.isEmpty()) ? "" : word);

		return boardDao.listArticle(param);
	}
	
	@Override
	public Map<String, String> getBoardName(String boardType) throws SQLException {
		return boardDao.getBoardName(boardType);
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

		int totalCount = boardDao.getTotalArticleCount(param);
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
	public Map<String, String> getArticle(String boardType, int articleNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		if (boardType == null || boardType.isEmpty())
			return null;

		param.put("boardType", boardType);
		param.put("articleNo", articleNo);

		return boardDao.getArticle(param);
	}

	@Override
	public int writeArticle(String boardType, BoardDto boardDto) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		System.out.println(boardDto);
		
		if(boardType == null || boardType.isEmpty()) return -1;

		param.put("boardType", boardType);
		param.put("board", boardDto);
		
		return boardDao.writeArticle(param);
	}

	@Override
	public int updateHit(String boardType, int articleNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		if(boardType == null || boardType.isEmpty()) return -1;

		param.put("boardType", boardType);
		param.put("articleNo", articleNo);
		
		return boardDao.updateHit(param);
	}

	@Override
	public int modifyArticle(String boardType, BoardDto boardDto) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		if(boardType == null || boardType.isEmpty()) return -1;

		param.put("boardType", boardType);
		param.put("board", boardDto);
		
		return boardDao.modifyArticle(param);
	}

	@Override
	public int deleteArticle(String boardType, int articleNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		if(boardType == null || boardType.isEmpty()) return -1;

		param.put("boardType", boardType);
		param.put("articleNo", articleNo);
		
		return boardDao.deleteArticle(param);
	}

	
}