package com.ssafy.member.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;

@RestController
@RequestMapping("/user")
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	@GetMapping("/{userid}")
	public String idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		return cnt + "";
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
	public String login(@RequestParam Map<String, String> map,
			@RequestParam(name = "saveid", required = false) String saveid, Model model, HttpSession session,
			HttpServletResponse response) {
		System.out.println("login gogogogo");
		logger.debug("login map : {}", map);

		try {
			MemberDto memberDto = memberService.loginMember(map);
			if (memberDto != null) {
				session.setAttribute("userinfo", memberDto);

				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
				cookie.setPath("/board");
				if ("ok".equals(saveid)) {
					cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				return "redirect:/";
			} else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "error/error";
		}

	}

	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();
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
