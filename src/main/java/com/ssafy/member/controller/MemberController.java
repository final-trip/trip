package com.ssafy.member.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin("*")
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@PutMapping("/modify")
	public ResponseEntity<String> modify(@ModelAttribute MemberDto memberDto) throws Exception {
//		File fileInfos = memberDto.getFiles();
		memberService.updateMember(memberDto);
//		log.debug("mmmmmmmmmmm"+memberDto.getFiles());
		memberService.registerfile(memberDto.getImgfile(), "memberfile", memberDto.getUserId());
//		String url = memberService.modifyimg(fileInfos, "memberfile", memberDto.getUserId());
		return ResponseEntity.ok("modified successfully");
	}

	@GetMapping("/checkid/{userid}")
	public ResponseEntity<Integer> idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
//		return cnt + "";
		return ResponseEntity.ok(cnt);

	}

	@GetMapping("/getinfo")
	public ResponseEntity<MemberDto> getMember(@RequestParam("userId") String userId) throws Exception {
		MemberDto memberDto = memberService.getMember(userId);
//		return cnt + "";
		return ResponseEntity.ok(memberDto);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteMember(@RequestParam("userId") String userId) throws Exception {

		logger.debug("idCheck userid : {}", userId);
		memberService.deleteMember(userId);
		return ResponseEntity.ok("member deleted ");
	}

	@PostMapping("/join")
	public ResponseEntity<String> registerMember(@RequestBody MemberDto memberDto) {
//	public String join(MemberDto memberDto, Model model) {
		logger.debug("memberDto info : {}", memberDto);

//		try {
//			memberService.joinMember(memberDto);
//			return "redirect:/user/login";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
//			return "error/error";
//		}

		try {
			memberService.joinMember(memberDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Member registered successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register member");
		}

	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map,
			@RequestParam(name = "saveid", required = false) String saveid, HttpSession session,
			HttpServletResponse response) {
		try {
			log.debug("idddd {}", map.toString());
			MemberDto memberDto = memberService.loginMember(map);
			log.debug("loggggg {}", memberDto.toString());

			if (memberDto != null) {
//				session.setAttribute("userinfo", memberDto);

				// 세션에 사용자 정보를 저장
				return ResponseEntity.ok("로그인 성공");
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 중 문제 발생!!!");
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
//		session.invalidate();
		return ResponseEntity.ok("Logged out successfully");
	}

	@GetMapping("/list")
	public ResponseEntity<List<MemberDto>> getMembers() throws Exception {
		List<MemberDto> members = memberService.listMember();

		if (members.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(members);
	}

}