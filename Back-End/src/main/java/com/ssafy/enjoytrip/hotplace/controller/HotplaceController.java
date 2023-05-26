package com.ssafy.enjoytrip.hotplace.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.hotplace.model.service.HotplaceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/hotplace")
@CrossOrigin("*")
@Api(tags = { "핫플레이스 관리" })
public class HotplaceController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(HotplaceController.class);

	private HotplaceService hotplaceService;

	@Autowired
	public HotplaceController(HotplaceService hotplaceService) {
		super();
		this.hotplaceService = hotplaceService;
	}

	@ApiOperation(value = "", notes = "planned를 +1 합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "플랜드 +1 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PostMapping(value = "")
	public ResponseEntity<?> plusPlanned(@RequestParam("params") String parser) throws Exception {
		logger.debug("Plus Planned Call");
		
		System.out.println("planned plus list : " + parser);
		
		int row = hotplaceService.plusPlanned(parser);
		
		if (row == 0)
			return new ResponseEntity<String>("PLUS PLANNED FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("PLUS PLANNED COMPLETE", HttpStatus.OK);
	}
	
	@ApiOperation(value = "", notes = "planned를 -1 합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "플랜드 -1 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PutMapping(value = "")
	public ResponseEntity<?> minusPlanned(@RequestParam List<Integer> list) throws Exception {
		logger.debug("Minus Planned Call");
		
		int row = hotplaceService.minusPlanned(list);
		
		if (row == 0)
			return new ResponseEntity<String>("MINUS PLANNED FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("MINUS PLANNED COMPLETE", HttpStatus.OK);
	}
	
	@ApiOperation(value = "", notes = "liked를 +1 합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "좋아요 +1 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PostMapping(value = "/{contentNo}")
	public ResponseEntity<?> plusLiked(@PathVariable int contentNo) throws Exception {
		logger.debug("Plus Liked Call");
		
		int row = hotplaceService.plusLiked(contentNo);
		
		if (row == 0)
			return new ResponseEntity<String>("PLUS LIKED FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("PLUS LIKED COMPLETE", HttpStatus.OK);
	}
	
	@ApiOperation(value = "", notes = "오늘의 여행지")
	@ApiResponses({ @ApiResponse(code = 200, message = "오늘의 여행지 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/today")
	public ResponseEntity<?> getTodaysAttr() throws Exception {
		logger.debug("Plus Liked Call");
		
		List<Map<String, Object>> result = hotplaceService.getTodaysAttr();
		
		if (result == null)
			return new ResponseEntity<String>("TODAYS ATTR FAILED", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<List<Map<String, Object>>>(result, HttpStatus.OK);
	}
}
