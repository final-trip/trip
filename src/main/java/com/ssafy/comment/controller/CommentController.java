package com.ssafy.comment.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.model.ArticleRequest;
import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.comment.model.CommentDto;
import com.ssafy.comment.model.service.CommentService;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.PageNavigation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
@Slf4j
public class CommentController {

	private final Logger logger = LoggerFactory.getLogger(CommentController.class);
//	private final String UPLOAD_PATH = "/upload";

//	@Autowired
//	private ServletContext servletContext;

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@GetMapping("/allcomments")
	public ResponseEntity<List<CommentDto>> allcomments(@RequestParam("articleNo") int articleNo,
			@RequestParam Map<String, String> map) throws Exception {
		List<CommentDto> commentDto = commentService.listComments(articleNo);
		return ResponseEntity.ok(commentDto);
	}

	@GetMapping("/view")
	public ResponseEntity<CommentDto> view(@RequestParam("idcomments") int idcomments,
			@RequestParam Map<String, String> map) throws Exception {
		CommentDto commentDto = commentService.getcomment(idcomments);
		return ResponseEntity.ok(commentDto);
	}

	@PostMapping("/writeComment")
	public ResponseEntity<String> writeComment(@RequestBody CommentDto commentDto) throws Exception {
		commentService.writeComment(commentDto);
		return ResponseEntity.ok("write");
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam("idcomments") int idcomments) throws Exception {
		commentService.deletecomment(idcomments);
		return ResponseEntity.ok("deleted");
	}
}