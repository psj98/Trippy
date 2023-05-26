package com.ssafy.enjoytrip.myplan.controller;

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
import com.ssafy.enjoytrip.myplan.model.dto.MyPlanDto;
import com.ssafy.enjoytrip.myplan.model.service.MyPlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
@CrossOrigin("*")
@Api(tags = { "여행 계획 관리" })
public class MyPlanController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);

	private MyPlanService myPlanService;

	@Autowired
	public MyPlanController(MyPlanService myPlanService) {
		super();
		this.myPlanService = myPlanService;
	}

	@ApiOperation(value = "목록보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "목록보기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/myplans")
	public ResponseEntity<?> list(@RequestParam("id") String id, @RequestParam("key") String key, @RequestParam("word") String word) throws Exception {
		logger.debug("MyPlan List Call");

		List<Map<String, String>> myPlans = myPlanService.listArticle(id, key, word);

		if (myPlans == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Map<String, String>>>(myPlans, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "상세보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "상세보기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/myplans/{articleNo}")
	public ResponseEntity<?> view(@PathVariable int articleNo) throws Exception {
		logger.debug("MyPlan View Call");

		Map<String, String> myPlan = myPlanService.getArticle(articleNo);
		myPlanService.updateHit(articleNo);

		if (myPlan == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Map<String, String>>(myPlan, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "글 쓰기")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 쓰기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PostMapping(value = "/myplans")
	public ResponseEntity<?> write(@RequestBody MyPlanDto myPlanDto) throws Exception {
		logger.debug("MyPlan Write Call");

		int row = myPlanService.writeArticle(myPlanDto);

		if (row == 0)
			return new ResponseEntity<String>("WRITE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("WRITE SUCCESS", HttpStatus.OK);
	}

	@ApiOperation(value = "글 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 수정 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PutMapping(value = "/myplans")
	public ResponseEntity<?> modify(@RequestBody MyPlanDto myPlanDto) throws Exception {
		logger.debug("MyPlan Modify Call");

		int row = myPlanService.modifyArticle(myPlanDto);

		if (row == 0)
			return new ResponseEntity<String>("MODIFY FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("MODIFY SUCCESS", HttpStatus.OK);
	}

	@ApiOperation(value = "글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 삭제 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@DeleteMapping(value = "/myplans/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable int articleNo) throws Exception {
		logger.debug("MyPlan Delete Call");

		int row = myPlanService.deleteArticle(articleNo);

		if (row == 0)
			return new ResponseEntity<String>("DELETE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("DELETE SUCCESS", HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 탈퇴 시, 해당 아이디의 모든 글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 삭제 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@DeleteMapping(value = "/myplans/deletearticle/{userId}")
	public ResponseEntity<?> deleteAllArticle(@PathVariable String userId) throws Exception {
		logger.debug("MyPlan DeleteAllArticle Call");

		int row = myPlanService.deleteAllArticle(userId);

		if (row == 0)
			return new ResponseEntity<String>("DELETE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("DELETE SUCCESS", HttpStatus.OK);
	}
	
	@ApiOperation(value = "공유 목록 보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "공유 목록 보기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/myplans/share")
	public ResponseEntity<?> shareArticle(@RequestParam("key") String key, @RequestParam("word") String word) throws Exception {
		logger.debug("MyPlan ShareArticle Call");

		List<Map<String, String>> myPlans = myPlanService.shareArticle(key, word);

		if (myPlans == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Map<String, String>>>(myPlans, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "게시글 공유")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시글 공유 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PutMapping(value = "/myplans/share/{articleNo}")
	public ResponseEntity<?> updateShare(@PathVariable int articleNo) throws Exception {
		logger.debug("MyPlan updateShare Call");

		int row = myPlanService.updateShare(articleNo);

		if (row == 0)
			return new ResponseEntity<String>("UPDATE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("UPDATE SUCCESS", HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시글 공유 해제")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시글 공유 해제 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PutMapping(value = "/myplans/noshare/{articleNo}")
	public ResponseEntity<?> updateNoShare(@PathVariable int articleNo) throws Exception {
		logger.debug("MyPlan updateNoShare Call");

		int row = myPlanService.updateNoShare(articleNo);

		if (row == 0)
			return new ResponseEntity<String>("UPDATE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("UPDATE SUCCESS", HttpStatus.OK);
	}
}
