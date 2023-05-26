package com.ssafy.enjoytrip.board.model.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.board.model.dto.BoardDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardTest {
	@Autowired
	DataSource ds;
	@Autowired
	BoardDao boardDao;

	@Test
	public void test() throws SQLException {
		assertNotNull(ds);
		assertNotNull(boardDao);
	}

	@Test
	public void test2() throws SQLException {
		BoardDto boardDto = new BoardDto();
		boardDto.setArticleNo(1);
		boardDto.setContent("안녕하세요");
		boardDto.setHit(0);
		boardDto.setSubject("인사");
		boardDto.setUserId("ssafy");
		
//		boardDao.writeArticle(boardDto);
//		boardDao.getArticle(1);
//		boardDao.updateHit(1);
		
		boardDto.setContent("안녕하세요!");
		
//		boardDao.modifyArticle(boardDto);
//		boardDao.getArticle(1);
//		boardDao.deleteArticle(1);
	}
}
