package com.ssafy.enjoytrip.user.model.service;

import com.ssafy.enjoytrip.user.model.dto.UserDto;

public interface UserService {
	int idCheck(String id) throws Exception;

	int joinUser(UserDto userDto) throws Exception;

	UserDto loginUser(String id, String password) throws Exception;

	UserDto findUser(String id) throws Exception;

	int updateUser(UserDto userDto) throws Exception;

	int deleteUser(String id) throws Exception;
	
	int updatePw(UserDto userDto) throws Exception;
	
	void saveRefreshToken(String userid, String refreshToken) throws Exception;
	
	Object getRefreshToken(String userid) throws Exception;
	
	void deleRefreshToken(String userid) throws Exception;
}
