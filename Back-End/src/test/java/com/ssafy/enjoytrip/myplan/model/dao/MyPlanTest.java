package com.ssafy.enjoytrip.myplan.model.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.myplan.model.dto.MyPlanDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyPlanTest {
	@Autowired
	DataSource ds;
	@Autowired
	MyPlanDao myPlanDao;

	@Test
	public void test() throws SQLException {
		assertNotNull(ds);
		assertNotNull(myPlanDao);
	}

	@Test
	public void test2() throws SQLException {
		MyPlanDto myPlanDto = new MyPlanDto();
		myPlanDto.setContent("여행 계획 테스트");
		myPlanDto.setHit(0);
		myPlanDto.setSubject("여행 계획  테스트");
		myPlanDto.setUserId("ssafy");

		myPlanDao.writeArticle(myPlanDto);
		myPlanDao.getArticle(1);
		myPlanDao.updateHit(1);

		myPlanDto.setContent("안녕하세요!");

		myPlanDao.modifyArticle(myPlanDto);
		myPlanDao.getArticle(1);
		myPlanDao.deleteArticle(1);
	}
}
