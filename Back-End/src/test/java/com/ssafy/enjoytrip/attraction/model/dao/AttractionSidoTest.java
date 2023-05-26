package com.ssafy.enjoytrip.attraction.model.dao;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionSidoDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttractionSidoTest {
	@Autowired
	DataSource ds;
	@Autowired
	IAttractionSidoDao attractionSidoDao;

	@Test
	public void test() {
		assertNotNull(ds);
		assertNotNull(attractionSidoDao);
	}

	/**
	 * 시도 code에 해당하는 시도 정보 가져오기
	 */
	@Test
	public void testFindSido() {
		AttractionSidoDto attractionSido = attractionSidoDao.selectSido(1);

		assertNotNull(attractionSido);
	}
}
