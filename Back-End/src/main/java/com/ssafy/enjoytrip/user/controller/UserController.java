package com.ssafy.enjoytrip.user.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.enjoytrip.user.model.dto.FileInfoDto;
import com.ssafy.enjoytrip.user.model.dto.UserDto;
import com.ssafy.enjoytrip.user.model.service.JwtServiceImpl;
import com.ssafy.enjoytrip.user.model.service.UserService;
//import com.ssafy.vue.model.MemberDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
@CrossOrigin("*")
@Api(tags = { "사용자 관리" })
public class UserController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private UserService userService;
	
	@Value("${file.path}")
	public String uploadPath;
	
	@Value("${file.imgPath}")
	public String uploadImgPath;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	/**
	 * 아이디 중복 체크
	 * 
	 * @param id
	 * @return row
	 * @throws Exception
	 */
	@ApiOperation(value = "아이디 중복 체크", notes = "아이디 중복 체크를 합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "아이디 중복 체크 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/users/idcheck/{id}")
	public ResponseEntity<?> idCheck(@PathVariable("id") String id) throws Exception {
		logger.debug("User IdCheck Call");

		int row = userService.idCheck(id);

		return new ResponseEntity<Integer>(row, HttpStatus.OK);
	}

	/**
	 * 회원 가입
	 * 
	 * Method : POST
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "사용자 회원가입", notes = "사용자가 회원가입 합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원가입 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PostMapping(value = "/users")
	public ResponseEntity<?> join(@RequestBody UserDto userDto) throws Exception {
		logger.debug("User Join Call");
//		System.out.println("user:"+userDto);
		
		//FileUpload
//		if (!files[0].isEmpty()) {
//			String today = new SimpleDateFormat("yyMMdd").format(new Date());
//			String saveFolder = uploadPath + File.separator + today;
//			logger.debug("저장 폴더 : {}", saveFolder);
//			File folder = new File(saveFolder);
//			if (!folder.exists())
//				folder.mkdirs();
//			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
//			for (MultipartFile mfile : files) {
//				FileInfoDto fileInfoDto = new FileInfoDto();
//				String originalFileName = mfile.getOriginalFilename();
//				if (!originalFileName.isEmpty()) {
//					String saveFileName = UUID.randomUUID().toString()
//							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
//					fileInfoDto.setSaveFolder(today);
//					fileInfoDto.setOriginalFile(originalFileName);
//					fileInfoDto.setSaveFile(saveFileName);
//					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
//					mfile.transferTo(new File(folder, saveFileName));
//				}
//				fileInfos.add(fileInfoDto);
//			}
//			userDto.setFileInfos(fileInfos);
//		}

		int row = userService.joinUser(userDto);

		if (row == 0)
			return new ResponseEntity<String>("NOT JOIN", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("JOIN SUCCESS", HttpStatus.OK);
	}

	/**
	 * 로그인
	 * 
	 * Method : POST
	 * 
	 * @param id
	 * @param password
	 * @param idSave
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "사용자 로그인", notes = "사용자가 로그인 합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "로그인 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PostMapping(value = "/users/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("User Login Call");

		System.out.println(userDto);
		
		Map<String, Object> resultMap = new HashMap<>();
		
		UserDto user = userService.loginUser(userDto.getId(), userDto.getPassword());
		
//		System.out.println("controller:"+user.getId());
		
		if (user == null)
			return new ResponseEntity<String>("LOGIN FAIL", HttpStatus.NO_CONTENT);
		else {
			String accessToken = jwtService.createAccessToken("id", user.getId());
			String refreshToken = jwtService.createRefreshToken("id", user.getId());
			userService.saveRefreshToken(user.getId(), refreshToken);
			logger.debug("로그인 accessToken 정보 : {}", accessToken);
			logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
			resultMap.put("access-token", accessToken);
			resultMap.put("refresh-token", refreshToken);
			resultMap.put("message", "LOGIN SUCCESS");
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
		}
		
		
		/* HttpSession */
//		session.setAttribute("userinfo", user);

		/* Cookie */
//		if (idSave != null) { // 아이디 저장 체크한 경우
//			Cookie cookie = new Cookie("savedId", id);
//			cookie.setPath(request.getContextPath());
//			cookie.setMaxAge(60 * 60 * 24); // 하루 저장
//			response.addCookie(cookie);
//		} else { // 아이디 저장을 해제한 경우
//			Cookie cookies[] = request.getCookies();
//			if (cookies != null) {
//				for (Cookie cookie : cookies) {
//					if ("savedId".equals(cookie.getName())) {
//						cookie.setMaxAge(0);
//						response.addCookie(cookie);
//						break;
//					}
//				}
//			}
//		}
	}

	/**
	 * 로그아웃
	 * 
	 * Method : GET
	 * 
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "사용자 로그아웃", notes = "사용자가 로그아웃 합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "로그아웃 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/users/logout/{id}")
	public ResponseEntity<?> logout(@PathVariable("id") String id) {
		logger.debug("User Logout Call");

		try {
			userService.deleRefreshToken(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("LOGOUT FAIL", HttpStatus.NO_CONTENT);
		}
		System.out.println("LOGOUT SUCCESS");
		return new ResponseEntity<String>("LOGOUT SUCCESS", HttpStatus.OK);
		
//		if (session.getAttribute("userinfo") == null)
//			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
//
//		/* HttpSession Invalidate */
//		session.invalidate();

	}

	/**
	 * 마이페이지
	 * 
	 * Method : GET
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "마이페이지", notes = "마이페이지에 정보를 출력합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "마이페이지 정보 출력 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/users/{id}")
	public ResponseEntity<?> mypage(@PathVariable("id") String id) throws Exception {
		logger.debug("User Mypage Call");

		
		System.out.println(id);
		UserDto user = userService.findUser(id);

		logger.debug(user.toString());
		
		

		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}

	/**
	 * 회원 정보 수정
	 * 
	 * Method : PUT
	 * 
	 * @param id
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원 정보 수정 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PutMapping(value = "/users/{id}")
	public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody UserDto userDto) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		logger.debug("User Update Call");

		userDto.setId(id);

		int row = userService.updateUser(userDto); // logout 생각
		
		String accessToken = jwtService.createAccessToken("id", userDto.getId());
		String refreshToken = jwtService.createRefreshToken("id", userDto.getId());
		userService.saveRefreshToken(userDto.getId(), refreshToken);
		logger.debug("업데이트 accessToken 정보 : {}", accessToken);
		logger.debug("업데이트 refreshToken 정보 : {}", refreshToken);
		resultMap.put("access-token", accessToken);
		resultMap.put("refresh-token", refreshToken);
		resultMap.put("message", "UPDATE SUCCESS");

		if (row == 0)
			return new ResponseEntity<String>("NOT UPDATE", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	/**
	 * 회원 탈퇴
	 * 
	 * Method : DELETE
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "회원 탈퇴", notes = "회원 정보를 삭제합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "회원 탈퇴 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) throws Exception {
		logger.debug("User Delete Call");

		int row = userService.deleteUser(id); // vue에서 logout으로 가야함

		if (row == 0)
			return new ResponseEntity<String>("NOT DELETE", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("DELETE SUCCESS", HttpStatus.OK);
	}
	
	/**
	 * 비밀번호 변경
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "비밀번호 변경", notes = "비밀번호를 변경합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "비밀번호 변경 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PutMapping(value = "/users/changepw")
	public ResponseEntity<?> updatePw(@RequestBody UserDto userDto) throws Exception {
		logger.debug("User UpdatePw Call");

		Map<String, Object> resultMap = new HashMap<>();

		int row = userService.updatePw(userDto);

		if (row == 0)
			return new ResponseEntity<String>("NOT UPDATE", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, userDto);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getId()))) {
				String accessToken = jwtService.createAccessToken("userid", userDto.getId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", "SUCCESS");
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
