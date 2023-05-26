package com.ssafy.enjoytrip.user.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.enjoytrip.user.model.dto.UserDto;

@Mapper
public interface UserDao {
	int idCheck(String id) throws SQLException;

	int joinUser(UserDto userDto) throws SQLException;

	UserDto loginUser(String id) throws SQLException;

	UserDto selectUser(String id) throws SQLException;

	int updateUser(UserDto userDto) throws SQLException;

	int deleteUser(String id) throws SQLException;
	
	int updatePw(UserDto userDto) throws SQLException;
	
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	
	Object getRefreshToken(String userid) throws SQLException;
	
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
	int registerFile(UserDto userDto) throws Exception;
}
