package com.ssafy.enjoytrip.attraction.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.model.dto.AttractionGugunDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionInfoDto;
import com.ssafy.enjoytrip.attraction.model.dto.AttractionSidoDto;
import com.ssafy.enjoytrip.attraction.model.service.IAttractionGugunService;
import com.ssafy.enjoytrip.attraction.model.service.IAttractionInfoService;
import com.ssafy.enjoytrip.attraction.model.service.IAttractionSidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("")
@CrossOrigin("*")
@Api(tags = { "관광지 관리" })
public class AttractionController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);

	private IAttractionInfoService attractionInfoService;
	private IAttractionSidoService attractionSidoService;
	private IAttractionGugunService attractionGugunService;

	@Autowired
	public AttractionController(IAttractionInfoService attractionInfoService,
			IAttractionSidoService attractionSidoService, IAttractionGugunService attractionGugunService) {
		super();
		this.attractionInfoService = attractionInfoService;
		this.attractionSidoService = attractionSidoService;
		this.attractionGugunService = attractionGugunService;
	}

	/**
	 * 관광지 검색
	 * 
	 * Method : GET
	 * 
	 * @param sidoCode
	 * @param gugunCode
	 * @param contentTypeId
	 * @param title
	 * @return
	 */
	@ApiOperation(value = "관광지 검색", notes = "관광지 목록을 리턴합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "관광지 목록 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/attractions")
	public ResponseEntity<?> attractionSearch(@RequestParam("sido") int sidoCode, @RequestParam("gugun") int gugunCode,
			@RequestParam("contentType") int contentTypeId, @RequestParam("searchTitle") String title) {
		logger.debug("Attraction Search Call");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sidoCode", sidoCode);
		map.put("gugunCode", gugunCode);
		map.put("contentTypeId", contentTypeId);
		map.put("title", title);
		
		System.out.println(sidoCode + " " + gugunCode);

		List<AttractionInfoDto> attractionInfos = attractionInfoService.findAttractionList(map);

		if (attractionInfos == null)
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		else {
			for (AttractionInfoDto attractionInfo : attractionInfos) {
				if (attractionInfo.getContentTypeId() == 12) {
					attractionInfo.setContentTypeName("관광지");
				} else if (attractionInfo.getContentTypeId() == 14) {
					attractionInfo.setContentTypeName("문화시설");
				} else if (attractionInfo.getContentTypeId() == 15) {
					attractionInfo.setContentTypeName("축제공연행사");
				} else if (attractionInfo.getContentTypeId() == 25) {
					attractionInfo.setContentTypeName("여행코스");
				} else if (attractionInfo.getContentTypeId() == 28) {
					attractionInfo.setContentTypeName("레포츠");
				} else if (attractionInfo.getContentTypeId() == 32) {
					attractionInfo.setContentTypeName("숙박");
				} else if (attractionInfo.getContentTypeId() == 38) {
					attractionInfo.setContentTypeName("쇼핑");
				} else {
					attractionInfo.setContentTypeName("음식점");
				}
			}

			return new ResponseEntity<List<AttractionInfoDto>>(attractionInfos, HttpStatus.OK);
		}
	}

	/**
	 * 관광지 상세 정보
	 * 
	 * Method : GET
	 * 
	 * @param contentId
	 * @return
	 */
	@ApiOperation(value = "관광지 상세 정보", notes = "관광지 상세 정보를 리턴합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "관광지 상세 정보 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/attractions/{contentId}")
	public ResponseEntity<?> attractionDetail(@PathVariable("contentId") int contentId) {
		logger.debug("Attraction Detail Call");

		AttractionInfoDto attractionInfo = attractionInfoService.findAttraction(contentId);

		if (attractionInfo.getContentTypeId() == 12) {
			attractionInfo.setContentTypeName("관광지");
		} else if (attractionInfo.getContentTypeId() == 14) {
			attractionInfo.setContentTypeName("문화시설");
		} else if (attractionInfo.getContentTypeId() == 15) {
			attractionInfo.setContentTypeName("축제공연행사");
		} else if (attractionInfo.getContentTypeId() == 25) {
			attractionInfo.setContentTypeName("여행코스");
		} else if (attractionInfo.getContentTypeId() == 28) {
			attractionInfo.setContentTypeName("레포츠");
		} else if (attractionInfo.getContentTypeId() == 32) {
			attractionInfo.setContentTypeName("숙박");
		} else if (attractionInfo.getContentTypeId() == 38) {
			attractionInfo.setContentTypeName("쇼핑");
		} else {
			attractionInfo.setContentTypeName("음식점");
		}

		return new ResponseEntity<AttractionInfoDto>(attractionInfo, HttpStatus.OK);
	}

	/**
	 * 관광지 시도 정보
	 * 
	 * Method : GET
	 * 
	 * @return
	 */
	@ApiOperation(value = "관광지 시도 정보", notes = "관광지 시도 정보를 리턴합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "관광지 시도 정보 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/attractions/sido")
	public ResponseEntity<?> attractionSido() {
		logger.debug("Attraction Sido Call");
		return new ResponseEntity<List<AttractionSidoDto>>(attractionSidoService.selectAll(), HttpStatus.OK);
	}

	/**
	 * 관광지 구군 정보
	 * 
	 * Method : GET
	 * 
	 * @param sido
	 * @return
	 */
	@ApiOperation(value = "관광지 구군 정보", notes = "관광지 구군 정보를 리턴합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "관광지 구군 정보 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/attractions/gugun")
	public ResponseEntity<?> attractionGugun(@RequestParam("sido") int sido) {
		logger.debug("Attraction Gugun Call");
		return new ResponseEntity<List<AttractionGugunDto>>(attractionGugunService.findGugunList(sido), HttpStatus.OK);
	}
}
