package com.ssafy.enjoytrip.user.model.dao;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ssafy.enjoytrip.user.model.dto.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	@Autowired
	DataSource ds;
	@Autowired
	UserDao userDao;

	@Test
	public void test() throws SQLException {
		assertNotNull(ds);
		assertNotNull(userDao);
		userDao.deleteUser("ssafyabc");
	}

	@Test
	public void test2() throws SQLException {
//		UserDto userDto = new UserDto("ssafyabc", "1234", "abc", "abc", "naver.com", 1, 1, 1);

//		userDao.joinUser(userDto);
//		userDao.idCheck("ssafyabc");
//		userDao.selectUser("ssafyabc");
//		userDao.loginUser("ssafyabc");
//
//		userDto.setName("cba");
//
//		userDao.updateUser(userDto);
//		userDao.selectUser("ssafyabc");
//		userDao.deleteUser("ssafyabc");
	}
}
