package com.ssafy.board.controller;

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
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.PageNavigation;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/article")
@CrossOrigin("*")
@Slf4j
public class BoardController {

	private final Logger logger = LoggerFactory.getLogger(BoardController.class);
//	private final String UPLOAD_PATH = "/upload";

	@Value("${file.path}")
	private String uploadPath;

	@Value("${file.path.upload-images}")
	private String uploadImagePath;

	@Value("${file.path.upload-files}")
	private String uploadFilePath;

//	@Autowired
//	private ServletContext servletContext;

	private BoardService boardService;

	public BoardController(BoardService boardService) {
		super();
		this.boardService = boardService;
	}

	@PostMapping("/likes")
	public ResponseEntity<Integer> toggleLike(@RequestBody ArticleRequest articleRequest) throws Exception {
		boardService.toggleLike(articleRequest.getArticleNo());
		int articleno = articleRequest.getArticleNo();
		int likenum = boardService.getArticle(articleno).getLikes();

		return ResponseEntity.ok(likenum);
	}

	@PostMapping("/write")
//	public ResponseEntity<String> write(BoardDto boardDto, @RequestParam("upfile") MultipartFile[] files,	HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
	public ResponseEntity<String> write(@ModelAttribute BoardDto boardDto) throws Exception {
		logger.debug("write boardDto : {}", boardDto);

		boardService.writeArticle(boardDto);

		log.debug("writeArticleeeeeeeeeeeeeeeeeee");
//		File fileInfos = boardDto.getFiles();

		log.debug("writeArticle afterrrrrrrrrrrrrr");
//		log.debug("writeArticle afterrrrrrrrrrrrrr" + fileInfos.getPath());

		String url = boardService.registerfile(boardDto.getFileInfos(), "mountainfile", boardDto.getArticleNo());
//		redirectAttributes.addAttribute("pgno", "1");
//		redirectAttributes.addAttribute("key", "");
//		redirectAttributes.addAttribute("word", "");

		return ResponseEntity.ok(url);

	}

	@GetMapping("/list")
	public ResponseEntity<List<BoardDto>> list() throws Exception {
//		logger.debug("list parameter pgno : {}", map.get("pgno"));
//		List<BoardDto> list = boardService.listArticle(map);
		List<BoardDto> list = boardService.listArticle();
//		PageNavigation pageNavigation = boardService.makePageNavigation(map);

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/getboardfiles")
	public ResponseEntity<List<FileInfoDto>> getfilelist(@RequestParam("articleNo") int articleNo) throws Exception {
//		logger.debug("list parameter pgno : {}", map.get("pgno"));
//		List<BoardDto> list = boardService.listArticle(map);

		List<FileInfoDto> list = boardService.getfilelist(articleNo);
//		PageNavigation pageNavigation = boardService.makePageNavigation(map);

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/alllist")
	public ResponseEntity<List<BoardDto>> alllist() throws Exception {
//		logger.debug("list parameter pgno : {}", map.get("pgno"));
//		List<BoardDto> list = boardService.listArticle(map);
		List<BoardDto> list = boardService.alllistArticle();
//		PageNavigation pageNavigation = boardService.makePageNavigation(map);

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/view")
	public ResponseEntity<BoardDto> view(@RequestParam("articleno") int articleNo,
			@RequestParam Map<String, String> map, Model model) throws Exception {
		logger.debug("view articleNo : {}", articleNo);
		BoardDto boardDto = boardService.getArticle(articleNo);
//		boardService.updateHit(articleNo);

		return ResponseEntity.ok(boardDto);
	}

	@PutMapping("/modify")
//	public String modify(BoardDto boardDto, @RequestParam Map<String, String> map,RedirectAttributes redirectAttributes) throws Exception {
	public ResponseEntity<String> modify(@RequestBody BoardDto boardDto) throws Exception {
		logger.debug("modify boardDto : {}", boardDto);
		System.out.println("before" + boardDto.toString());
		boardService.modifyArticle(boardDto);
		System.out.println("after" + boardDto.toString());
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key"));
//		redirectAttributes.addAttribute("word", map.get("word"));
		return ResponseEntity.ok("successfuly modified");
	}

	@DeleteMapping("/delete/{articleno}")
	public ResponseEntity<String> delete(@PathVariable("articleno") int articleNo) throws Exception {
		logger.debug("delete articleNo : {}", articleNo);
//		boardService.deleteArticle(articleNo, servletContext.getRealPath(UPLOAD_PATH));
		boardService.deleteArticle(articleNo);
//		redirectAttributes.addAttribute("pgno", map.get("pgno"));
//		redirectAttributes.addAttribute("key", map.get("key")); 
//		redirectAttributes.addAttribute("word", map.get("word"));
		return ResponseEntity.ok("successfully deleted");
	}

	@GetMapping("/download")
	public ModelAndView downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
			@RequestParam("sfile") String sfile) {
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("sfolder", sfolder);
		fileInfo.put("ofile", ofile);
		fileInfo.put("sfile", sfile);
		return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
	}

}