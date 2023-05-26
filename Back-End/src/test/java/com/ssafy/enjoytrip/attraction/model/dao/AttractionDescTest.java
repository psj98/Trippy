package com.ssafy.enjoytrip.attraction.model.dao;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionDescDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttractionDescTest {
	@Autowired
	DataSource ds;
	@Autowired
	IAttractionDescDao attractionDescDao;

	@Test
	public void test() {
		assertNotNull(ds);
		assertNotNull(attractionDescDao);
	}

	/**
	 * 관광지 번호에 해당하는 관광지 설명 가져오기
	 */
	@Test
	public void testFindDesc() {
		AttractionDescDto attractionDesc = attractionDescDao.select(125405);

		assertNotNull(attractionDesc);
	}
}
