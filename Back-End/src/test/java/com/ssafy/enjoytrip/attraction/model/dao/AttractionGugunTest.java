package com.ssafy.enjoytrip.attraction.model.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionGugunDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttractionGugunTest {
	@Autowired
	DataSource ds;

	@Autowired
	IAttractionGugunDao attractionGugunDao;

	@Test
	public void test() {
		assertNotNull(ds);
		assertNotNull(attractionGugunDao);
	}

	/**
	 * 시도에 따른 구군 목록 가져오기
	 */
	@Test
	public void testFindGugunList() {
		List<AttractionGugunDto> attractionGuguns = attractionGugunDao.selectGugun(1);

		assertNotNull(attractionGuguns);
	}
}
