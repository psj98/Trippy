package com.ssafy.enjoytrip.attraction.model.dao;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttractionInfoTest {
	@Autowired
	DataSource ds;
	@Autowired
	IAttractionInfoDao attractionInfoDao;

	@Test
	public void test() {
		assertNotNull(ds);
		assertNotNull(attractionInfoDao);
	}

	/**
	 * 조건에 맞는 관광지 리스트 가져오기 (시도, 구군, 관광지 유형, 검색어 포함)
	 */
	@Test
	public void testFindAllInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidoCode", 1);
		map.put("gugunCode", 15);
		map.put("contentTypeId", 12);
		map.put("title", "서");

		List<AttractionInfoDto> attractionInfos = attractionInfoDao.selectAll(map);

		assertNotNull(attractionInfos);
	}

	/**
	 * 관광지 번호에 해당하는 관광지 세부 정보 가져오기
	 */
	@Test
	public void testFindDetail() {
		AttractionInfoDto attractionInfo = attractionInfoDao.selectId(125405);

		assertNotNull(attractionInfo);
	}
}
