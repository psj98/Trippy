package com.ssafy.enjoytrip.comment.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.enjoytrip.attraction.controller.AttractionController;
import com.ssafy.enjoytrip.comment.model.dto.CommentDto;
import com.ssafy.enjoytrip.comment.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/comments/{boardType}")
@CrossOrigin("*")
@Api(tags = { "댓글 관리" })
public class CommentController extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(AttractionController.class);

	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@ApiOperation(value = "댓글 목록")
	@ApiResponses({@ApiResponse(code = 200, message = "목록보기 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@GetMapping(value = "/{articleNo}")
	public ResponseEntity<?> list(@PathVariable String boardType, @PathVariable int articleNo) throws Exception{
		logger.debug("Comment List Call");
		
		List<CommentDto> comments = commentService.listComment(boardType, articleNo);
		
		if(comments == null) {
			return new ResponseEntity<String>("NO DATA", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "댓글 쓰기")
	@ApiResponses({@ApiResponse(code = 200, message = "목록보기 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@PostMapping(value = "")
	public ResponseEntity<?> write(@PathVariable String boardType, @RequestBody CommentDto commentDto) throws Exception{
		logger.debug("Comment Write Call");
		
		int row = commentService.writeComment(boardType, commentDto);
		
		if (row == 0)
			return new ResponseEntity<String>("WRITE FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("WRITE SUCCESS", HttpStatus.OK);
	}
	@ApiOperation(value = "댓글 수정")
	@ApiResponses({@ApiResponse(code = 200, message = "목록보기 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@PutMapping(value = "")
	public ResponseEntity<?> modify(@PathVariable String boardType, @RequestBody CommentDto commentDto) throws Exception{
		logger.debug("Comment Modify Call");
		
		int row = commentService.modifyComment(boardType, commentDto);
		
		if (row == 0)
			return new ResponseEntity<String>("MODIFY FAIL", HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<String>("MODIFY SUCCESS", HttpStatus.OK);
	}
	@ApiOperation(value = "댓글 삭제")
	@ApiResponses({@ApiResponse(code = 200, message = "목록보기 OK"), @ApiResponse(code = 500, message = "서버 에러")})
	@DeleteMapping(value = "/{commentNo}")
	public ResponseEntity<?> delete(@PathVariable String boardType, @PathVariable int commentNo) throws Exception{
		logger.debug("Comment Delete Call");
		
		int row = commentService.deleteComment(boardType, commentNo);
		
		if(row == 0) {
			return new ResponseEntity<String>("DELETE FAIL", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<String>("DELETE SUCCESS", HttpStatus.OK);
		}
	}
	
	
}
