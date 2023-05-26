package com.ssafy.enjoytrip.notice.model.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.notice.model.dto.NoticeDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeTest {
	@Autowired
	DataSource ds;
	@Autowired
	NoticeDao noticeDao;

	@Test
	public void test() throws SQLException {
		assertNotNull(ds);
		assertNotNull(noticeDao);
	}

	@Test
	public void test2() throws SQLException {
		NoticeDto noticeDto = new NoticeDto();
		noticeDto.setContent("공지사항 테스트");
		noticeDto.setHit(0);
		noticeDto.setSubject("공지사항 테스트");
		noticeDto.setUserId("admin");

		noticeDao.writeArticle(noticeDto);
		noticeDao.getArticle(12);
		noticeDao.updateHit(1);

		noticeDto.setContent("안녕하세요!");

		noticeDao.modifyArticle(noticeDto);
		noticeDao.getArticle(1);
		noticeDao.deleteArticle(1);
	}
}
