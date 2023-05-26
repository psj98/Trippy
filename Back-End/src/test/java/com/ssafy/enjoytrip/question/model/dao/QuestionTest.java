package com.ssafy.enjoytrip.question.model.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.question.model.dto.QuestionDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionTest {
	@Autowired
	DataSource ds;
	@Autowired
	QuestionDao questionDao;

	@Test
	public void test() throws SQLException {
		assertNotNull(ds);
		assertNotNull(questionDao);
	}

	@Test
	public void test2() throws SQLException {
		QuestionDto questionDto = new QuestionDto();
		questionDto.setContent("문의 게시판 테스트");
		questionDto.setHit(0);
		questionDto.setSubject("문의 게시판 테스트");
		questionDto.setUserId("ssafy");

		questionDao.writeArticle(questionDto);
		questionDao.getArticle(12);
		questionDao.updateHit(1);

		questionDto.setContent("안녕하세요!");

		questionDao.modifyArticle(questionDto);
		questionDao.getArticle(1);
		questionDao.deleteArticle(1);
	}
}
