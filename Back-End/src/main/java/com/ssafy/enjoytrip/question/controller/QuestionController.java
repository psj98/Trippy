package com.ssafy.enjoytrip.question.controller;

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
import com.ssafy.enjoytrip.question.model.dto.QuestionDto;
import com.ssafy.enjoytrip.question.model.service.QuestionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
@CrossOrigin("*")
@Api(tags = { "문의 게시판 관리" })
public class QuestionController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);

	private QuestionService questionService;

	@Autowired
	public QuestionController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}

	@ApiOperation(value = "목록보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "목록보기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/questions")
	public ResponseEntity<?> list(@RequestParam Map<String, String> map) throws Exception {
		logger.debug("Question List Call");

		List<Map<String, String>> questions = questionService.listArticle(map);

		if (questions == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Map<String, String>>>(questions, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "상세보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "상세보기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/questions/{articleNo}")
	public ResponseEntity<?> view(@PathVariable int articleNo) throws Exception {
		logger.debug("Question View Call");

		Map<String, String> question = questionService.getArticle(articleNo);
		questionService.updateHit(articleNo);

		if (question == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Map<String, String>>(question, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "글 쓰기")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 쓰기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PostMapping(value = "/questions")
	public ResponseEntity<?> write(@RequestBody QuestionDto questionDto) throws Exception {
		logger.debug("Question Write Call");

		int row = questionService.writeArticle(questionDto);

		if (row == 0)
			return new ResponseEntity<String>("WRITE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("WRITE SUCCESS", HttpStatus.OK);
	}

	@ApiOperation(value = "글 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 수정 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PutMapping(value = "/questions")
	public ResponseEntity<?> modify(@RequestBody QuestionDto questionDto) throws Exception {
		logger.debug("Question Modify Call");

		int row = questionService.modifyArticle(questionDto);

		if (row == 0)
			return new ResponseEntity<String>("MODIFY FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("MODIFY SUCCESS", HttpStatus.OK);
	}

	@ApiOperation(value = "글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 삭제 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@DeleteMapping(value = "/questions/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable int articleNo) throws Exception {
		logger.debug("Question Delete Call");

		int row = questionService.deleteArticle(articleNo);

		if (row == 0)
			return new ResponseEntity<String>("DELETE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("DELETE SUCCESS", HttpStatus.OK);
	}
}
