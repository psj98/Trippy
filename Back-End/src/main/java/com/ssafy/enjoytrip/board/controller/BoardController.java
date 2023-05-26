package com.ssafy.enjoytrip.board.controller;

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
import com.ssafy.enjoytrip.board.model.dto.BoardDto;
import com.ssafy.enjoytrip.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/articles/{boardType}")
@CrossOrigin("*")
@Api(tags = { "게시판 관리" })
public class BoardController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);

	private BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}
	
	@ApiOperation(value = "게시판 이름 받기")
	@ApiResponses({ @ApiResponse(code = 200, message = "게시판 이름 받기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/name")
	public ResponseEntity<?> name(@PathVariable("boardType") String boardType) throws Exception {
		logger.debug("Board List Call");

//		Map<String, String> map = new HashMap<String, String>();
//		
//		map.put("key", key);
//		map.put("word", word);
		
		Map<String, String> boardInfo = boardService.getBoardName(boardType);

		if (boardInfo == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Map<String, String>>(boardInfo, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "목록보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "목록보기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "")
	public ResponseEntity<?> list(@PathVariable("boardType") String boardType, @RequestParam("key") String key, @RequestParam("word") String word) throws Exception {
		logger.debug("Board List Call");

		System.out.println(key);
//		Map<String, String> map = new HashMap<String, String>();
//		
//		map.put("key", key);
//		map.put("word", word);
		
		List<Map<String, String>> articles = boardService.listArticle(boardType, key, word);

		if (articles == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Map<String, String>>>(articles, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "상세보기")
	@ApiResponses({ @ApiResponse(code = 200, message = "상세보기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@GetMapping(value = "/{articleNo}")
	public ResponseEntity<?> view(@PathVariable("boardType") String boardType, @PathVariable int articleNo) throws Exception {
		logger.debug("Board View Call");

		boardService.updateHit(boardType, articleNo);
		Map<String, String> article = boardService.getArticle(boardType, articleNo);

		if (article == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Map<String, String>>(article, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "글 쓰기")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 쓰기 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PostMapping(value = "")
	public ResponseEntity<?> write(@PathVariable("boardType") String boardType, @RequestBody BoardDto boardDto) throws Exception {
		logger.debug("Board Write Call");

		int row = boardService.writeArticle(boardType, boardDto);

		if (row == 0)
			return new ResponseEntity<String>("WRITE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("WRITE SUCCESS", HttpStatus.OK);
	}

	@ApiOperation(value = "글 수정")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 수정 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@PutMapping(value = "")
	public ResponseEntity<?> modify(@PathVariable("boardType") String boardType, @RequestBody BoardDto boardDto) throws Exception {
		logger.debug("Board Modify Call");
		System.out.println(boardDto);

		int row = boardService.modifyArticle(boardType, boardDto);

		if (row == 0)
			return new ResponseEntity<String>("MODIFY FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("MODIFY SUCCESS", HttpStatus.OK);
	}

	@ApiOperation(value = "글 삭제")
	@ApiResponses({ @ApiResponse(code = 200, message = "글 삭제 OK"), @ApiResponse(code = 500, message = "서버 에러") })
	@DeleteMapping(value = "/{articleNo}")
	public ResponseEntity<?> delete(@PathVariable("boardType") String boardType, @PathVariable int articleNo) throws Exception {
		logger.debug("Board Delete Call");

		int row = boardService.deleteArticle(boardType, articleNo);

		if (row == 0)
			return new ResponseEntity<String>("DELETE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("DELETE SUCCESS", HttpStatus.OK);
	}
	
}
