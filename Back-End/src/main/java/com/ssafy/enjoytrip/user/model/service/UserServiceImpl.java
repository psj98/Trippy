package com.ssafy.enjoytrip.user.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.enjoytrip.user.model.dao.UserDao;
import com.ssafy.enjoytrip.user.model.dto.FileInfoDto;
import com.ssafy.enjoytrip.user.model.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	private UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public int idCheck(String id) throws Exception {
		return userDao.idCheck(id);
	}

	@Override
	public int joinUser(UserDto userDto) throws Exception {
		userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		
//		List<FileInfoDto> fileInfos = userDto.getFileInfos();
//		if (fileInfos != null && !fileInfos.isEmpty()) {
//			userDao.registerFile(userDto);
//		}
		return userDao.joinUser(userDto);
	}

	@Override
	public UserDto loginUser(String id, String password) throws Exception {
		UserDto userDto = userDao.loginUser(id);

		// 비번 체크
		if (userDto != null && BCrypt.checkpw(password, userDto.getPassword()))
			return userDto;
		else
			return null;
	}

	@Override
	public UserDto findUser(String id) throws Exception {
		return userDao.selectUser(id);
	}

	@Override
	public int updateUser(UserDto userDto) throws Exception {
		// 비밀번호 암호화
		userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		return userDao.updateUser(userDto);
	}

	@Override
	public int deleteUser(String id) throws Exception {
		return userDao.deleteUser(id);
	}
	
	@Override
	public int updatePw(UserDto userDto) throws Exception {
		// 비밀번호 암호화
		userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt()));
		return userDao.updatePw(userDto);
	}
	
	@Override
	public void saveRefreshToken(String id, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", refreshToken);
		userDao.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String id) throws Exception {
		return userDao.getRefreshToken(id);
	}

	@Override
	public void deleRefreshToken(String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("token", null);
		userDao.deleteRefreshToken(map);
	}
}
