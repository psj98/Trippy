package com.ssafy.enjoytrip.comment.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.comment.model.dao.CommentDao;
import com.ssafy.enjoytrip.comment.model.dto.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao;
		
	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		super();
		this.commentDao = commentDao;
	}

	@Override
	public List<CommentDto> listComment(String boardType, int articleNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		if (boardType == null || boardType.isEmpty())
			return null;

		param.put("boardType", boardType);
		param.put("articleNo", articleNo);
		
		return commentDao.listComment(param);
	}

	@Override
	public int writeComment(String boardType, CommentDto commentDto) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		if (boardType == null || boardType.isEmpty())
			return -1;

		param.put("boardType", boardType);
		param.put("comment", commentDto);
		
		return commentDao.writeComment(param);
	}

	@Override
	public int modifyComment(String boardType, CommentDto commentDto) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		if (boardType == null || boardType.isEmpty())
			return -1;

		param.put("boardType", boardType);
		param.put("comment", commentDto);
		
		return commentDao.modifyComment(param);
	}

	@Override
	public int deleteComment(String boardType, int commentNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		if (boardType == null || boardType.isEmpty())
			return -1;

		param.put("boardType", boardType);
		param.put("commentNo", commentNo);
		
		return commentDao.deleteComment(param);
	}

}
