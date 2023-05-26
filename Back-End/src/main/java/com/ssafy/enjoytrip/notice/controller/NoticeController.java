package com.ssafy.enjoytrip.notice.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.controller.AttractionController;
import com.ssafy.enjoytrip.notice.model.dto.NoticeDto;
import com.ssafy.enjoytrip.notice.model.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
@CrossOrigin("*")
@Api(tags = { "공지사항 관리" })
public class NoticeController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);

	private NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}

	@ApiOperation(value = "목록보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "목록보기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/notices")
	public ResponseEntity<?> list(@RequestParam Map<String, String> map) throws Exception {
		logger.debug("Notice List Call");

		List<Map<String, String>> notices = noticeService.listArticle(map);

		if (notices == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Map<String, String>>>(notices, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "상세보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "상세보기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/notices/{articleNo}")
	public ResponseEntity<?> view(@PathVariable int articleNo) throws Exception {
		logger.debug("Notice View Call");

		Map<String, String> notice = noticeService.getArticle(articleNo);
		noticeService.updateHit(articleNo);

		if (notice == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Map<String, String>>(notice, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "글 쓰기")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 쓰기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PostMapping(value = "/notices")
	public ResponseEntity<?> write(@RequestBody NoticeDto noticeDto) throws Exception {
		logger.debug("Notice Write Call");

		int row = noticeService.writeArticle(noticeDto);

		if (row == 0)
			return new ResponseEntity<String>("WRITE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("WRITE SUCCESS", HttpStatus.OK);
	}

	@ApiOperation(value = "글 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 수정 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PutMapping(value = "/notices")
	public ResponseEntity<?> modify(@RequestBody NoticeDto noticeDto) throws Exception {
		logger.debug("Notice Modify Call");

		int row = noticeService.modifyArticle(noticeDto);

		if (row == 0)
			return new ResponseEntity<String>("MODIFY FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("MODIFY SUCCESS", HttpStatus.OK);
	}

	@ApiOperation(value = "글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 삭제 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@DeleteMapping(value = "/notices/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable int articleNo) throws Exception {
		logger.debug("Notice Delete Call");

		int row = noticeService.deleteArticle(articleNo);

		if (row == 0)
			return new ResponseEntity<String>("DELETE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("DELETE SUCCESS", HttpStatus.OK);
	}
}
