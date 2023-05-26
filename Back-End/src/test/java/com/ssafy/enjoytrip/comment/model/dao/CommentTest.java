package com.ssafy.enjoytrip.comment.model.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.comment.model.dto.CommentDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTest {
	@Autowired
	DataSource ds;
	@Autowired
	CommentDao commentDao;

	@Test
	public void test() throws SQLException {
		assertNotNull(ds);
		assertNotNull(commentDao);
	}

	@Test
	public void test2() throws SQLException {
		CommentDto commentDto = new CommentDto();
//		commentDto.setArticleNo(23);
//		commentDto.setUserId("ssafy");
//		commentDto.setContent("안녕하세요");
//		
//		commentDao.writeComment(commentDto);
//		commentDao.listComment(23);
//		
//		commentDto.setContent("안녕못해요");
//		commentDao.modifyComment(commentDto);
//		
//		commentDto.setContent("안녕하세요!");
//		commentDao.writeComment(commentDto);
//		commentDao.listComment(23);
	}
	
	@Test
	public void test3() throws SQLException{
//		CommentDto commentDto = new CommentDto(0, 23, "ssafy", "뭐하시나요");
//		commentDao.writeComment(commentDto);
//		
//		commentDto.setUserId("nothing");
//		commentDto.setCommentNo(5);
//		commentDao.modifyComment(commentDto);
//		
//		commentDao.listComment(1);
//		
//		commentDao.deleteComment(123);
	}
	
}
